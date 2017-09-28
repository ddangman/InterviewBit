/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import interviewbit.datastructures.ListNode;

/**
 *
 * @author Dang
 */
public class Intersection {

    public ListNode getIntersectionNode(ListNode a, ListNode b) {
        int al = getLength(a);
        int bl = getLength(b);
        
        ListNode ta = a;
        ListNode tb = b;
        
        int diff = al - bl;
        while (diff < 0) {
            tb = tb.next;
            diff++;
        }
        while (diff > 0) {
            ta = ta.next;
            diff--;
        }
        
        while (ta != null) {
            if (ta == tb) {
                return ta;
            } 
            ta = ta.next;
            tb = tb.next;
        }

        return null;
    }
    
    public int getLength(ListNode ln) {
        int count = 0;
        while (ln != null) {
            count++;
            ln = ln.next;
        }
        return count;
    }
}
