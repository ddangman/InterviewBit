/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.greedy;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class AssignMiceToHoles {

    public int mice(ArrayList<Integer> a, ArrayList<Integer> b) {
        Collections.sort(a);
        Collections.sort(b);
        int diff = 0;
        int d = 0;
        for (int i = 0; i < a.size(); i++) {
            d = a.get(i) - b.get(i);
            d = d < 0 ? d * -1 : d;
            if (d > diff) {
                diff = d;
            }            
        }
        return diff;
    }
}
