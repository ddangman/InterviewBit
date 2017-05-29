/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

/**
 *
 * @author Dang
 */
public class SwapListNodesInPairs {

    public ListNode swapPairs(ListNode a) {
        ListNode chain = new ListNode(0);
        ListNode primer = chain;
        ListNode m;
        ListNode n;
        while (a != null && a.next != null) {
            m = a;
            n = a.next;
            a = a.next.next;
            n.next = m;
            chain.next = n;
            chain = m;
        }
        chain.next = a;
        return primer.next;
    }
}
