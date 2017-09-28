/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import interviewbit.datastructures.ListNode;

/**
 *
 * @author Dang
 */
public class ListCycle {

    public ListNode detectCycle(ListNode a) {
        ListNode tortoise = a;
        ListNode hare = a;

        // move hare 2 steps for 1 step tortoise
        do {
            if (tortoise.next == null
                    || hare.next == null
                    || hare.next.next == null) {
                return null;
            }
            tortoise = tortoise.next;
            hare = hare.next.next;
            
        } while (tortoise != hare); // cycle found
        
        // reset tortoise
        tortoise = a;
        // move hare and tortoise 1 step each
        // next time they meet will be at beginning of cycle
        while (tortoise != hare) {
            tortoise = tortoise.next;
            hare = hare.next;
        }
        return tortoise;
    }
}
