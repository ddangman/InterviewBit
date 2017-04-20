/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class MaximumUnsortedSubarray {

    public ArrayList<Integer> subUnsort(ArrayList<Integer> A) {
        int start = 0;
        int end = A.size() - 1;

        // find index of first unsorted value
        for (int i = 1; i < A.size(); i++) {
            if (A.get(i - 1) > A.get(i)) {
                start = i;
                break;
            }
        }
        
        // array already sorted
        if (start == 0) {
            return new ArrayList<Integer>(Arrays.asList(-1));
        }        

        // find index of last unsorted value
        for (int i = A.size() - 2; i >= 0; i--) {
            if (A.get(i + 1) < A.get(i)) {
                end = i;
                break;
            }
        }

        // values to compare
        int comS = A.get(start);
        int comE = A.get(end);
        
        // limit range to avoid OutOfBoundsException
        int limS = start > 0 ? start - 1 : 0;
        int limE = end + 1 < A.size() ? end + 1 : end;
        for (int i = limS; i <= limE; i++) {
            if (A.get(i) < comS) {
                comS = A.get(i);
            }  
            if (A.get(i) > comE) {
                comE = A.get(i);
            }
        }

        // find indexes of unsorted subarray
        while (end + 1 < A.size()) {
            if (comE <= A.get(end + 1)) {
                break;
            }
            end++;
        }
        while (start - 1 >= 0) {
            if (A.get(start - 1) <= comS) {
                break;
            }
            start--;
        }


        return new ArrayList<Integer>(Arrays.asList(start, end));
    }

    public int binarySearchFirst(ArrayList<Integer> A, int target, int high) {
        int low = 0;
        int startIndex = -1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (A.get(mid) > target) {
                high = mid - 1;
            } else if (A.get(mid) == target) {
                startIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return startIndex;
    }

    public int binarySearchLast(ArrayList<Integer> A, int target, int low) {
        int endIndex = -1;
        low = 0;
        int high = A.size() - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (A.get(mid) > target) {
                high = mid - 1;
            } else if (A.get(mid) == target) {
                endIndex = mid;
                low = mid + 1;
            } else {
                low = mid + 1;
            }
        }
        return endIndex;
    }
}
