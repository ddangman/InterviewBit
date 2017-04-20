/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class SetMatrixZeros {

    public void setZeroes(ArrayList<ArrayList<Integer>> a) {
        boolean[] yb = new boolean[a.size()];
        boolean[] xb = new boolean[a.get(0).size()];

        // iterate all O(xy)
        for (int y = 0; y < a.size(); y++) {
            for (int x = 0; x < a.get(0).size(); x++) {
                if (a.get(y).get(x) == 0) {
                    yb[y] = true;
                    xb[x] = true;
                }
            }
        }

        // convert all y
        for (int y = 0; y < a.size(); y++) {
            if (yb[y]) {
                for (int x = 0; x < a.get(0).size(); x++) {
                    a.get(y).set(x, 0);
                }
            }
        }

        // convert x, skipping y
        for (int x = 0; x < a.get(0).size(); x++) {
            if (xb[x]) {
                for (int y = 0; y < a.size(); y++) {
                    if (!yb[y]) {
                        a.get(y).set(x, 0);
                    }
                }
            }

        }
    }
}
