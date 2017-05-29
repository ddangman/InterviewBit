/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class DiffK {

    public int diffPossible(ArrayList<Integer> a, int b) {
        for (int i = a.size() - 1; i >= 0; i--) {
            int diff = a.get(i) - b;
            int index = binarySearch(a, diff);
            if (index == -1) {
                continue;
            } else if (index != i) {
                return 1;
            } else if (index > 0 && a.get(index) == a.get(index - 1)) {
                return 1;
            } else if (index < a.size() - 1 && a.get(index) == a.get(index + 1)) {
                return 1;
            }
        }
        return 0;
    }

    public int binarySearch(ArrayList<Integer> a, int key) {
        int start = 0;
        int end = a.size() - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (a.get(mid) == key) {
                return mid;
            } else if (a.get(mid) > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
