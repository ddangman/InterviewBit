/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class NextPermutation {

    public void nextPermutation(ArrayList<Integer> a) {
        if (a.size() < 2) {
            return;
        }    
        
        // find first non-ascending number from right
        // mark its index for swap
        int index = a.size() - 2;
        for (; index > 0; index--) {
            if (a.get(index) < a.get(index + 1)) {
                break;
            }
        }
        
        // reverse sort
        if (index == 0) {
            reverse(a, index);
            return;
        } else {
            int swap = a.get(index); // marked for swap
            int end = a.size()-1;
            while(index<=end){
                // find first value greater than swap
                // to minimize increased permutation
                if (a.get(end)>swap) {
                    break;
                }                
                end--;
            }
            // swap
            a.set(index, a.get(end));
            a.set(end, swap);
            
            // reverse list right of index
            // so rightward of index is in increasing order
            reverse(a, index+1);
        }        
    }

    private void reverse(ArrayList<Integer> list, int start) {
        int left = start;
        int right = list.size()-1;
        while (left < right) {
            int temp = list.get(right);
            list.set(right, list.get(left));
            list.set(left, temp);
            left++;
            right--;
        }
    }
}
