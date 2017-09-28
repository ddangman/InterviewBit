/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.Stack;

/**
 *
 * @author Dang
 */
public class SymmetricalBinaryTree {

    public int isSymmetric(TreeNode root) {
        // stacks to traverse root's immediate child
        Stack<TreeNode> left = new Stack<>();
        Stack<TreeNode> right = new Stack<>();
        left.add(root.left);
        right.add(root.right);
        TreeNode lp, rp; // left pointer, right pointer       

        while (!left.isEmpty() || !right.isEmpty()) {
            // pop from stack
            lp = left.pop();
            rp = right.pop();

            // compare nodes
            if (lp == null && rp == null) {
                continue;
            }
            if (lp == null || rp == null) {
                return 0;
            }
            if (lp.val != rp.val) {
                return 0;
            }

            // push into stack using mirror traversal
            left.add(lp.left);
            left.add(lp.right);
            right.add(rp.right);
            right.add(rp.left);
        }
        return 1;
    }
}
