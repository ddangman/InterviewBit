/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import interviewbit.datastructures.ListNode;

/**
 *
 * @author Dang
 */
public class RemoveDuplicatesFromSortedList {

    public ListNode leaveOneDuplicate(ListNode a) {
        ListNode it = a;
        ListNode run;
        OUTER:
        while (it.next != null) {
            if (it.val == it.next.val) {
                run = it.next;
                while (it.val == run.val) {
                    if (run.next == null) {
                        it.next = null;
                        break OUTER;
                    }
                    run = run.next;
                }
                it.next = run;
            }
            it = it.next;
        }
        return a;
    }

    public ListNode deleteAllDuplicates(ListNode a) {
        ListNode chain = new ListNode(0);
        chain.next = a;
        ListNode primer = chain;
        ListNode forward = a;
        OUTER: while (forward.next != null) {
            if (forward.val == forward.next.val) {
                int remove = forward.val;
                while (forward.val == remove) {
                    forward = forward.next;
                    if (forward == null) {
                        chain.next = null;
                        break OUTER;
                    }                    
                }
                chain.next = forward;
            } else {
                forward = forward.next;
                chain = chain.next;
            }           
        }
        return primer.next;
    }
}
