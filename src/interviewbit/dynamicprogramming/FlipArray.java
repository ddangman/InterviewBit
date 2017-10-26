/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author Duy Dang
 */
public class FlipArray {
    
    // The solution is to divide the given numbers into two subsets 
    // such that the difference between their total sum is minimum.
    public int minimalDifferenceSplit(final List<Integer> A) {
        int n = A.size();
        int[] a = new int[n];
        // final List can not be sorted, copy to array for sorting
        for (int i = 0; i < n; i++) {
            a[i] = A.get(i);
        }
        Arrays.sort(a);
        int sum = IntStream.of(a).sum();
        int half = (sum / 2) + 1; // +1 for zero
        // memoize if it is possible to make target sum with any subset
        boolean[] canSum = new boolean[half]; 
        canSum[0] = true; // zero sum is possible
        // memoize minimum values that would be used to get the sum
        int[] minUsed = new int[half]; // minimum values used
        int intMax = 10000; // sum won't exceed 10000, use this to preven overflow
        for (int i = 1; i < half; i++) {
            minUsed[i] = intMax;
        }

        // adding largest values first yields minimal values used
        for (int i = n - 1; i >= 0; i--) {
            // eliminate the need to create a matrix by 
            // creating a temporary row before each inner loop
            // and memoizing it after each loop
            boolean[] newRow = new boolean[half];
            /* iterating 'h' from half-1 down to 0 also works */
            /* or increasing i and decreasing h */
            /* any combination EXCEPT increasing i and increasing h */
            for (int h = 0; h < half; h++) { // iterate to target sum, must be inner loop
                if (canSum[h]) {
                    newRow[h] = true;
                    int now = a[i];
                    if (h + now < half) {
                        newRow[h + now] = true;
                        minUsed[h + now] = Math.min(minUsed[h] + 1, minUsed[h + now]);
                    }
                }
            }
            canSum = newRow; // update memoization row 
        }

        for (int h = half - 1; h >= 0; h--) {
            if (canSum[h]) {
                return minUsed[h];
            }
        }
        return minUsed[0];
    }

    public int solveDP(List<Integer> a) {
        int n = a.size();
        int sum = a.stream().mapToInt(Integer::intValue).sum();
        int half = sum / 2; // target sum is half total
        int[][] dp = new int[n + 1][half + 1];

        // initialize no options dp[row] == 0
        int maxInt = 10001; // sum of all elements will not exceed 10,000
        // set initial option's output to int max
        for (int h = 1; h <= half; h++) {
            dp[0][h] = maxInt;
        }

        // iterate from 1 to half sum
        // decide whether to use option 
        // (current sum - option value) + 1 < previous option
        // or not
        // dp[m + 1] will be updated 
        // dp[m] is carrying previous memoization
        for (int h = 1; h <= half; h++) {
            for (int m = 0; m < n; m++) {
                // memoize dp[m + 1][h]

                int now = a.get(m);
                // sum is greater than current option's value
                // so we can go back to that (sum - option's value) memoization
                // and determine if plus one flip is better than 
                // current carrying calculations
                if (now <= h && dp[m][h] > dp[m][h - now] + 1) {
                    dp[m + 1][h] = dp[m][h - now] + 1;
                } else {
                    dp[m + 1][h] = dp[m][h]; // carry previous calculation
                }
            }
        }
//        utilities.MatrixUtilities.print2DArray(dp);
        for (int h = half; h >= 0; h--) {
            if (dp[n][h] != maxInt) {
                return dp[n][h];
            }
        }

        return dp[n][0];
    }
}
