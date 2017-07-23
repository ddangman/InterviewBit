/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.List;

/**
 *
 * @author Dang
 */
public class LongestIncreasingSubsequence {

    private int n;
    private int length;
    private List<Integer> input;

    public int lis(final List<Integer> input) {
        n = input.size();
        if (n <= 1) {
            return n;
        }
        this.input = input;
        
        return getLISlength();
    }
    
    private int getLISlength() {
        length = 0;
        int[] util = new int[n];
        int[] lis = new int[n];
        util[0] = input.get(0);
        lis[0] = 1;
        
        for (int i = 1; i < n; i++) {
            int x = input.get(i);
            if (x > util[length]) {
                util[++length] = x;
                lis[i] = length + 1; 
            } else {
                int index = biSearchIndex(util, x);
                util[index] = x;
                lis[i] = index + 1;
            }           
        }
        
        return length + 1;
    }
    
    private int biSearchIndex(int[] util, int x) {
        int start = 0;
        int end = length;
        int mid;
        
        while (start <= end) {
            mid = (start + end) / 2;
            if (util[mid] == x) {
                return mid;
            } else if (util[mid] > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }           
        }
        
        return start;
    }
}
