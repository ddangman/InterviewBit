/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class ExcelColumnTitle {

    public String convertToTitle(int a) {
        ArrayList<Character> cAr = new ArrayList<Character>();
        while (a > 0) {
            char c = (char) (a % 26 - 1 + 'A');
            // adjust for 'Z' roll over
            if (c < 'A') {
                c = 'Z';
                a--;
            }            
            cAr.add(c);
            a /= 26;
        }
        Collections.reverse(cAr);
        return getStringRepresentation(cAr);
    }

    String getStringRepresentation(ArrayList<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for (Character ch : list) {
            builder.append(ch);
        }
        return builder.toString();
    }
}
