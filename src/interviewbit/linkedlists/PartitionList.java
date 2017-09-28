/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import interviewbit.datastructures.ListNode;

/**
 *
 * @author Dang
 */
public class PartitionList {
    public ListNode partition(ListNode a, int b) {
        // initialize pointers
        ListNode lessPtr = a; // last element less than b
        ListNode behindParse = a;
        ListNode parse = a.next;
        // track pointer index for efficiency
        int lpi = 0;
        int pi = 0;    

        while (parse != null) {
            if (lessPtr.next == null) {
                break;
            } else if (lessPtr.next.val < b) {
                // advance lessPointer
                lessPtr = lessPtr.next;
                lpi++;
            } else if (pi < lpi) {
                // if parse is behind lessPointer, jump ahead
                parse = lessPtr.next;
                pi = lpi + 1;
            } else if (parse.val < b) {
                // reassign links
                behindParse.next = parse.next;
                parse.next = lessPtr.next;
                lessPtr.next = parse;

                // reassign pointers
                lessPtr = parse;
                parse = behindParse.next;

            } else {
                // advance parse
                if (parse.next == null) {
                    break;
                }
                behindParse = parse;
                parse = parse.next;
                pi++;
            }
        }
        // case where first element was not less than b and
        // lessPtr was initialized to point to second element
        if (lessPtr != a && a.val > b) {
            parse = a;
            a = a.next;
            parse.next = lessPtr.next;
            lessPtr.next = parse;
        }        
        return a;
    }
}
