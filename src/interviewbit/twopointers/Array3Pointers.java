/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.List;

/**
 *
 * @author Dang
 */
public class Array3Pointers {

    public int minimize(final List<Integer> a, final List<Integer> b, final List<Integer> c) {
        int minfx = Integer.MAX_VALUE;
        int ap = a.size() - 1;
        int bp = b.size() - 1;
        int cp = c.size() - 1;
        
        while (ap >= 0 && bp >= 0 && cp >= 0) {
            int maxe = Math.max(a.get(ap), Math.max(b.get(bp), c.get(cp)));
            int minwh = Math.max(Math.abs(a.get(ap) - b.get(bp)),
                    Math.max(Math.abs(b.get(bp) - c.get(cp)), 
                            Math.abs(c.get(cp) - a.get(ap))));
            minfx = Math.min(minfx, minwh);
            
            if (maxe == a.get(ap)) {
                ap--;
            } else if (maxe == b.get(bp)) {
                bp--;
            } else if (maxe == c.get(cp)) {
                cp--;
            }            
        }
        return Math.abs(minfx);        
    }
}
