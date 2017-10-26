/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dang
 */
public class SpiralPrintMatrix {

    private enum DIR {
        UP, DOWN, LEFT, RIGHT;
    }

    // DO NOT MODIFY THE LIST
    public ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> a) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int left = 0;
        int top = 0;
        int bottom = a.size();
        int right = a.get(0).size();
        DIR d = DIR.RIGHT;
        // Populate result;
        while (top < bottom && left < right) {
            if (d == DIR.RIGHT) {
                for (int i = left; i < right; i++) {
                    result.add(a.get(top).get(i));
                }
                d = DIR.DOWN;
                top++;
            } else if (d == DIR.LEFT) {
                for (int i = right - 1; i >= left; i--) {
                    result.add(a.get(bottom - 1).get(i));
                }
                d = DIR.UP;
                bottom--;

            } else if (d == DIR.DOWN) {
                for (int i = top; i < bottom; i++) {
                    result.add(a.get(i).get(right - 1));
                }
                d = DIR.LEFT;
                right--;

            } else if (d == DIR.UP) {
                for (int i = bottom - 1; i >= top; i--) {
                    result.add(a.get(i).get(left));
                }
                d = DIR.RIGHT;
                left++;
            }

        }
        return result;
    }
}
