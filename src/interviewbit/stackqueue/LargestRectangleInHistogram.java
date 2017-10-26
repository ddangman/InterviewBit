/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Dang
 */
public class LargestRectangleInHistogram {

    private int maxArea;

    public int largestRectangleArea(ArrayList<Integer> a) {
        // stack contains indices of increasing heights
        Stack<Integer> stack = new Stack<>();
        maxArea = 0;

        int i = 0;
        while (i < a.size()) {
            if (stack.isEmpty() || a.get(stack.peek()) <= a.get(i)) {
                stack.push(i++);
            } else {
                // 'i' does not increment here so it loops
                findArea(a, stack, i);
            }
        }

        // calculate area for remainder of stack
        while (!stack.isEmpty()) {
            findArea(a, stack, i);
        }
        return maxArea;
    }

    // 'i' is explored length of array 'a'
    private void findArea(ArrayList<Integer> a, Stack<Integer> stack, int i) {
        int area = 0;
        int height = a.get(stack.pop()); // greatest height
        if (stack.isEmpty()) {
            // height is shortest when stack is empty
            // so it is safe to multiply shortest height with entire explored base
            area = height * i;
        } else {
            // stack.peek is shorter than 'height' so its range must be excluded
            area = height * (i - stack.peek() - 1);
        }
        if (area > maxArea) {
            maxArea = area;
        }
    }
}
