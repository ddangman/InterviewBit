/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Duy Dang
 */
public class UniqueGridPaths {

    // obstacles (1) block paths
    public int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> a) {
        int row = a.size();
        int col = a.get(0).size();
        int[] dp = new int[col];
        ArrayList<Integer> cur = a.get(0);
        dp[0] = cur.get(0) ^ 1; // if obstacle, 0. Else 1;
        for (int c = 1; c < col; c++) { // initialize top row
            if (cur.get(c) == 1) {
                dp[c] = 0; // path is blocked
            } else {
                dp[c] = dp[c - 1]; // extrapolate path
            }           
        }

        for (int r = 1; r < row; r++) {
            cur = a.get(r);
            if (cur.get(0) == 1) {
                dp[0] = 0;
            }            
            for (int c = 1; c < col; c++) {
                if (cur.get(c) == 1) {
                    dp[c] = 0;
                } else {
                    // if this was matrix, dp[c] is value from top
                    // dp[c - 1] is value from left
                    // after update, dp[c] will be sum of both
                    dp[c] += dp[c - 1];
                }               
            }
        }
        return dp[col - 1];
    }
}
