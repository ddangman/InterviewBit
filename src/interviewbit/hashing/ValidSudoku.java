/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.List;

/**
 *
 * @author Dang
 */
public class ValidSudoku {

    public int isValidSudoku(final List<String> a) {
        boolean[][] ver = new boolean[9][9]; // tracks vertical numbers
        boolean[][] hor = new boolean[9][9]; // tracks horizontal numbers
        // tracks square numbers with row keys
        // 123
        // 456
        // 789
        boolean[][] sq = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            String parse = a.get(i);
            for (int j = 0; j < 9; j++) {
                int n = parse.charAt(j) - '0' - 1;
                if (n >= 0) {
                    // check vertical & horizontal
                    if (ver[i][n] || hor[j][n]) {
                        return 0;
                    }
                    // calculate square
                    int b = j / 3;
                    b += (3 * (i / 3));
                    if (sq[b][n]) {
                        return 0;
                    }

                    // flag used numbers
                    ver[i][n] = hor[j][n] = sq[b][n] = true;
                }
            }
        }
        return 1;
    }
}
