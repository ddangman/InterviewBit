/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.Stack;

/**
 * Given a Balanced Binary Search Tree and a target sum, write a function that
 * returns true if there is a pair with sum equals to target sum, otherwise
 * return false. Expected time complexity is O(n) and only O(Log n) extra space
 * can be used
 *
 * The idea is same as finding the pair in sorted array. We traverse BST in
 * Normal Inorder and Reverse Inorder simultaneously. In reverse inorder, we
 * start from the rightmost node which is the maximum value node. In normal
 * inorder, we start from the left most node which is minimum value node. We add
 * sum of current nodes in both traversals and compare this sum with given
 * target sum. If the sum is same as target sum, we return true. If the sum is
 * more than target sum, we move to next node in reverse inorder traversal,
 * otherwise we move to next node in normal inorder traversal. If any of the
 * traversals is finished without finding a pair, we return false.
 */
public class TwoSumBinaryTree {

    public int t2Sum(TreeNode root, int target) {
        // inorder traversal. Lowest value to greatest
        Stack<TreeNode> istack = new Stack<>(); // increasing stack
        // reverse inorder traversal. Greatest value to lowest
        Stack<TreeNode> dstack = new Stack<>(); // decreasing stack

        // stack until null
        TreeNode ptr = root;
        while (ptr != null) {
            istack.push(ptr);
            ptr = ptr.left;
        } // top of increasing stack is leftmost node (smallest value)
        ptr = root;
        while (ptr != null) {
            dstack.push(ptr);
            ptr = ptr.right;
        } // top of decreasing stack is rightmost node (greatest value)

        TreeNode ipop = istack.pop(); // increasing pop
        TreeNode dpop = dstack.pop(); // decreasing pop
        int sum;
        while (ipop.val < dpop.val) {
            // math operations
            sum = ipop.val + dpop.val;
            if (sum == target) {
                return 1; // true
            } else if (sum < target) {
                // increase sum
                if (ipop.right != null) {
                    istack.push(ipop.right);
                    ipop = ipop.right;
                    // stack left until null
                    while (ipop.left != null) {
                        istack.push(ipop.left);
                        ipop = ipop.left;
                    }
                }
                // pop next inorder node
                ipop = istack.pop();
            } else {
                // decrease sum
                if (dpop.left != null) {
                    dstack.push(dpop.left);
                    dpop = dpop.left;
                    // stack right until null
                    while (dpop.right != null) {
                        dstack.push(dpop.right);
                        dpop = dpop.right;
                    }
                }
                // pop next reverse inorder node
                dpop = dstack.pop();
            }
        }

        return 0; // false
    }
}
