/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import interviewbit.datastructures.ListNode;
import interviewbit.datastructures.TreeNode;
import java.util.ArrayList;

/**
 *
 * @author Duy Dang
 */
public class ConvertSortedListToBST {

    private ArrayList<Integer> al;
    private int n;

    public TreeNode sortedListToBST(ListNode a) {
        ListNode ptr = a;
        if (ptr == null) {
            return null;
        }
        al = new ArrayList<>();
        al.add(ptr.val);
        while (ptr.next != null) {
            ptr = ptr.next;
            al.add(ptr.val);
        }
        this.n = al.size();
        int mid = n / 2;
        TreeNode root = new TreeNode(al.get(mid));
        recursion(0, mid - 1, root, mid + 1, n - 1);
        return root;
    }

    private void recursion(int ls, int le, TreeNode root, int rs, int re) {

        if (le < ls) {
            
        } 
        else if (ls == le) {
            root.left = new TreeNode(al.get(ls));
        } else {
            int lm = (le - ls) / 2;
            lm += ls;
            root.left = new TreeNode(al.get(lm));
            recursion(ls, lm - 1, root.left, lm + 1, le);
        }
        if (re < rs) {
            
        } 
        else if (rs == re) {
            root.right = new TreeNode(al.get(rs));
        } else {
            int rm = (re - rs) / 2;
            rm += rs;
            root.right = new TreeNode(al.get(rm));
            recursion(rs, rm - 1, root.right, rm + 1, re);
        }
    }
}
