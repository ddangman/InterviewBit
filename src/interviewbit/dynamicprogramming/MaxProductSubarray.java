/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.List;

/**
 *
 * @author Dang
 */
public class MaxProductSubarray {

    // solution using n^2 space
    public int maxProductMaxMemory(final List<Integer> a) {
        int max = a.get(0);
        int n = a.size();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = a.get(i);
            if (dp[i][i] > max) {
                max = dp[i][i];
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = r + 1; c < n; c++) {
                dp[r][c] = dp[c][c] * dp[r][c - 1];
                if (dp[r][c] > max) {
                    max = dp[r][c];
                }
            }
        }

        return max;
    }

    // solution using non-negative segment space
    public int maxProduct(final List<Integer> a) {
        int max = a.get(0);
        int n = a.size();
        for (int i = 0; i < n; ) {
            int get = a.get(i);
            if (get > max) {
                max = get;
            }            
            if (get != 0) {
                int start = i++;
                while (i < n && a.get(i) != 0) {
                    i++;
                }
                int ms = maxSegment(a, start, i - 1);
                max = Math.max(max, ms);
            } else {
                i++;
            }           
        }

        return max;
    }
    
    private int maxSegment(final List<Integer> a, int start, int end) {
        int max = a.get(start);
        int n = end - start + 1;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = a.get(start++);
        }

        for (int r = 0; r < n; r++) {
            for (int c = r + 1; c < n; c++) {
                dp[r][c] = dp[c][c] * dp[r][c - 1];
                if (dp[r][c] > max) {
                    max = dp[r][c];
                }
            }
        }

        return max;
    }
}
