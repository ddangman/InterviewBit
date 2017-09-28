/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MinJumpsArray {

    // O(n)
    public int minJumps(ArrayList<Integer> arr) {
        if (arr.size() <= 1) {
            return 0;
        }

        // Return -1 if not possible to jump
        if (arr.get(0) == 0) {
            return -1;
        }

        // initialization
        int maxReach = arr.get(0); // farthest index that can be reached
        int step = arr.get(0); // steps to reach end of current jump
        int jump = 1; // count of jumps

        // Start traversing array
        for (int i = 1; i < arr.size(); i++) {
            // Check if we have reached the end of the array
            if (i == arr.size() - 1) {
                return jump;
            }

            // updating maxReach
            maxReach = Math.max(maxReach, i + arr.get(i));

            // we use a step to get to the current index
            step--;

            // If no further steps left
            if (step == 0) {
                //  we must have used a jump
                jump++;

                //Check if the current index/position  or lesser index
                // is the maximum reach point from the previous indexes
                if (i >= maxReach) {
                    return -1;
                }

                // re-initialize the steps to the amount
                // of steps to reach maxReach from position i.
                step = maxReach - i;
            }
        }

        return -1;
    }

    // O(n^2)
    public int jumpTimeExceeded(ArrayList<Integer> a) {
        int n = a.size();
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int get = a.get(i);
            if (get == 0 && dp[i] == 0) {
                return -1;
            }
            int farthest = i + get;
            if (farthest >= n - 1) {
//                utilities.ArrayUtilities.printArray(dp);
                return dp[i] + 1;
            }

            for (int j = i + 1; j <= farthest; j++) {
                if (dp[j] == 0) {
                    dp[j] = dp[i] + 1;
                } else {
                    dp[j] = Math.min(dp[j], dp[i] + 1);
                }
            }
        }
//        utilities.ArrayUtilities.printArray(dp);
        return dp[n - 1] == 0 ? -1 : dp[n - 1];
    }

    // n^2 theta
    public int jump(ArrayList<Integer> a) {
        int n = a.size();
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[n];

        for (int land = 1; land < n; land++) {
            for (int start = 0; start < land; start++) {
                if (a.get(start) + start >= land) {
                    if (dp[land] != 0) {
                        dp[land] = Math.min(dp[start] + 1, dp[land]);
                    } else {
                        dp[land] = dp[start] + 1;
                    }
                }
            }
            if (dp[land] == 0) {
                return -1;
            }
        }

        return dp[n - 1] == 0 ? -1 : dp[n - 1];
    }

}
