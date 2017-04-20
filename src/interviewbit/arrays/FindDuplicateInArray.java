/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Dang
 */
public class FindDuplicateInArray {

    public int repeatedNumber(final List<Integer> a) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i : a) {
            if (hs.contains(i)) {
                return i;
            } else {
                hs.add(i);
            }           
        }
        return -1;
    }

}
