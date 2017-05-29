package interviewbit;

import interviewbit.backtracking.Sudoku;
import java.util.ArrayList;
import java.util.Arrays;
import utilities.ArrayUtilities;

/**
 * Main class for running InterviewBit solutions.
 *
 * @author Dang
 */
public class InterviewBit {

    public static void main(String[] args) {
        ArrayList<String> toSud = new ArrayList<>(Arrays.asList(
                "53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79"));
        ArrayList<ArrayList<Character>> puzzle = ArrayUtilities.stringArrayToCharMatrix(toSud);

        Sudoku sdk = new Sudoku();
        sdk.solveSudoku(puzzle);
        for (ArrayList<Character> ca :puzzle) {
            System.out.println(ca);
        }

    }

}
