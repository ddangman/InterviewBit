/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class SortedInsertPosition {

    public int searchInsert(ArrayList<Integer> a, int b) {
        int start = 0;
        int end = a.size() - 1;
        int mid = 0;
        while (start <= end) {
            mid = start + ((end - start) / 2);
            // comparisons
            if (a.get(start) >= b) {
                return start;
            } else if (a.get(mid) == b) {
                return mid;
            } else if (a.get(end) == b) {
                return end;
            } else if (a.get(end) < b) {
                return end + 1;
            }
            
            // reduce search indices if not at solution
            if (a.get(mid) < b) {
                if (mid + 1 < a.size()) {
                    if (b < a.get(mid + 1)) {
                        return mid + 1;
                    }                    
                }                
                start = mid + 1;
            } else {
                if (mid > 0) {
                    if (a.get(mid-1) < b) {
                        return mid;
                    }                    
                }                
                end = mid - 1;
            }           
        }
        return mid;
    }
}
