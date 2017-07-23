/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Dang
 */
public class Seats {

    public int seats(String input) {
        int i = 0, j = 0, n = input.length();
        // add all groups to deque
        Deque<int[]> dq = new ArrayDeque<>();

        while (j < n) {
            // skip empty seats
            while (j < n && input.charAt(j) != 'x') {
                j++;
            }
            if (j == n) {
                break;
            }

            // group filled seats
            i = j;
            while (j < n && input.charAt(j) == 'x') {
                j++;
            }
            // add group {start, end} to deque
            dq.add(new int[]{i, j});
        }
        
        
        long moves = 0;
        // push groups together from both ends
        // don't process last item since median group does not need to be moved
        while (dq.size() > 1) { 
            int[] left = dq.peekFirst();
            int[] right = dq.peekLast();
            
            int leftSize = left[1] - left[0]; // left size
            int rightSize = right[1] - right[0]; // right size
            
            // push smaller group towards center
            if (leftSize < rightSize) {
                // merge left two groups
                left = dq.pollFirst(); // remove from queue
                // add moves to count
                // size of group * distance to next group
                moves += leftSize * (dq.peekFirst()[0] - left[1]);
                // expand start to include group removed from queue
                dq.peekFirst()[0] -= leftSize; 
            } else {
                // merge right two groups
                right = dq.pollLast();
                moves += rightSize * (right[0] - dq.peekLast()[1]);
                dq.peekLast()[1] += rightSize;
            }           
            if (moves > 10000003) {
                moves %= 10000003;
            }            
        }
        
        return (int) moves;
    }
}
