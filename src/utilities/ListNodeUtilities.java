/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package utilities;

import interviewbit.linkedlists.ListNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Dang
 */
public class ListNodeUtilities {

    public static ListNode generateLinkedList(String s) {
        Scanner scanner = new Scanner(s);
        List<Integer> list = new ArrayList<Integer>();
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
