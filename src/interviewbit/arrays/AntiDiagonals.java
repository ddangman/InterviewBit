/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class AntiDiagonals {

    public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> a) {
        ArrayList<ArrayList<Integer>> ad = new ArrayList<>();

        // traverse horizontally
        for (int i = 0; i < a.size(); i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = i; j >= 0; j--) {
                row.add(a.get(i - j).get(j));
            }
            ad.add(row);
        }

        // traverse vertically
        for (int i = 1; i < a.size(); i++) {
            ArrayList<Integer> row = new ArrayList<>();
            int j = a.size()-1;
            int k = i;
            for ( ; j >= i; j--, k++) {
                row.add(a.get(k).get(j));
            }
            ad.add(row);
        }

        return ad;
    }
}
