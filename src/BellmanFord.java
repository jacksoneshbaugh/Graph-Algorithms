import java.util.*;

/**
 * Bellman-Ford algorithm written in class
 * Completed during class for CS 202: Analysis of Algorithms at Lafayette College.
 *
 * @author Jackson Eshbaugh
 * @version 10/22/2024
 */
public class BellmanFord {
    public static void main(String[] args) {

        int n = 4;
        // assume vertices are labelled by integers [0,..,n-1]
        int[][] E = {{0, 1, 8}, {0, 2, 10}, {2, 1, -5}, {2, 3, 2}, {3, 1, 2}};

        Vertex[] G = new Vertex[n];
        for (int i = 0; i < n; i++) {
            G[i] = new Vertex(i);
        }

        // add edges
        for (int[] e : E) {
            int a = e[0];
            int b = e[1];
            int w = e[2];
            G[a].neighbors.add(new Edge(G[b], w));
        }

        // run Bellman-Ford algorithm

        bellmanFord(G, n, G[0]);

        for(int i = 0; i < n; i++) System.out.println(G[i].dist);
    }

    static void bellmanFord(Vertex[] G, int n, Vertex start) {
        start.dist = 0;
        for (int r = 0; r < n - 1; r++) {
            for (Vertex u : G) {
                for (Edge e : u.neighbors) {
                    Vertex v = e.end;
                    int w = e.weight;

                    if(v.dist > u.dist + w) {
                        v.dist = u.dist + w;
                    }
                }
            }
        }

        for(Vertex u : G) {
            for(Edge e : u.neighbors) {
                Vertex v = e.end;
                int w = e.weight;

                if(v.dist > u.dist + w) {
                    System.out.println("Negative cycle containing " + v.id + " detected!");
                }
            }
        }
    }

    static class Vertex {
        int id;
        int dist = Integer.MAX_VALUE;
        ArrayList<Edge> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }
    }

    static class Edge {
        Vertex end;
        int weight;

        public Edge(Vertex end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
