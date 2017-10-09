/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Duy Dang
 */
public class RepeatingSubsequence {

    public int anytwo(String a) {
        int n = a.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r != c && a.charAt(r) == a.charAt(c)) {
                    if (dp[r][c] > 0) {
                        return 1;
                    } else {
                        dp[r + 1][c + 1] = dp[r][c] + 1;
                    }                   
                } else {
                    dp[r + 1][c + 1] = Math.max(dp[r][c + 1], dp[r + 1][c]);
                }               
            }
        }
        
        return 0;
    }
}
