import java.util.ArrayList;
import java.util.List;

/**
 * Depth-first search algorithm written in class
 * Completed during class for CS 202: Analysis of Algorithms at Lafayette College.
 *
 * @author Jackson Eshbaugh
 * @version 09/24/2024
 */
public class DFS {
    static int clock = 0;
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
        dfs(G, n);
    }

    // explore every vertex once and traverse every edge twice
    public static void dfs(List<Integer>[] G, int n) {
        int components = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                components++;
                System.out.print("Component " + components + ":");
                explore(G, visited, i);
                System.out.println();
            }
        }
        System.out.println("There are " + components + " components.");
    }
    //explore every unvisted vertex from v
    public static void explore(List<Integer>[] G, boolean[] visited, int v) {
        visited[v] = true;
        //previsit(v);
        System.out.print(" " + v);
        for(int u : G[v]) {
            if(!visited[u]) {
                explore(G, visited, u);
            }
        }
        //postvisit(v);
    }

    public static void previsit(int v) {
        clock++;
        System.out.println("pre-" + v + " at clock " + clock);
    }

    public static void postvisit(int v) {
        clock++;
        System.out.println("post-" + v + " at clock " + clock);
    }
}
