/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author Dang
 */
public class SlidingWindowMax {

    public ArrayList<Integer> slidingMaximum(final List<Integer> a, int b) {
        ArrayList<Integer> mar = new ArrayList<>();
        // stores indices of useful array elements
        // front to rear is sorted in decreasing order
        Deque<Integer> qi = new ArrayDeque<>();

        // process first window
        int i = 0;
        for (; i < b; i++) {
            // if current element is greater than qi rear, pollLast
            while (!qi.isEmpty() && a.get(i) >= a.get(qi.peekLast())) {
                qi.pollLast();
            }

            // add new element to qi rear
            qi.addLast(i);
        }

        // process rest of elements
        for (; i < a.size(); i++) {
            // element at front of qi is largest element of previous window
            mar.add(a.get(qi.peekFirst()));

            // remove elements that are out of window
            while (!qi.isEmpty() && qi.peekFirst() <= i - b) {
                qi.pop();
            }

            // remove all elements smaller than/equal to current
            while (!qi.isEmpty() && a.get(qi.peekLast()) <= a.get(i)) {
                qi.pollLast();
            }

            // add current element to rear of qi
            qi.add(i);
        }
        // add last window
        mar.add(a.get(qi.pop()));

        return mar;
    }

}
