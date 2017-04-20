/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class NobleInteger {
    public int solve(ArrayList<Integer> A) {
        Collections.sort(A);
        int count = 0;
        for (int i = A.size() - 1; i >= 0; i--, count++) {
            if (A.get(i) == count) {
                if (count == 0) { // edge case
                    // last value is 0
                    return 1;
                } else if (A.get(i+1) > count){
                    // check if next index is greater
                    return 1;
                }                
            }            
        }
        return -1;
    }
    
    
}
