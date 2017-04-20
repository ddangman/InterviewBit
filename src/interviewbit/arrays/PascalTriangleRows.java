/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class PascalTriangleRows {

    public ArrayList<ArrayList<Integer>> generate(int a) {
        ArrayList<ArrayList<Integer>> pt = new ArrayList<ArrayList<Integer>>();
        if (a < 1) {
            return pt;
        }
        pt.add(new ArrayList<Integer>(Arrays.asList(1)));
        if (a == 1) {
            return pt;
        }
        pt.add(new ArrayList<Integer>(Arrays.asList(1, 1)));

        for (int i = 2; i < a; i++) {
            ArrayList<Integer> newRow = new ArrayList<Integer>();
            newRow.add(1);
            for (int j = 1; j < pt.get(i - 1).size(); j++) {
                newRow.add(pt.get(i - 1).get(j - 1) + pt.get(i - 1).get(j));
            }
            newRow.add(1);
            pt.add(newRow);
        }
        return pt;
    }
}
