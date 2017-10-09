/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Duy Dang
 */
public class MinSumPathInTriangle {

    public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        int rows = a.size();
        int[] dp = new int[rows];
        ArrayList<Integer> cur = a.get(rows - 1);
        for (int i = 0; i < cur.size(); i++) {
            dp[i] = cur.get(i);
        }

        for (int i = rows - 2; i >= 0; i--) {
            cur = a.get(i);
            for (int j = 0; j < cur.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + cur.get(j);
            }
        }
        return dp[0];
    }
}
