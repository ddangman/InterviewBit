/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class LargestAreaOfRectangleWithPermutations {

    public int maxArea(ArrayList<ArrayList<Integer>> input) {
//        utilities.ArrayUtilities.printMatrix(a);
        int row = input.size();
        int col = input.get(0).size();
        int[][] dp = new int[row + 1][col + 1];

        // count consecutive vertical ones
        for (int c = 0; c < col; c++) {
            // copy from input to prevent index out of bounds
            dp[0][c] = input.get(0).get(c);
        }
        for (int r = 1; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (input.get(r).get(c) == 0) {
                    dp[r][c] = 0;
                } else {
                    // increase consecutive count if previous row,
                    // same column holds '1'
                    dp[r][c] = dp[r - 1][c] + 1;
                }
            }
        }
//        utilities.MatrixUtilities.print2DArray(dp);

        // sort each row in descending dp column value
        for (int r = 0; r < row; r++) {
            // count consecutive occurances
            int[] count = new int[row + 1];
            for (int c = 0; c < col; c++) {
                count[dp[r][c]]++;
            }
            // place occurances in descending order
            int cn = 0; // column number
            //  Traverse the count array from greatest (right) side
            for (int c = row; c >= 0; c--) {
                if (count[c] > 0) {
                    for (int i = 0; i < count[c]; i++) {
                        // update dp to descending column value
                        dp[r][cn++] = c;
                    }
                }
            }
        }
//        utilities.MatrixUtilities.print2DArray(dp);

        // find max area from sorted dp matrix
        int maxArea = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // since matrix valuers are in descending order
                // the area with bottom right at cell (r, c)
                // can be obtained by multiplying width (column number + 1) with
                // height (dp value)
                int area = dp[r][c] * (c + 1);
                if (area > maxArea) {
                    maxArea = area;

                }
            }
        }

        return maxArea;
    }

}
