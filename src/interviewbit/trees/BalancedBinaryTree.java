/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;

/**
 *
 * @author Dang
 */
public class BalancedBinaryTree {

    boolean invalid;

    public int isBalanced(TreeNode a) {
        countHeight(a);
        return invalid ? 0 : 1;
    }

    public int countHeight(TreeNode tn) {
        if (tn == null) {
            return 0;
        }

        int left = countHeight(tn.left) + 1;
        int right = countHeight(tn.right) + 1;

        if (Math.abs(left - right) > 1) {
            invalid = true;
        } 
        return left > right ? left : right;
    }
}
