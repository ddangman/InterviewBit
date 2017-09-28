/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MaxSumWithoutAdjacentElements {

    public int adjacent(ArrayList<ArrayList<Integer>> a) {
        int n = a.get(0).size();
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return Math.max(a.get(0).get(0), a.get(1).get(0));
        }

        int[] dp = new int[n];
        // get maximum of first 2
        dp[0] = Math.max(a.get(0).get(0), a.get(1).get(0));
        dp[1] = Math.max(a.get(0).get(1), a.get(1).get(1));

        // greedy solution
        // since max sum can't be touching, add max for column to max 
        // that is 2 or 3 columns behind
        for (int i = 2; i < n; i++) {
            if (i == 2) {
                dp[i] = Math.max(a.get(0).get(i), a.get(1).get(i)) + dp[i - 2];
            } else {
                dp[i] = Math.max(a.get(0).get(i), a.get(1).get(i))
                        + Math.max(dp[i - 2], dp[i - 3]);
            }
        }

        return Math.max(dp[n - 1], dp[n - 2]);
    }
}
