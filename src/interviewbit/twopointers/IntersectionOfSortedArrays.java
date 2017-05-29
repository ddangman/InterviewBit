/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dang
 */
public class IntersectionOfSortedArrays {

    public ArrayList<Integer> intersect(final List<Integer> a, final List<Integer> b) {
        ArrayList<Integer> matches = new ArrayList<>();
        int ap = 0;
        int bp = 0;
        while (ap < a.size() && bp < b.size()) {
            // failed when comparing Objects using ==
            if (a.get(ap).equals(b.get(bp))) {
                matches.add(a.get(ap));
                ap++;
                bp++;
            } else if (a.get(ap) > b.get(bp)) {
                bp++;
            } else {
                ap++;
            }
        }
        return matches;
    }
}
