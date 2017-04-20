/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class WaveArray {

    public ArrayList<Integer> wave(ArrayList<Integer> a) {
        if (a.size() < 2) {
            return a;
        }
        Collections.sort(a);
        int i = 0;
        ArrayList<Integer> res = new ArrayList<>();
        for ( ; i + 1 < a.size(); i += 2) {
            res.add(a.get(i + 1)); // add higher
            res.add(a.get(i)); // add lower
        }
        if (a.size() % 2 == 1) {
            res.add(a.get(a.size()-1));
        }
        return res;
    }
}
