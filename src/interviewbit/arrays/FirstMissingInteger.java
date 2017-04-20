/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 *
 * 
 */
public class FirstMissingInteger {

    public int firstMissingPositive(ArrayList<Integer> a) {
        for (int i = 0; i < a.size(); i++) {
            
            // ignore correct value
            // ith element == i + 1
            while (a.get(i) != i + 1){ // bucket sort until correct or break
                
                // ignore invalid numbers:
                // 0, negatives, greater than array size
                if (a.get(i) > a.size() || a.get(i) <= 0) {
                    break;
                }
                
                // swap current value with its correct index
                int n = a.get(i);
                if (a.get(n - 1) == n) { // duplicate
                    break; // break infinite loop
                }                
                a.set(i, a.get(n - 1));
                a.set(n - 1, n); // n belongs at index n-1
            }
        }
        
        // find first incorrect index
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != i + 1) {
                return i + 1;
            }            
        }
        
        // all values are correct, return next integer after array
        return a.size() + 1;
    }
}
