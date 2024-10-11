import java.util.*;

/**
 * Dijkstra's algorithm written in class
 * Completed during class for CS 202: Analysis of Algorithms at Lafayette College.
 *
 * @author Jackson Eshbaugh
 * @version 10/10/2024
 */
public class Dijkstra {
    public static void main(String[] args) {

        int n = 4;
        // assume vertices are labelled by integers [0,..,n-1]
        int[][] E = {{0, 1, 8}, {0, 2, 1}, {2, 1, 5}, {2, 3, 2}, {3, 1, 2}};

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

        // run Dijkstra's algorithm

        dijkstra(G, G[0]);

        for(int i = 0; i < n; i++) System.out.println(G[i].dist);
    }

    static void dijkstra(Vertex[] G, Vertex start) {
        PriorityQueue<PqNode> pq = new PriorityQueue<>();
        pq.add(new PqNode(start, 0));
        start.dist = 0;

        while (!pq.isEmpty()) {
            PqNode curr = pq.poll();

            Vertex u = curr.v;
            int d = curr.d;
            if(d != u.dist) continue;

            for(Edge e : u.neighbors) {
                Vertex v = e.end;
                int w = e.weight;
                if(v.dist > u.dist + w) {
                    v.dist = u.dist + w;
                    pq.add(new PqNode(v, v.dist));
                }
            }
        }
    }

    static class PqNode implements Comparable<PqNode> {
        Vertex v;
        int d;

        public PqNode(Vertex v, int d) {
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(PqNode that) {
            return this.d - that.d;
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
