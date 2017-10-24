/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

import java.util.List;

/**
 *
 * @author Dang
 */
public class CountElementOccurence {

    public int findCount(final List<Integer> a, int b) {
        int start = findFirst(a, b, a.size() - 1);
        
        if (start == -1) {
            return 0;
        } 
        
        int end = findLast(a, b, start);
        return end - start + 1;
    }

    public static int findLast(final List<Integer> a, int b, int start) {
        int end = a.size() - 1;
        int mid = 0;
        int index = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (a.get(mid) == b) {
                index = mid;
                start = mid + 1;
            } else if (a.get(mid) < b){
                start = mid + 1;
            } else {
                end = mid - 1;
            }           
        }
        return index;
    }

    public static int findFirst(final List<Integer> a, int b, int end) {
        int start = 0;
        int mid = 0;
        int index = -1;
        while (start <= end) {
        while (start <= end) {
            mid = (start + end) / 2;
            if (a.get(mid) == b) {
                index = mid;
                end = mid - 1;
            } else if (a.get(mid) < b){
                start = mid + 1;
            } else {
                end = mid - 1;
            }           
        }
        }
        return index;
    }

}
