/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MergeTwoSortedLists {

    public void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> c = new ArrayList<>();
        c.addAll(a);
        a.clear();

        int bp = 0;
        int cp = 0;
        while (cp < c.size() && bp < b.size()) {
            if (b.get(bp) > c.get(cp)) {
                a.add(c.get(cp++));
            } else {
                a.add(b.get(bp++));
            }
        }

        while (cp < c.size()) {
            a.add(c.get(cp++));
        }
        while (bp < b.size()) {
            a.add(b.get(bp++));
        }
    }
}
