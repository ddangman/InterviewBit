/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0’s and 1’s, find the largest rectangle
 * containing all ones and return its area. Solution: form a histogram using the
 * lesser side of the matrix. 1's will increase histogram value by one, 0's will
 * reset height to zero. Calculate maximum histogram area after each update
 * loop.
 *
 * @author Dang
 */
public class MaxRectangleInBinaryMatrix {

    Stack<Integer> stack;
    int[] hist; // histogram
    int max;

    public int maximalRectangle(ArrayList<ArrayList<Integer>> input) {
        max = 0;
        int row = input.size();
        int col = input.get(0).size();
        boolean rgc = col < row; // is row greater than column?
        hist = new int[rgc ? col : row]; // histogram length is shortest side
        stack = new Stack<>(); // stacks increasing histogram indices

        for (int h = 0; h < (rgc ? row : col); h++) { // 'h'eight is longer side
            // inner loop should run 'w'idth of dp, 
            // which is the lesser side of input
            for (int w = 0; w < (rgc ? col : row); w++) {
                if (rgc) {
                    hist[w] = input.get(h).get(w) == 1 ? hist[w] + 1 : 0;
                } else {
                    hist[w] = input.get(w).get(h) == 1 ? hist[w] + 1 : 0;
                }
            }

            // maximum histogram area
            int i = 0;
            while (i < hist.length) {
                // add to stack so histogram height is non-decreasing
                if (stack.isEmpty() || hist[stack.peek()] <= hist[i]) {
                    stack.add(i++);
                } else {
                    popArea(i);
                }
            }
            while (!stack.isEmpty()) {
                popArea(i);
            }
        }

        return max;
    }

    // calculates maximum histogram area
    public void popArea(int i) {
        int pop = stack.pop();
        int area = hist[pop] * i; // assuming smallest height index was popped
        if (!stack.isEmpty()) { // correct for smaller height in stack
            // assumption was wrong, limit width by
            // subtracting next smaller height index (top of stack)
            area = hist[pop] * (i - stack.peek() - 1);
        }

        if (area > max) { // update maximum area
            max = area;
        }
    }
}
