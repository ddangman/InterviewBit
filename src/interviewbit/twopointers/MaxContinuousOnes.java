/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MaxContinuousOnes {

    public ArrayList<Integer> maxone(ArrayList<Integer> a, int b) {
        // store indices of zeros
        ArrayList<Integer> zero = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == 0) {
                zero.add(i);
            }
        }

        // flips exceed zeros, return entire array
        if (zero.size() <= b) {
            return result(0, a.size());
        }

        int i = 0; // points to last zero omitted 
        int j = b; // points to last zero flipped to one
        int max = zero.get(j++);
        int start = 0;
        int end = max;
        while (j < zero.size()) {
            // calculate longest run of zeros
            int n = zero.get(j) - (zero.get(i) + 1);
            if (n > max) {
                max = n;
                start = zero.get(i) + 1;
                end = zero.get(j);
            }
            j++;
            i++;
        }

        // check final run on ones after final zero
        int n = a.size() - (zero.get(i) + 1);
        if (n > max) {
            start = zero.get(i) + 1;
            end = a.size();
        }
        return result(start, end);
    }

    public ArrayList<Integer> result(int start, int end) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = start; i < end; i++) {
            al.add(i);
        }
        return al;
    }
}
