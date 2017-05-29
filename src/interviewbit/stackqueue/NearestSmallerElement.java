/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 *
 * @author Dang
 */
public class NearestSmallerElement {
    
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> arr) {
        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> qi = new ArrayDeque<>();
        for (int i = 0; i < arr.size(); i++) {
            if (qi.isEmpty()) {
                res.add(-1);
                qi.add(i);
            } else if (arr.get(i) < arr.get(qi.getLast())) {
                qi.clear();
                qi.add(i);
                res.add(-1);
            } else if (arr.get(i) > arr.get(qi.getFirst())) {
                res.add(arr.get(qi.getFirst()));
                qi.addFirst(i);
            } else {
                // pop until we get a smaller integer
                while (true) {
                    int pop = qi.pop();
                    int n = arr.get(pop);
                    if (n < arr.get(i)) {
                        res.add(n);
                        qi.addFirst(pop); // put pop back into stack
                        qi.addFirst(i);
                        break;
                    } else if (qi.isEmpty()) {
                        res.add(-1);
                        qi.add(i);
                        break;
                    }                 
                }
            }            
        }
        return res;
    }
}
