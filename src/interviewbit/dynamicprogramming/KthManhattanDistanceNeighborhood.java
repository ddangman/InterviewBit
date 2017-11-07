/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 * Given a matrix M of size n*m and an integer K, 
 * find the maximum element in the K Manhattan distance neighborhood for all elements in n*m matrix. 
 * In other words, for every element M[i][j] find the maximum element M[p][q] such that abs(i-p)+abs(j-q) <= K
 * @author Duy Dang
 */
public class KthManhattanDistanceNeighborhood {

    public ArrayList<ArrayList<Integer>> solve(int kmax, ArrayList<ArrayList<Integer>> mat) {
        int row = mat.size();
        int col = mat.get(0).size();
        int[][][] dp = new int[kmax + 1][row + 2][col + 2];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[0][i + 1][j + 1] = mat.get(i).get(j);
            }
        }

        // for each new layer, take max of previous layer's down, up, left, right, center
        // dp[k+1][i][j] = max(dp[k][i-1][j], dp[k][i+1][j], dp[k][i][j-1], dp[k][i][j+1], dp[k][i][j] )
        for (int k = 1; k <= kmax; k++) {
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    int maxi = Math.max(dp[k - 1][i - 1][j], dp[k - 1][i + 1][j]);
                    int maxj = Math.max(dp[k - 1][i][j - 1], dp[k - 1][i][j + 1]);
                    int maxij = Math.max(maxi, maxj);
                    dp[k][i][j] = Math.max(maxij, dp[k - 1][i][j]);
                }
            }
        }
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= row; i++) {
            ArrayList<Integer> build = new ArrayList<>();
            for (int j = 1; j <= col; j++) {
                build.add(dp[kmax][i][j]);
            }
            result.add(build);
        }
        return result;
    }
}
