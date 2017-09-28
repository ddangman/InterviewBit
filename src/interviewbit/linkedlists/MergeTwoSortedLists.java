/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import interviewbit.datastructures.ListNode;

/**
 *
 * @author Dang
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode chain = new ListNode(0);
        ListNode primer = chain;
        while (a != null || b != null) {
            if (a == null) {
                chain.next = b;
                b = b.next;
            } else if (b == null) {
                chain.next = a;
                a = a.next;
            } else {
                if (a.val < b.val) {
                    chain.next = a;
                    a = a.next;
                } else {
                    chain.next = b;
                    b = b.next;
                }
            }
            chain = chain.next;
        }
        chain.next = null;
        return primer.next;
    }
}
