/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class RodCutting {

    private long[][] dp;
    private int[] cuts;
    private int[][] parent;
    private ArrayList<Integer> solution;

    public ArrayList<Integer> rodCut(int rod, ArrayList<Integer> scores) {
        // insert zero and rod length
        int n = scores.size() + 2;
        cuts = new int[n];
        cuts[0] = 0;
        for (int i = 0; i < scores.size(); i++) {
            cuts[i + 1] = scores.get(i);
        }
        cuts[n - 1] = rod;

        // initialize matrices
        dp = new long[n][n];
        parent = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int s = 0; s <= n - len; s++) {
                int e = s + len - 1;
                for (int k = s + 1; k < e; k++) {
                    long sum = dp[s][k] + dp[k][e] + cuts[e] - cuts[s];
                    if (dp[s][e] == 0 || sum < dp[s][e]) {
                        dp[s][e] = sum;
                        parent[s][e] = k;
                    }
                }
            }
        }
        solution = new ArrayList<>();
        backtrack(0, n - 1); //build solution
        
        return solution;
    }

    private void backtrack(int s, int e) {
        // base case: start index is adjacent to end index
        if (s + 1 >= e) {
            return;
        }

        // add current parent
        solution.add(cuts[parent[s][e]]);

        // call back recursively for two new segments
        // call left segment first becuase we want lexicographically smallest
        backtrack(s, parent[s][e]);
        backtrack(parent[s][e], e);
    }

    public class Simplified {

        // driver method with expected output
        public void main(String[] args) {
            RodCutting rc = new RodCutting();
            ArrayList<Integer> given = new ArrayList<Integer>(Arrays.asList(1, 2, 5));
            System.out.println(rc.rodCut(6, given)); // 2, 1, 5
            ArrayList<Integer> price = new ArrayList<Integer>(Arrays.asList(2, 3, 6, 7, 14, 22, 25, 30, 34, 45, 51, 52, 54, 56, 57, 58, 59, 61, 63, 66, 76, 78));
            System.out.println(rc.rodCut(100, price)); // 56 30 14 7 3 2 6 22 25 45 34 51 54 52 78 66 61 58 57 59 63 76 
        }

        private ArrayList<Integer> build;

        public ArrayList<Integer> rodCut(int rod, ArrayList<Integer> scores) {
            int n = scores.size() + 2;
            int[] cuts = new int[n];
            cuts[0] = 0;
            for (int i = 0; i < scores.size(); i++) {
                cuts[i + 1] = scores.get(i);
            }
            cuts[n - 1] = rod;

            long[][] dp = new long[n][n];
            int[][] parent = new int[n][n];
            for (int len = 1; len <= n; len++) {
                // if range includes s <= n - len, exclude for 'e'
                // e = s + len - 1
                for (int s = 0; s < n - len; s++) {
                    int e = s + len;
                    for (int k = s + 1; k < e; k++) {
                        long sum = cuts[e] - cuts[s] + dp[s][k] + dp[k][e];
                        if (dp[s][e] == 0 || dp[s][e] > sum) {
                            dp[s][e] = sum;
                            parent[s][e] = k;
                        }
                    }
                }
            }

            build = new ArrayList<>();
            recursion(parent, cuts, 0, n - 1);

            return build;
        }

        private void recursion(int[][] parent, int[] cuts, int start, int end) {
            if (start + 1 >= end) {
                return;
            }

            build.add(cuts[parent[start][end]]);
            recursion(parent, cuts, start, parent[start][end]);
            recursion(parent, cuts, parent[start][end], end);
        }
    }
}
