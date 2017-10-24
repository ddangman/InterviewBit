/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Duy Dang
 */
public class LongestCommonSubstring {

    public static int longestCommonSubstring(String x, String y) {
        int xl = x.length();
        int yl = y.length();
        int[][] dp = new int[xl + 1][yl + 1];
        int max = 0;
        for (int xi = 1; xi <= xl; xi++) {
            for (int yi = 0; yi <= yl; yi++) {
                if (x.charAt(xi) == y.charAt(yi)) {
                    dp[xi][yi] = dp[xi - 1][yi - 1] + 1;
                    if (dp[xi][yi] > max) {
                        max = dp[xi][yi];
                    }
                }
            }
        }
        return max;
    }
}
