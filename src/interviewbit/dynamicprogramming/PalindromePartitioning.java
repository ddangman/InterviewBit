/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Dang
 */
public class PalindromePartitioning {

    String input;

    public int minCut(String input) {
        this.input = input;
        int n = input.length();
        int[][] dp = new int[n][n]; // stores minimum cut for range[start][end]

        for (int len = 1; len <= n; len++) {
            for (int s = 0; s < n - len; s++) { // start
                int e = s + len; // end
                if (isPalindrome(s, e)) {
                    dp[s][e] = 0;
                } else {
                    // find minimum split for range start to end
                    int min = Integer.MAX_VALUE;
                    for (int k = s; k < e; k++) { // split entire sublength at 'k'
                        // add splits from both sides of 'k'
                        int add = dp[s][k] + dp[k + 1][e];
                        if (add < min) { // save best split
                            /* store 'k' here to lookup where split occurs */
                            min = add;
                        }
                    }
                    dp[s][e] = min + 1; // save minimum split plus one for 'k'
                }
            }
        }
        return dp[0][n - 1];
    }

    private boolean isPalindrome(int s, int e) {
        while (s < e) {
            if (input.charAt(s++) != input.charAt(e--)) {
                return false;
            }
        }
        return true;
    }
}
