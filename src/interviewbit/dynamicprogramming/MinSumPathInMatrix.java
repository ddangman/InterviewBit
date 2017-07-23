/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MinSumPathInMatrix {

    public int minPathSum(ArrayList<ArrayList<Integer>> input) {
        int row = input.size();
        int col = input.get(0).size();
        int[][] m = new int[row][col];

        m[0][0] = input.get(0).get(0);
        for (int i = 1; i < row; i++) {
            m[i][0] = input.get(i).get(0) + m[i - 1][0];
        }
        for (int i = 1; i < col; i++) {
            m[0][i] = input.get(0).get(i) + m[0][i - 1];
        }

        int x;
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                x = Math.min(m[r][c - 1], m[r - 1][c]);
                m[r][c] = x + input.get(r).get(c);
            }
        }

        return m[row - 1][col - 1];
    }
}
