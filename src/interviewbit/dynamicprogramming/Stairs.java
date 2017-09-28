/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Dang
 */
public class Stairs {

    public int climbStairs(int a) {
        int[] dp = new int[a + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= a; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[a];
    }
}
