/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class QueenAttack {

    private int row;
    private int col;
    private int[][][] dp;
    ArrayList<String> input;

    public ArrayList<ArrayList<Integer>> queenAttack(ArrayList<String> a) {
        row = a.size();
        col = a.get(0).length();
        input = a;
        // row and col correspond to input
        // numbers correspond to cell direction
        // 1 2 3
        // 4 c 5
        // 6 7 8
        // dp[r][c][0] holds sum of queen attacking
        dp = new int[row][col][9];

        // fill in matrix
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                for (int d = 1; d <= 8; d++) {
                    if (dp[r][c][d] == 0) {
                        // calculate value
                        calculate(r, c, d);
                    }
                    if (dp[r][c][d] == 1) {
                        dp[r][c][0]++;
                    }
                }
            }
        }

        // copy results to output format
        ArrayList<ArrayList<Integer>> count = new ArrayList<>();
        for (int r = 0; r < row; r++) {
            ArrayList<Integer> curRow = new ArrayList<>();
            for (int c = 0; c < col; c++) {
                curRow.add(dp[r][c][0]);
            }
            count.add(curRow);
        }

        return count;
    }

    private int calculate(int r, int c, int d) {
        // check range and memoization
        if (r < 0 || r >= row) {
            return -1;
        } else if (c < 0 || c >= col) {
            return -1;
        } else if (dp[r][c][d] != 0) {
            return dp[r][c][d];
        }

        // check for adjacent queen
        switch (d) {
            case 1:
                if (r == 0) {
                    return dp[r][c][d] = -1;
                } 
                if (c == 0) {
                    return dp[r][c][d] = -1;
                }
                if (input.get(r - 1).charAt(c - 1) == '1') {
                    return dp[r][c][d] = 1;
                }                
                return dp[r][c][d] = calculate(r - 1, c - 1, d);
            case 2:
                if (r == 0) {
                    return dp[r][c][d] = -1;
                }
                if (input.get(r - 1).charAt(c) == '1') {
                    return dp[r][c][d] = 1;
                }                
                return dp[r][c][d] = calculate(r - 1, c, d);
            case 3:
                if (r == 0) {
                    return dp[r][c][d] = -1;
                } 
                if (c == col - 1) {
                    return dp[r][c][d] = -1;
                }
                if (input.get(r - 1).charAt(c + 1) == '1') {
                    return dp[r][c][d] = 1;
                }                
                return dp[r][c][d] = calculate(r - 1, c + 1, d);
            case 4:
                if (c == 0) {
                    return dp[r][c][d] = -1;
                }
                if (input.get(r).charAt(c - 1) == '1') {
                    return dp[r][c][d] = 1;
                }                
                return dp[r][c][d] = calculate(r, c - 1, d);
            case 5:
                if (c == col - 1) {
                    return dp[r][c][d] = -1;
                }
                if (input.get(r).charAt(c + 1) == '1') {
                    return dp[r][c][d] = 1;
                }                
                return dp[r][c][d] = calculate(r, c + 1, d);
            case 6:
                if (c == 0) {
                    return dp[r][c][d] = -1;
                }
                if (r == row - 1) {
                    return dp[r][c][d] = -1;
                }  
                if (input.get(r + 1).charAt(c - 1) == '1') {
                    return dp[r][c][d] = 1;
                }                
                return dp[r][c][d] = calculate(r + 1, c - 1, d);
            case 7:
                if (r == row - 1) {
                    return dp[r][c][d] = -1;
                }
                if (input.get(r + 1).charAt(c) == '1') {
                    return dp[r][c][d] = 1;
                }                
                return dp[r][c][d] = calculate(r + 1, c, d);
            case 8:
                if (c == col - 1) {
                    return dp[r][c][d] = -1;
                }
                if (r == row - 1) {
                    return dp[r][c][d] = -1;
                }
                if (input.get(r + 1).charAt(c + 1) == '1') {
                    return dp[r][c][d] = 1;
                }                
                return dp[r][c][d] = calculate(r + 1, c + 1, d);
        }

        return dp[r][c][d];
    }
}
