/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import interviewbit.datastructures.ListNode;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Dang
 */
public class AddTwoNumbersAsLists {

    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        // push nodes into deque
        Deque<Integer> da = new ArrayDeque<>();
        Deque<Integer> db = new ArrayDeque<>();
        while (a != null) {
            da.push(a.val);
            a = a.next;
        }
        while (b != null) {
            db.push(b.val);
            b = b.next;
        }

        // math logic
        boolean addCarry = false;
        ListNode chain = new ListNode(0);
        ListNode primer = chain;
        primer.next = chain;
        while (!da.isEmpty() || !db.isEmpty()) {
            int n;
            if (da.isEmpty()) {
                n = db.pollLast();
            } else if (db.isEmpty()) {
                n = da.pollLast();
            } else {
                n = db.pollLast() + da.pollLast();
            }
            if (addCarry) {
                n++;
                addCarry = false;
            }
            if (n > 9) {
                addCarry = true;
                n %= 10;
            }
            ListNode node = new ListNode(n);           
            chain.next = node;
            chain = node;

        }
        
        // carry final 1
        if (addCarry) {
            ListNode node = new ListNode(1);
            chain.next = node;
            chain = node;
        }   
        
        // end chain
        chain.next = null;
        
        return primer.next;
    }
}
