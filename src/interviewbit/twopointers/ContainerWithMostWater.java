/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class ContainerWithMostWater {

    public int maxArea(ArrayList<Integer> a) {
        int best = 0; // best area
        int i = 0;
        int j = a.size() - 1;
        while (i < j) {
            // area = base * minimum height
            int current = (j - i) * Math.min(a.get(i), a.get(j));
            if (current > best) {
                best = current;
            }

            // reduce smaller height
            if (a.get(i) < a.get(j)) {
                i++;
            } else {
                j--;
            }           
        }
        return best;
    }
}
