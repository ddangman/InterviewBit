/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

/**
 *
 * @author Dang
 */
public class RemoveNthNodeFromListEnd {

    public ListNode removeNthFromEnd(ListNode a, int b) {
        int count = 1;
        ListNode parse = a;
        while (parse.next != null) {
            count++;
            parse = parse.next;
        }

        if (b >= count || a.next == null) {
            return a.next;
        }

        parse = a;
        int findCount = 1;
        int limit = count - b;
        while (findCount < limit) {
            parse = parse.next;
            findCount++;
        }
        if (parse.next == null || parse.next.next == null) {
            parse.next = null;
        } else {
            parse.next = parse.next.next;
        }

        return a;
    }
}
