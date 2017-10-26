/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.greedy;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class HighestProduct {

    public int maxp3(ArrayList<Integer> a) {
        // return entire array as only product
        if (a.size() == 3) {
            return a.stream().reduce(1, (x, y) -> x * y);
        }  
        
        // initialize variables
        int top = Integer.MIN_VALUE;
        int mid = Integer.MIN_VALUE;
        int low = Integer.MIN_VALUE;
        int ntop = 0;
        int nmid = 0;
        
        for (int i = 0; i < a.size(); i++) {
            int n = a.get(i);
            
            // take max value and propagate value down
            if (n > top) {
                low = mid;
                mid = top;
                top = n;
            } else if (n > mid) {
                low = mid;
                mid = n;
            } else if (n > low) {
                low = n;
            }  
            
            // store lowest values in case they form greatest product
            if (n < ntop) {
                nmid = ntop;
                ntop = n;
            } else if (n < nmid) {
                nmid = n;
            }
           
        }
                
        // return greatest product
        if (top * nmid * ntop > top * mid * low) {
            return top * nmid * ntop;
        }        
        return top * mid * low;
    }
}
