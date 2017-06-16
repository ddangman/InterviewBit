/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

/**
 *
 * @author Dang
 */
public class GridUniquePaths {

    public int uniquePaths(int a, int b) {
        int[][] matrix = new int[a+1][b+1];
        for (int i = 0; i < a; i++) {
            matrix[i][0]=1;
        }
        for (int i = 0; i < b; i++) {
            matrix[0][i]=1;
        }
        for (int r = 1; r <= a; r++) {
            for (int c = 1; c <= b; c++) {
                matrix[r][c]= matrix[r-1][c] + matrix[r][c-1];
            }
        }
        return matrix[a-1][b-1];
    }
}
