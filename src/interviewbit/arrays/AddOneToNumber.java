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
public class AddOneToNumber {
    public ArrayList<Integer> plusOne(ArrayList<Integer> a) {
        Collections.reverse(a);
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == 9) {
                a.set(i, 0);
            } else {
                a.set(i, a.get(i) +1);
                break;
            }
            if (i+1 == a.size()) {
                a.add(1);
                break;
            }            
            
        }
        boolean leading = true;
        for (int i = a.size()-1; i >= 0; i--) {
            if (leading) {
                if (a.get(i) == 0) {
                    continue;
                } else{
                    leading = false;
                }               
            }            
            res.add(a.get(i));
        }
        return res;
    }
    
}
