/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Dang
 */
public class SearchRange {

    public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
        int start = searchFirst(a, b);
        if (start == -1) {
            return new ArrayList<Integer>(Arrays.asList(-1,-1));
        }
        return new ArrayList<Integer>(Arrays.asList(start, 
                searchLast(a, b, start)));
    }
    
    public int searchFirst(final List<Integer> a, int key){
        int start = 0;
        int end = a.size() - 1;
        int mid = 0;
        int index = -1;
        while (start <=end) {
            mid = start + ((end - start) / 2);
            if (a.get(mid) ==key) {
                index = mid;
                end = mid - 1;
            } else if (a.get(mid) < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }          
        }
        return index;
    }
    
    public int searchLast(final List<Integer> a, int key, int start){
        int end = a.size() - 1;
        int mid = 0;
        int index = -1;
        while (start <= end) {
            mid = start + ((end - start) / 2);
            if (a.get(mid) == key) {
                index = mid;
                start = mid + 1;
            } else if (a.get(mid) < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }          
        }
        return index;
    }
}
