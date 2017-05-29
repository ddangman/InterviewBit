/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class CountingTriangles {

    public int nTriang(ArrayList<Integer> A) {
        Collections.sort(A);
        long count = 0;
        
        for (int i = 0; i < A.size() - 2; i++) {
            for (int j = i + 1; j < A.size() - 1; j++) {             
                int max = A.get(j) + A.get(i);
                int upper = biSearchUpper(A, max);
                if (upper == 0) {
                    continue;
                }                
                count += upper;
                count -= j;         
                if (count >= 1000000007) {
                    count %= 1000000007;
                }                
            }
        }
        return (int) count % 1000000007;
    }
    
    // returns index of all numbers less than max
    public int biSearchUpper(ArrayList<Integer> A, int max) {
        int start = 0;
        int end = A.size() - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (A.get(mid) < max) {
                if (mid == A.size() - 1) {
                    return mid;
                } 
                if (A.get(mid + 1) >= max) {
                    return mid;
                }                
                start = mid + 1; 
            } else {
                end = mid - 1;
            }           
        }
        return mid;
    }
    
    // return index of allNumbers >= min
    public int biSearchBottom(ArrayList<Integer> A, int min) {
        int start = 0;
        int absoluteStart = start;
        int end = A.size() - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (A.get(mid) >= min) {
                if (mid == absoluteStart) {
                    return mid;
                } 
                if (A.get(mid - 1) < min) {
                    return mid;
                } 
                end = mid - 1;
            }            
            else {
                start = mid + 1;
            }
        }
        return mid;
    }
}
