/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Dang
 */
public class WaysToColor3xNBoard {

    public int solve(int n) {
        int mod = 1000000007;
        long dp_same[] = new long[n + 1];
        long dp_diff[] = new long[n + 1];
        dp_same[1] = 12; 
        dp_diff[1] = 24; 

        for (int i = 2; i <= n; i++) {
            dp_same[i] = (((dp_same[i - 1] % mod) * 7) % mod + ((dp_diff[i - 1] % mod) * 5) % mod) % mod;
            dp_diff[i] = (((dp_same[i - 1] % mod) * 10) % mod + ((dp_diff[i - 1] % mod) * 11) % mod) % mod;
        }

        return (int) (dp_same[n] % mod + dp_diff[n] % mod) % mod;
    }
}
