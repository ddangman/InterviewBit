/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Duy Dang
 */
public class LongestCommonSubsequence {

    public static int lcs(String x, String y) {
        int xl = x.length();
        int yl = y.length();
        int[][] dp = new int[xl + 1][yl + 1];

        for (int i = 1; i <= xl; i++) {
            for (int j = 1; j <= yl; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[xl][yl];
    }
}
