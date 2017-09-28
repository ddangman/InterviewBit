/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import interviewbit.datastructures.ListNode;
import java.util.Stack;

/**
 *
 * @author Dang
 */
public class ReverseUsingRecursion {

    public ListNode reverseList(ListNode a) {
        Stack<ListNode> stack = new Stack<>();
        while (a != null) {
            stack.push(a);
            a = a.next;
        }

        ListNode chain = new ListNode(0);
        ListNode primer = chain;
        while (!stack.isEmpty()) {
            chain.next = stack.pop();
            chain = chain.next;
        }
        chain.next = null;
        return primer.next;
    }
}
