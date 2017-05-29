/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

/**
 *
 * @author Dang
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pointer = head;
        int count = 1;
        while (pointer.next != null) {
            pointer = pointer.next;
            count++;
        }

        pointer = head;
        int half = count / 2;
        int halfCount = 0;
        ListNode left = head;
        ListNode right = null;
        while (pointer.next != null) {
            halfCount++;
            if (halfCount == half) {
                right = pointer.next;
                pointer.next = null;
                continue;
            }
            pointer = pointer.next;
        }

        // recursively sort the two halves
        ListNode h1 = sortList(left);
        ListNode h2 = sortList(right);

        // merge sort both halves
        ListNode merged = merge(h1, h2);
        return merged;
    }

    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode primer = new ListNode(0);
        ListNode pointer = primer;
        while (h1 != null || h2 != null) {
            if (h1 == null) {
                pointer.next = h2;
                h2 = h2.next;
            } else if (h2 == null) {
                pointer.next = h1;
                h1 = h1.next;
            } else {
                if (h1.val < h2.val) {
                    pointer.next = h1;
                    h1 = h1.next;
                } else {
                    pointer.next = h2;
                    h2 = h2.next;
                }
            }
            pointer = pointer.next;
        }
        return primer.next;
    }

}
