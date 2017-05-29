/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class NQueens {

    ArrayList<ArrayList<String>> allSolutions = new ArrayList<>();

    public ArrayList<ArrayList<String>> solveNQueens(int n) {
        int[] positions = new int[n];
        findPositions(positions, 0, n);

        return allSolutions;
    }

    public boolean findPositions(int[] positions, int i, int n) {
        // solution found
        if (i == n) {
            saveSolution(positions, n);
            return true;
        }

        for (int j = 0; j < n; j++) {
            positions[i] = j;
            if (isSafe(positions, i, n)) {
                findPositions(positions, i + 1, n);
            }
        }
        // no possible solution
        return false;
    }

    public boolean isSafe(int[] positions, int i, int n) {
        for (int j = 0; j < i; j++) {
            // check vertical
            if (positions[i] == positions[j]) {
                return false;
            }
            // check diagonals
            if (positions[i] == positions[j] + (i - j)) {
                return false;
            }
            if (positions[i] == positions[j] - (i - j)) {
                return false;
            }
        }
        return true;
    }

    public void saveSolution(int[] positions, int n) {
        ArrayList<String> solved = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (positions[i] == j) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            solved.add(sb.toString());
        }
        allSolutions.add(solved);
    }
}
