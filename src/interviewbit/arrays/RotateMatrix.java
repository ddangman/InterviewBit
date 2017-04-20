/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import interviewbit.InterviewBit;
import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class RotateMatrix {

    public void rotate(ArrayList<ArrayList<Integer>> a) {
        int left = 0;
        int top = 0;
        int bottom = a.size() - 1;
        int right = a.get(0).size() - 1;

        while (left <= right) {
            for (int i = 0; i < right - left; i++) {
                // top-left value
                int n = a.get(top).get(left + i);

                // top-left from bottom-left
                a.get(top).set(left + i, a.get(bottom - i).get(left));
                //bottom-left from bottom-right
                a.get(bottom - i).set(left, a.get(bottom).get(right - i));
                // bottom-right from top-right
                a.get(bottom).set(right - i, a.get(top + i).get(right));
                // top-right from temp(top-left)
                a.get(top + i).set(right, n);

            }
            left++;
            right--;
            top++;
            bottom--;
        }
    }

}
