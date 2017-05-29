/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

/**
 *
 * @author Dang
 */
public class InsertionSortList {

    public ListNode insertionSortList(ListNode a) {
        // initialize pointers
        ListNode primer = new ListNode(Integer.MIN_VALUE);
        primer.next = a;
        ListNode sortLine = a.next;
        ListNode smaller = primer;
        ListNode larger = a;
        ListNode p2sl = a; // points to sort line

        while (sortLine != null) {
            if (smaller.next.val >= sortLine.val) {
                // insertion sort
                larger = smaller.next;
                smaller.next = sortLine;
                sortLine = sortLine.next;
                smaller.next.next = larger;
                p2sl.next = sortLine;
                
                smaller = primer; // reset smaller
            } else {
                // advance smaller until it meets sortLine
                smaller = smaller.next;
                if (smaller.next == sortLine) {
                    p2sl = sortLine;
                    sortLine = sortLine.next;
                    smaller = primer;
                }
            }
        }

        return primer.next;
    }
}
