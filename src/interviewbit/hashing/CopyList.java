/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

/**
 *
 * @author Dang
 */
public class CopyList {

    public class RandomListNode {

        public int label;
        public RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    };

    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            RandomListNode copy = new RandomListNode(iter.label);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        RandomListNode primer = new RandomListNode(0);
        RandomListNode copy, copyIter = primer;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return primer.next;
    }
}
