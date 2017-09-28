/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;

/**
 *
 * @author Dang
 */
public class ValidBST {

    public int isValidBST(TreeNode root) {
        return recursion(root, Long.MIN_VALUE, Long.MAX_VALUE) ? 1 : 0;
    }
    
    public boolean recursion(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return recursion(root.left, minVal, root.val) && recursion(root.right, root.val, maxVal);
    }
}
