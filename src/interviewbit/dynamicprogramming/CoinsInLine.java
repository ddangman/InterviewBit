/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Duy Dang
 */
public class CoinsInLine {

    public int maxcoin(ArrayList<Integer> a) {
        int n = a.size();
        // store best value for [first/second][start index][end index]
        int[][][] dp = new int[2][n][n];
        for (int i = 0; i < n; i++) {
            dp[0][i][i] = a.get(i); // initialize best pick for single coin
        }

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                int left = a.get(i) + dp[1][i + 1][j]; // pick left first
                int right = a.get(j) + dp[1][i][j - 1]; // pick right first
                if (right > left) { // take best 1st pick from either side
                    dp[0][i][j] = right;
                    // second player takes best 1st pick of remaining array
                    dp[1][i][j] = dp[0][i][j - 1]; 
                } else {
                    dp[0][i][j] = left;
                    dp[1][i][j] = dp[0][i + 1][j];
                }               
            }
        }
        
        return dp[0][0][n - 1];
    }
}
