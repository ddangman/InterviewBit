/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package utilities;

import interviewbit.datastructures.ListNode;
import java.util.Scanner;

/**
 *
 * @author Dang
 */
public class LinkedListUtilities {

    public static ListNode generateLinkedList(String s) {
        Scanner scanner = new Scanner(s);
        ListNode root = new ListNode(scanner.nextInt());
        ListNode temp = root;
        while (scanner.hasNextInt()) {
            ListNode newNode = new ListNode(scanner.nextInt());
            temp.next = newNode;
            temp = newNode;
        }
        return root;
    }
    
    public static void printLinkedList(ListNode root) {
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.next;
        }
    }
}
