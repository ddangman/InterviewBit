/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.greedy;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class Bulbs {

    public int bulbs(ArrayList<Integer> a) {
        boolean flipped = false;
        int flips = 0;
        
        // traverse array flipping zeros
        // since all values right of switch also gets flipped,
        // track whether they are currently flipped or not using boolean flipped
        for (int i = 0; i < a.size(); i++) {
            int x = flipped ? a.get(i) ^ 1 : a.get(i);
            if (x == 0) {
                flips++;
                flipped = !flipped;
            }            
        }
        
        return flips;
    }
}
