/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Dang
 */
public class ReorderList {
    public ListNode reorderList(ListNode a) {
        Deque<ListNode> deck = new ArrayDeque<>();
        ListNode parse = a;
        while (parse.next != null) {
            deck.addLast(parse.next);
            parse = parse.next;
        }

        parse = a;
        boolean grabLast = true;
        while (!deck.isEmpty()) {
            if (grabLast) {
                parse.next = deck.pollLast();
            } else {
                parse.next = deck.pollFirst();
            }
            parse = parse.next;
            grabLast = !grabLast;
        }
        parse.next = null;
        
        return a;
    }
}
