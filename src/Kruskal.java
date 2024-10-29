import java.util.ArrayList;
import java.util.Arrays;

/**
 * Kruskal's algorithm written in class
 * Completed during class for CS 202: Analysis of Algorithms at Lafayette College.
 *
 * @author Jackson Eshbaugh
 * @version 10/29/2024
 */
public class Kruskal {
    static ArrayList<int[]> mst = new ArrayList<>();

    public static void main(String[] args) {

        int n = 4;
        // assume vertices are labelled by integers [0,..,n-1]
        int[][] E = {{0, 1, 8}, {0, 2, 1}, {2, 1, 5}, {2, 3, 2}, {3, 1, 2}};

        Vertex[] V = new Vertex[n];
        for (int i = 0; i < n; i++) {
            V[i] = new Vertex(i);
        }

        // run Kruskal's algorithm

        kruskal(V, E);

        for (int[] e : mst) {
            System.out.println(Arrays.toString(e));
        }

    }

    /**
     * Run Kruskal's algorithm
     * @param V set of vertices
     * @param E set of edges
     */
    static void kruskal(Vertex[] V, int[][] E) {
        Arrays.sort(E, (a, b) -> { return a[2] - b[2]; });

        for(int[] e: E) {
            Vertex u = V[e[0]];
            Vertex v = V[e[1]];
            Vertex uRoot = find(u);
            Vertex vRoot = find(v);
            if(uRoot != vRoot) {
                union(uRoot, vRoot);
                mst.add(e);
            }
        }
    }

    /**
     * Finds the root node of u
     * @param u the node to find the root of
     * @return the root of u
     */
    static Vertex find(Vertex u) {
        if(u.parent == null) return u;
        else return u.parent = find(u.parent); // use path compression
    }

    /**
     * Disjoint set union operation
     * @param u a disjoint set to join
     * @param v a disjoint set to join
     */
    static void union(Vertex u, Vertex v) {
        if(u == v) return;
        if(u.rank > v.rank) {
            v.parent = u;
        } else if(u.rank < v.rank) {
            u.parent = v;
        } else {
            v.parent = u;
            u.rank++;
        }
    }

    static class Vertex {
        int id;
        Vertex parent;
        int rank = 0;

        public Vertex(int id) {
            this.id = id;
            this.parent = null;
        }
    }
}
