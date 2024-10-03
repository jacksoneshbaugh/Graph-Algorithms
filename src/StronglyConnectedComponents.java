import java.util.ArrayList;

/**
 * An algorithm locate all the SCCs (strongly connected components) in a directed graph.
 * Implemented as practice after reviewing the pseudocode in CS 202: Analysis of Algorithms at Lafayette College.
 *
 * @author Jackson Eshbaugh
 * @version 10/03/2024
 */
public class StronglyConnectedComponents {

    static int clock = 0;
    static int[] dfsOrder;
    static int idx;


    /**
     * Finds the strongly connected components in an example graph
     * @param args (not used)
     */
    public static void main(String[] args) {

        int[][] graph = { {0, 1}, {1, 3}, {1, 2}, {1, 4}, {2, 5}, {5, 2}, {4, 1}, {4, 5},
                {6, 4}, {7, 5}, {6, 7}, {6, 9}, {7, 10}, {10, 11}, {11, 9}, {9, 8}, {8, 6}};

        int n = 12;

        Vertex[] V = new Vertex[n];

        for(int i = 0; i < n; i++) V[i] = new Vertex(i);

        for(int[] e : graph) {
            int a = e[0];
            int b = e[1];
            V[a].neighbors.add(V[b]);
        }

        findStronglyConnectedComponents(V);

    }


    /**
     * Reverses a provided graph.
     * @param V the graph (set of vertices) to reverse
     * @return G^R, the reversed graph (set of vertices)
     */
    private static Vertex[] reverseGraph(Vertex[] V) {

        Vertex[] G_R = new Vertex[V.length];

        for(int i = 0; i < V.length; i++) {
            G_R[i] = new Vertex(V[i].id);
        }

        for(int i = 0; i < V.length; i++) {
            for(Vertex u : V[i].neighbors) {
                G_R[u.id].neighbors.add(G_R[i]);
            }
        }

        return G_R;
    }

    /**
     * Finds all the strongly connected components in G (given by V) and
     * prints them, along with the count at the end.
     * @param V the graph (set of vertices) to examine
     */
    private static void findStronglyConnectedComponents(Vertex[] V){

        dfsOrder = new int[V.length];
        idx = V.length - 1;
        Vertex[] G_R = reverseGraph(V);

        // DFS 1 (on G^R), to determine order.
        for(Vertex v : G_R) {
            if(!v.visited) explore(v);
        }

        // DFS 2 (On G), in order of decreasing post timestamps
        // Determines number of and vertices in SCCs.

        int numSCCs = 0;
        for(int i : dfsOrder) {
            Vertex v = V[i];
            if(!v.visited) {
                System.out.print("Strongly Connected Component " + numSCCs + ":");
                verboseExplore(v);
                numSCCs++;
                System.out.println();
            }
        }
        System.out.println(numSCCs + " total strongly connected components.");


    }

    /**
     * Explores all possible vertices from u.
     * @param u the vertex to explore from
     */
    private static void explore(Vertex u) {
        u.visited = true;
        u.pre = clock++;
        for(Vertex v : u.neighbors) {
            if(!v.visited) explore(v);
        }
        u.post = clock++;
        dfsOrder[idx--] = u.id;
    }

    /**
     * Explores all possible vertices from u. Outputs the values of each.
     * @param u the vertex to explore from
     */
    private static void verboseExplore(Vertex u) {
        u.visited = true;
        u.pre = clock++;
        System.out.print(" " + u.id);
        for(Vertex v : u.neighbors) {
            if(!v.visited) {
                verboseExplore(v);
            }
        }
        u.post = clock++;
    }

    /**
     * Representation of a vertex in a graph.
     */
    static class Vertex {

        int id;
        int pre = -1, post = -1;
        boolean visited = false;
        ArrayList<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

    }

}
