/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import interviewbit.datastructures.ListNode;

/**
 *
 * @author Dang
 */
public class RotateList {

    public ListNode rotateRight(ListNode a, int b) {
        if (a.next == null) {
            return a;
        }

        int count = 1;
        ListNode parse = a;
        while (parse.next != null) {
            parse = parse.next;
            count++;
        }

        ListNode stop = a;
        ListNode head;
        int stopCount = 1;

        b = b % count; // more rotations than nodes
        if (b == 0) { // no rotations needed
            return a;
        }
        
        while (stopCount < count - b) {
            stop = stop.next;
            stopCount++;
        }

        head = stop.next;
        parse.next = a;
        stop.next = null;

        return head;
    }
}
