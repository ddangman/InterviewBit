/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Dang
 */
public class ColorfulNumber {

    public int colorful(int a) {
        if (a < 10) {
            return 1;
        }        
        ArrayList<Integer> ar = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        while (a > 0) {
            int i = a % 10;
            if (set.contains(i)) {
                return 0;
            }   
            set.add(i);
            ar.add(i);
            a/=10;
        }
        
        // calculate hash
        int i = 0;
        int j = 1;
        int n = ar.size() - 1;
        int hash = ar.get(i) * ar.get(j);
        if (set.contains(hash)) {
            return 0;
        }        
        boolean forward = true;
        while (i!=0 || j != n) {
            if (set.contains(hash)) {
                return 0;
            } else {
                set.add(hash);
            }        
            
            // conserve product by dividing out previous value and
            // multiplying in next value
            if (forward && j == n) {
                forward = !forward;
                hash *= ar.get(--i);
            } else if (!forward && i == 0) {
                forward = !forward;
                hash *= ar.get(++j);
            } else if (forward) {
                hash /= ar.get(i++);
                hash *= ar.get(++j);
            } else if (!forward) {
                hash /= ar.get(j--);
                hash *= ar.get(--i);
            }            
        }
        return 1;
    }
}
