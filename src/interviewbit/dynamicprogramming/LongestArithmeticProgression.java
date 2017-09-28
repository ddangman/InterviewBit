/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Dang
 */
public class LongestArithmeticProgression {

    public int solveTM(final List<Integer> a) {
        int n = a.size();
        TreeMap<Integer, Integer>[] dp = new TreeMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new TreeMap<>();
        }
        int max = 0;
        if (n > 1) {
            max++;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int diff = a.get(i) - a.get(j);
                Integer val = dp[j].get(diff);
                // greedy for most right diff
                if (val != null && !dp[i].containsKey(diff)) {
                    dp[i].put(diff, val + 1);
                    if (val + 1 > max) {
                        max = val + 1;
                    }
                } else {
                    if (!dp[i].containsKey(diff)) {
                        dp[i].put(diff, 1);
                    }
                }
            }
        }

        return max + 1;
    }

    public int solveDP(final List<Integer> A) {

        int n = A.size();
        if (n <= 2) {
            return n;
        }
        int set[] = new int[n];
        for (int i = 0; i < n; i++) {
            set[i] = A.get(i);
        }
        Arrays.sort(set);
        int L[][] = new int[n][n];
        int llap = 2;
        for (int i = 0; i < n; i++) {
            L[i][n - 1] = 2;
        }
        for (int j = n - 2; j >= 1; j--) {

            int i = j - 1;
            int k = j + 1;
            while (i >= 0 && k <= n - 1) {
                if (set[k] - set[j] < set[j] - set[i]) {
                    k++;
                } else if (set[k] - set[j] > set[j] - set[i]) {
                    L[i][j] = 2;
                    i--;
                } else {
                    L[i][j] = L[j][k] + 1;
                    llap = Math.max(llap, L[i][j]);
                    i--;
                    k++;
                }
            }
            while (i >= 0) {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }
}
