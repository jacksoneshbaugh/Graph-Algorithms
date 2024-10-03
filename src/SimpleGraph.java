import java.util.List;
import java.util.ArrayList;

/**
 * A simple implementation of a graph G = (V, E) using both an adjacency matrix and
 * an adjacency list. Completed during CS 202: Analysis of Algorithms at Lafayette College.
 *
 * @author Jackson Eshbaugh
 * @version 09/19/2024
 */
public class SimpleGraph {

    public static void main(String[] args) {
        int n = 6;
        // assume vertices are labelled by integers [0,..,n-1]
        int[][] E = {{0,1}, {0,5}, {4,0}, {3,2}, {2,1}, {4,5}, {5,3}};

        // adjacency list
        List<Integer>[] G = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<Integer>();
        }

        for (int[] e: E){
            G[e[0]].add(e[1]);
            G[e[1]].add(e[0]);
        }

        // adjacency matrix
        int[][] M = new int[n][n];

        for (int[] e: E) {
            M[e[0]][e[1]] = M[e[1]][e[0]] = 1;
        }
    }

}