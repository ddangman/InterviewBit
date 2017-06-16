/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.heapmap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Dang
 */
public class DistinctNumbersInWindow {

    public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
        ArrayList<Integer> solution = new ArrayList<>();
        // holds unique numbers and count
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // grow window
        int i;
        for (i = 0; i < B; i++) {
            int n = A.get(i);
            map.compute(n, (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }               
            });
        }
        
        // move window
        int d = 0;
        for (; i < A.size(); i++, d++) {
            // add unique number count to solution
            solution.add(map.size());
            
            // drop previous number from window
            int drop = A.get(d);
            int count = map.get(drop);
            if (count == 1) {
                map.remove(drop);
            } else {
                map.put(drop, count - 1);
            }   
            
            // add next number to window
            int add = A.get(i);
            map.compute(add, (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }               
 
            });
        }
        solution.add(map.size());
        
        return solution;
    }
}
