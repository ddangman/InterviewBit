/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

/**
 *
 * @author Dang
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode a) {
        // initialize pointers
        ListNode tail = null;
        ListNode current = a;
        ListNode next;

        while (current != null) {
            next = current.next; // advance pointer
            current.next = tail; // assign link
            tail = current; // advance pointer
            current = next; // advance pointer
        }
        return tail;
    }

    public ListNode reverseBetween(ListNode a, int m, int n) {
        if (m == n) {
            return a;
        } else if (m == 1) {
            return reverseHead(a, n);
        }        

        ListNode beforeM = a; // node before m
        int indexM = 1; // index count m
        while (indexM + 1 < m) {
            beforeM = beforeM.next;
            indexM++;
        }

        ListNode atN = beforeM.next; // node at n
        int indexN = indexM + 1;
        while (indexN != n) {
            atN = atN.next;
            indexN++;
        }

        // cut and rotate
        ListNode parse = beforeM.next;
        beforeM.next = atN;
        ListNode willConnect = parse;
        ListNode connectHere = atN.next;

        // reverse direction of links
        ListNode tail = parse;
        parse = parse.next;

        ListNode next = parse.next;
        while (parse != connectHere) {
            next = parse.next;
            parse.next = tail;
            tail = parse;
            parse = next;
        }

        // reconnect
        willConnect.next = connectHere;

        return a;
    }
    
    public ListNode reverseHead(ListNode a, int n) {
        int count = 1;
        ListNode tail = a;
        ListNode parse = a.next;
        ListNode next;
        while (count < n) {
            next = parse.next;
            parse.next = tail;
            tail = parse;
            parse = next;
            count++;
        }
        // connect head 
        a.next = parse;
        return tail;
    }
}
