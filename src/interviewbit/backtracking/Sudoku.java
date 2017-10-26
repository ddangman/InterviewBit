/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class Sudoku {

    private int[][] matrix;
    private ArrayList<ArrayList<Character>> a;

    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
        // initialize matrix
        matrix = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int n = Integer.valueOf(a.get(i).get(j)) - 48;
                matrix[i][j] = n > 0 ? n : 0;
            }
        }
        this.a = a;

        solve(0, 0);

    }

    private boolean solve(int row, int col) {
        // find square to solve
        while (col < 9 && row < 9 && matrix[row][col] > 0) {
            if (col == 8) {
                col = 0;
                row++;
            } else {
                col++;
            }
        }

        // base case
        if (row == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    a.get(i).set(j, (char) (matrix[i][j] + 48));
                }
            }
            return true;
        }

        for (int i = 1; i <= 9; i++) {
            if (isLegal(row, col, i)) {
                matrix[row][col] = i;
                if (!solve(row, col)) {
                    matrix[row][col] = 0;
                }

            }
        }
        return false;
    }

    private boolean isLegal(int row, int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (matrix[row][i] == n) {
                return false;
            }
            if (matrix[i][col] == n) {
                return false;
            }
        }
        return checkSquare(row, col, n);
    }

    private boolean checkSquare(int row, int col, int n) {
        int r = row / 3;
        int c = col / 3;
        int rs = r * 3;
        int re = (r + 1) * 3;
        int cs = c * 3;
        int ce = (c + 1) * 3;
        for (int i = rs; i < re; i++) {
            for (int j = cs; j < ce; j++) {
                if (matrix[i][j] == n) {
                    return false;
                }
            }
        }
        return true;
    }
}
