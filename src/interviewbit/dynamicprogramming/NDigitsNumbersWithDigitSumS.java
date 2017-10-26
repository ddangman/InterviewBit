/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Dang
 */
public class NDigitsNumbersWithDigitSumS {

    // iteration solution
    public int solve(int digit, int sum) {
        int mod = 1000000007;
        int[][] dp = new int[digit + 1][sum + 1];

        // fill in first digit loop 'd == 1'
        for (int s = 1; s <= Math.min(9, sum); s++) {
            // only one combination possible, 
            // excluding zero and values greater than sum
            dp[1][s] = 1;
        }
        for (int d = 2; d <= digit; d++) { // digit loop
            for (int s = 1; s <= sum; s++) { // sum loop
                for (int k = 0; k <= Math.min(9, s); k++) {
                    // add all previous digit (row: d - 1)
                    // within (0 - 9) or current column (sum) if lesser
                    dp[d][s] += dp[d - 1][s - k];
                    dp[d][s] %= mod;
                }
            }
        }

        return dp[digit][sum];
    }

    public class RecursionSolution {

        private int[][] dp;
        private long total;
        private int mod = 1000000007;

        // permutation equation
        // sum * (sum + n-2)! / sum! * (n-1)!
        public int solve(int n, int sum) {
            // initialize dynamic programming matrix
            dp = new int[n + 1][sum + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < sum; j++) {
                    dp[i][j] = -1;
                }
            }

            total = 0;

            // wrapper recursion to exclude leading zeros
            for (int i = 1; i < 10; i++) {
                total += solveRecursion(n - 1, sum - i);
                total %= mod;
            }

            return (int) total;
        }

        private int solveRecursion(int n, int sum) {
            // base case
            if (n == 0) {
                return sum == 0 ? 1 : 0;
            } else if (sum < 0) {
                return 0;
            }

            // memoization
            if (dp[n][sum] != -1) {
                return dp[n][sum];
            }

            // calculate recursively
            long digitSum = 0;
            for (int i = 0; i < 10; i++) {
                digitSum += solveRecursion(n - 1, sum - i);
                digitSum %= mod;
            }

            // save and return result
            return dp[n][sum] = (int) digitSum;
        }
    }

}
