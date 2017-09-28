/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;

/**
 *
 * @author Dang
 */
public class IdenticalBinaryTrees {

    // return 1 if true, 0 if false
    public int isSameTree(TreeNode a, TreeNode b) {
        return sameRec(a, b);
    }

    public int sameRec(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return 1;
        }
        if (a == null || b == null) {
            return 0;
        }
        if (a.val != b.val) {
            return 0;
        }

        int left = sameRec(a.left, b.left);
        int right = sameRec(a.right, b.right);

        return left + right == 2 ? 1 : 0;
    }
}
