/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MatrixMedian {

    public int findMedian(ArrayList<ArrayList<Integer>> A) {
        int r = A.size(); // row
        int c = A.get(0).size(); // column
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // go through rows to find min and max
        for (int i = 0; i < r; i++) {
            if (A.get(i).get(0) < min) {
                min = A.get(i).get(0);
            }
            if (A.get(i).get(c - 1) > max) {
                max = A.get(i).get(c - 1);
            }
        }

        // Half of matrix numbers must be less than median
        int half = (r * c + 1) / 2;
        int lowestMedian = max;
        
        while (min < max) {
            // subtract first to avoid bit overflow
            int median = min + ((max - min) / 2); // calculated median
            int count = 0; // reset count

            for (int i = 0; i < r; i++) {
                // count elements lower than calculated_median
                count += lessOrEqualCount(A.get(i), median);
            }

            // calculated_median is greater than or equal to actual median
            if (count >= half) {
                // reduce current max to find lowest valid calculated_median possible
                max = median;
                // keep lowest median found in matrix
                if (median < lowestMedian && findMedian(A, median)) {
                    lowestMedian = median;
                }
            } else {
                // calculated_median is not greater than half of matrix's elements
                min = median + 1;
            }
        }

        return lowestMedian;
    }

    public int lessOrEqualCount(ArrayList<Integer> arr, int key) {

        int start = 0;
        int end = arr.size() - 1;
        int mid = 0;
        while (start < end) { // final comparison done outside loop
            mid = start + ((end - start) / 2);           
            if (arr.get(mid) <= key) {
                // search after
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        // final comparison
        if (arr.get(start) <= key) {
            return start + 1;
        } else {
            return start;
        }       
    }

    public boolean findMedian(ArrayList<ArrayList<Integer>> matrix, int key) {
        int r = matrix.size(); // row
        int c = matrix.get(0).size() - 1; // column
        for (int i = 0; i < r; i++) {
            int start = 0;
            int end = c;
            int mid = 0;
            while (start <= end) { // final comparison done in loop
                mid = start + ((end - start) / 2);
                if (matrix.get(i).get(mid) == key) {
                    return true;
                }
                if (matrix.get(i).get(mid) > key) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return false;
    }
}
