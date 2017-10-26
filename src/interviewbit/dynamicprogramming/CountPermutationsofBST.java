/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Duy Dang
 */
public class CountPermutationsofBST {

    private static int mod = 1000000007;
    private long[][] dp;
    private long[][] comb;

    public int cntPermBST(int A, int B) {
        dp = new long[A + 1][B + 1];
        dp[0][0] = dp[1][0] = 1;
        comb = new long[A + 1][A + 1];
        combine();
        for (int a = 2; a <= A; a++) {

            for (int b = 1; b <= B; b++) {

                for (int k = 1; k <= a; k++) {
                    int x = k - 1; // root index of left subtree
                    int y = a - x - 1; // root index of right subtree

                    long sum1 = 0, sum2 = 0, ret1 = 0;

                    for (int l = 0; l <= b - 2; l++) {
                        // iterate over height of left subtree
                        sum1 = (sum1 + dp[x][l]) % mod;
                        // iterate over height of right subtree
                        sum2 = (sum2 + dp[y][l]) % mod;
                    }
                    // number of permutations to form left subtree
                    ret1 = (sum1 * dp[y][b - 1]) % mod;
                    // add number of permutations to form right subtree
                    ret1 += (sum2 * dp[x][b - 1]) % mod;
                    // add the case when both heights = m - 1
                    ret1 += (dp[x][b - 1] * dp[y][b - 1]) % mod;

                    // any sequence of size(x + y) can give same BST
                    // if the mutual ordering of the permutations from setA 
                    // and permutations of setB is maintained.
                    ret1 = (ret1 * comb[x + y][y]) % mod;

                    dp[a][b] = (dp[a][b] + ret1) % mod;
                }
            }
        }
        return (int) dp[A][B];
    }

    private void combine() {
        comb[0][0] = 1;
        for (int i = 1; i < comb.length; i++) {
            comb[i][0] = 1;

            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i - 1][j] + comb[i - 1][j - 1];
                comb[i][j] %= mod;
            }
        }
    }

    /* matrix indices must be int or lossy conversion error */
    public static class RecursiveSolution {

        static long[][] comb = new long[51][51];

        static {
            for (int n = 0; n <= 50; n++) {
                comb[n][0] = 1;
                for (int i = 1; i <= n; i++) {
                    comb[n][i] = comb[n][i - 1] * (n - i + 1) % mod * inverse(i) % mod;
                }
            }
        }

        public int cntPermBST(int n, int targetHeight) {
            if (n < 0 || targetHeight < 0) {
                return 0;
            }
            if (targetHeight + 1 > n) {
                return 0;
            }
            if (targetHeight == 0) {
                return (n == 1) ? 1 : 0;
            }
            long[][] dp = new long[n + 1][targetHeight + 2];
            dp[0][0] = 1;
            dp[1][1] = 1;

            for (int height = 2; height <= targetHeight + 1; height++) {
                for (int nodes = 2; nodes <= n; nodes++) {
                    for (int root = 1; root <= nodes; root++) {
                        for (int restHeight = 0; restHeight < height; restHeight++) {
                            dp[nodes][height] = (int) ((dp[nodes][height] + dp[root - 1][restHeight] 
                                    * dp[nodes - root][height - 1] % mod 
                                    * combination(nodes - 1, Math.min(root - 1, nodes - root)) % mod) % mod);
                            if (restHeight + 1 != height) {
                                dp[nodes][height] = (int) ((dp[nodes][height] + dp[root - 1][height - 1] 
                                        * dp[nodes - root][restHeight] % mod 
                                        * combination(nodes - 1, Math.min(root - 1, nodes - root)) % mod) % mod);
                            }
                        }
                    }
                }
            }

            return (int) (dp[n][targetHeight + 1]);
        }

        private long combination(int n, int k) {
            return comb[n][k];
        }

        private static long pow(long a, int n) {
            if (a == 0) {
                return 0;
            }
            if (n == 0) {
                return 1;
            }
            if (n % 2 == 1) {
                return a * pow(a * a % mod, n / 2) % mod;
            }
            return pow(a * a % mod, n / 2);
        }

        private static long inverse(int a) {
            return pow(a, mod - 2);
        }
    }

}
