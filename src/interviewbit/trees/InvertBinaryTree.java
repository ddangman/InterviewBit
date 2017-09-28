/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;

/**
 *
 * @author Dang
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        inversion(root);
        return root;
    }

    public void inversion(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        inversion(root.right);
        inversion(root.left);
    }
}
