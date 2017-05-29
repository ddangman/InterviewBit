/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import java.util.Stack;

/**
 *
 * @author Dang
 */
public class KReverseLinkedList {

    public ListNode reverseList(ListNode A, int B) {
        Stack<ListNode> stack = new Stack<>();
        ListNode chain = new ListNode(0);
        ListNode primer = chain;
        while (A != null) {
            if (stack.size() != B) {
                stack.add(A);
                A = A.next;
            } else {
                while (!stack.isEmpty()) {
                    chain.next = stack.pop();
                    chain = chain.next;
                }
            }
        }
        while (!stack.isEmpty()) {
            chain.next = stack.pop();
            chain = chain.next;
        }
        chain.next = null;
        return primer.next;
    }

}
