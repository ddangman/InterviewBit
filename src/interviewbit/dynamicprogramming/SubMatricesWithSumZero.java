/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class SubMatricesWithSumZero {

    public int solve(ArrayList<ArrayList<Integer>> a) {
        if (a.isEmpty()) {
            return 0;
        }
        int rows = a.size();
        int col = a.get(0).size();
        int[][] dp = new int[rows + 1][col + 1];

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= col; c++) {
                int get = a.get(r - 1).get(c - 1);
                dp[r][c] = get - dp[r - 1][c - 1] + dp[r][c - 1] + dp[r - 1][c];
            }
        }


        int count = 0;
        for (int rs = 0; rs <= rows; rs++) {
            for (int cs = 0; cs <= col; cs++) {
                for (int re = rs + 1; re <= rows; re++) {
                    for (int ce = cs + 1; ce <= col; ce++) {
                        int sum = dp[re][ce] + dp[rs][cs] - dp[rs][ce] - dp[re][cs];
                        if (sum == 0) {
                            count++;
                        } 
                    }
                }
            }
        }
        return count;
    }
}
