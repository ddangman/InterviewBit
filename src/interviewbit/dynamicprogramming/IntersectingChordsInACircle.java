/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a number N, return number of ways you can draw N chords in a circle
 * with 2*N points such that no 2 chords intersect. Solution: Catalan number
 *
 * @author DuyDang
 */
public class IntersectingChordsInACircle {

    int mod = 1000000007;
    long[] lookUp;

    public int chordRecursion(int n) {
        lookUp = new long[n + 1];
        Arrays.fill(lookUp, -1);
        return (int) recursion(n);
    }

    private long recursion(int n) {
        if (n == 0) {
            return 1;
        } else if (lookUp[n] != -1) {
            return lookUp[n];
        }

        long product = 0;
        for (int i = 0; i < n; i++) {
            product += recursion(i) * recursion(n - i - 1);
            product %= mod;
        }

        return lookUp[n] = product;
    }

    // iteration
    public int chordCnt(int n) {
        long[] dp = new long[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
                dp[i] %= mod;
            }
        }

        return (int) dp[n];
    }
}
