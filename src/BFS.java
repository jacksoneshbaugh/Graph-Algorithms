import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Breadth-first search algorithm written in class
 * Completed during class for CS 202: Analysis of Algorithms at Lafayette College.
 *
 * @author Jackson Eshbaugh
 * @version 10/08/2024
 */
public class BFS {
    public static void main(String[] args) {
        int n = 8;
        // assume vertices are labelled by integers [0,..,n-1]
        int[][] E = {{0,1}, {4,0}, {0,5}, {3,2}, {2,1}, {4,5}, {6,7}};

        // adjacency list
        List<Integer>[] G = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<Integer>();
        }

        for (int[] e: E){
            G[e[0]].add(e[1]);
            G[e[1]].add(e[0]);
        }

        // run DFS algorithm
        int[] dist = bfs(G, n, 0);

        for(int i : dist) System.out.println(i);
    }

    // compute the distance from start to every vertex in the graph
    public static int[] bfs(List<Integer>[] G, int n, int start) {
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) dist[i] = -1;

        Queue<Integer> q = new ArrayDeque<>();
        dist[start] = 0;
        q.add(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for(int next : G[curr]){
                if(dist[next] == -1){
                    dist[next] = dist[curr] + 1;
                    q.add(next);
                }
            }
        }
        return dist;
    }
}
