/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.List;

/**
 *
 * @author Dang
 */
public class MaxDistance {
    /* utility functions for integer comparisons */
    private int max(int x, int y) {
        return x > y ? x : y;
    }

    private int min(int x, int y) {
        return x > y ? y : x;
    }

    public int maximumGap(final List<Integer> a) {
        if (a.size() <= 1) {
            return 0;
        }
        // holds increasing values of list a from right to left
        int[] rightMax = new int[a.size()];
        // holds decreasing values of list a from left to right
        int[] leftMin = new int[a.size()];

        // when constructing auxillary arrays,
        // irrelevant values are dropped
        // and last relevant value is copied forward.
        // leftMin will have new minimum values copied into their proper index
        // rightMax will have new maximum values copied into their proper index
        leftMin[0] = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            leftMin[i] = min(leftMin[i - 1], a.get(i));
        }
        rightMax[a.size() - 1] = a.get(a.size() - 1);
        for (int i = a.size() - 2; i >= 0; i--) {
            rightMax[i] = max(rightMax[i + 1], a.get(i));
        }

        int maxDist = -1;
        // start both indexes at zero
        int r = 0;
        int l = 0;
        while (r < a.size() && l < a.size()) {
            // since maximum value of rightMax is extrapolated back to zero
            // traverse rightMax until we arrive to index of origin
            //  continuing further will result in the next lower value
            //  if this value is greater than current minimum, 
            //  traverse until index of origin is reached            
            if (leftMin[l] <= rightMax[r]) {
                maxDist = max(r - l, maxDist); // save maximum distance
                r++; // find origin of max index
            } else { // minimum greater than max
                l++; // search for next minimum value
            }
        }
        return maxDist;
    }
}
