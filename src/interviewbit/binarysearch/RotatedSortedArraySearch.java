/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

import java.util.List;

/**
 *
 * @author Dang
 */
public class RotatedSortedArraySearch {

    // search for element in one pass, not duplicate compatible
    public int optimalSearch(final List<Integer> a, int b) {
        int left = 0;
        int right = a.size() - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (a.get(mid) == b) {
                return mid;
            } else if (a.get(left) == b) {
                return left;
            } else if (a.get(right) == b) {
                return right;
            } else if (a.get(left) <= a.get(mid)) {
                // left half is sorted
                if (a.get(left) < b && b < a.get(mid)) {
                    // element falls in left half
                    right = mid - 1;
                } else {
                    // element falls in right half
                    left = mid + 1;
                }
            } else {
                // right half is sorted
                if (a.get(mid + 1) < b && b < a.get(right)) {
                    // element falls in right half
                    left = mid + 1;
                } else {
                    // element falls in left half
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    // returns minimum number
    public int findMin(final List<Integer> a) {
        int left = 0;
        int right = a.size() - 1;
        int minIndex = 0;
        // list is not rotated
        if (a.get(right) > a.get(left)) {
            return a.get(minIndex);
        }
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (a.get(mid) < a.get(minIndex)) {
                minIndex = mid;
            }
            if (a.get(left) < a.get(minIndex)) {
                minIndex = left;
            } 
            if (a.get(right) < a.get(minIndex)) {
                minIndex = right;
            }            
            if (a.get(left) < a.get(mid)) {
                // left side is sorted
                // search right side for min
                left = mid + 1;
            } else {
                // right side is sorted
                // search left partition for min
                right = mid - 1;
            }
        }
        return a.get(minIndex);
    }
}
