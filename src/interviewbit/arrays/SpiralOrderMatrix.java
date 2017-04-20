/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class SpiralOrderMatrix {

    public enum DIR {
        UP, DOWN, LEFT, RIGHT;
    }

    public ArrayList<ArrayList<Integer>> generateMatrix(int a) {
        ArrayList<ArrayList<Integer>> spiral = new ArrayList<>();
        
        for (int i = 0; i < a; i++) {
            spiral.add(new ArrayList<Integer>(Collections.nCopies(a, 0)));
        }
        int sq = a * a;
        int c = 1;
        DIR d = DIR.RIGHT;
        int left = 0;
        int top = 0;
        int bottom = a;
        int right = a;
        while (c <= sq) {
            if (d == DIR.RIGHT) {
                for (int i = left; i < right; i++) {
                    spiral.get(top).set(i, c);
                    c++;
                }
                d = DIR.DOWN;
                top++;
            } else if (d == DIR.LEFT) {
                for (int i = right - 1; i >= left; i--) {
                    spiral.get(bottom - 1).set(i, c);
                    c++;
                }
                d = DIR.UP;
                bottom--;

            } else if (d == DIR.DOWN) {
                for (int i = top; i < bottom; i++) {
                    spiral.get(i).set(right - 1, c);
                    c++;
                }
                d = DIR.LEFT;
                right--;

            } else if (d == DIR.UP) {
                for (int i = bottom - 1; i >= top; i--) {
                    spiral.get(i).set(left, c);
                    c++;
                }
                d = DIR.RIGHT;
                left++;
            }
        }
        return spiral;
    }
}
