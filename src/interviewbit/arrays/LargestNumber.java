/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Dang
 */
public class LargestNumber {

    public String largestNumber(final List<Integer> a) {
        List<String> s = new ArrayList<String>();
        for (int i : a) {
            s.add(Integer.toString(i));
        }
        Collections.sort(s, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s2.compareTo(s1);
            }
        });

        String remZero = String.join("", s);
        return remZero.replaceFirst("^0+(?!$)", "");
    }
}
