/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;

/**
 *
 * @author Dang
 */
public class LeastCommonAncestor {

    boolean foundx, foundy;

    public int lca(TreeNode tn, int val1, int val2) {
        foundx = foundy = false;
        TreeNode anc = traverse(tn, val1, val2);
        if (anc == null || !foundx || !foundy) {
            return -1;
        } else {
            return anc.val;
        }
    }

    public TreeNode traverse(TreeNode root, int x, int y) {
        // base case
        if (root == null) {
            return null;
        }

        // recursively traverse tree
        TreeNode left = traverse(root.left, x, y);
        // when returned value is not x or y, it must be solution
        if (left != null && left.val != x && left.val != y) {
            return left;
        }
        TreeNode right = traverse(root.right, x, y);
        if (right != null && right.val != x && right.val != y) {
            return right;
        }

        // least common ancestor found
        if (left != null && right != null) {
            return root;
        }

        // return matching node
        if (root.val == x) {
            foundx = true;
            if (x == y) {
                foundy = true;
            }
            return root;
        }
        if (root.val == y) {
            foundy = true;
            return root;
        }
        return left == null ? right : left;
    }

}
