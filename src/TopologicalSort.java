import java.util.ArrayList;

/**
 * An algorithm to topologically sort a DAG (directed acyclic graph) written in class.
 * Completed during class for CS 202: Analysis of Algorithms at Lafayette College.
 *
 * @author Jackson Eshbaugh
 * @version 10/01/2024
 */
public class TopologicalSort {

    static int clock = 0;
    static Vertex[] tSort;
    static int idx;

    public static void main(String[] args) {
        int n = 6;

        // assume vertices are labelled by integers [0,..,n-1]
        int[][] E = {{0,1}, {1,4}, {1,5}, {2,0}, {2,3}, {3,1}};

        Vertex[] V = new Vertex[n];
        tSort = new Vertex[n];
        idx = n-1;


        // Initialize the vertices
        for(int i = 0; i < n; i++) {
            V[i] = new Vertex(i);
        }

        // Add the edges to the graph
        for(int[] e : E){
            int a = e[0];
            int b = e[1];
            V[a].neighbors.add(V[b]);
        }

        // DFS
        for(int i = 0; i < n; i++) {
            if(!V[i].visited) {
                explore(V[i]);
            }
        }

        // Results
        for (int i = 0; i < n; i++) {
            System.out.println(i + ": " + V[i].pre + "/" + V[i].post);
        }
        System.out.print("Topological sort:");
        for (Vertex u: tSort) System.out.print(" " + u.id);
        System.out.println();
    }

    private static void explore(Vertex u) {
        u.visited = true;
        u.pre = clock++;
        for(Vertex v : u.neighbors) {
            if(!v.visited) explore(v);
        }
        u.post = clock++;
        tSort[idx--] = u;
    }

    /**
     * Representation of a vertex in a graph.
     */
    static class Vertex {
        int id;
        int pre = -1, post = -1;
        boolean visited = false;
        ArrayList<Vertex> neighbors = new ArrayList<>(); // Adjacency list

        public Vertex(int id) {
            this.id = id;
        }
    }
}
