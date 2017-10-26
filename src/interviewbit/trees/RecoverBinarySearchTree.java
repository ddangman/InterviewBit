/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class RecoverBinarySearchTree {

    private int visit, wrong1, wrong2;
    private boolean firstFault;

    public ArrayList<Integer> recoverTree(TreeNode root) {
        TreeNode ptr;
        visit = Integer.MIN_VALUE;

        while (root != null) {
            if (root.left == null) {
                // visit before moving root right
                if (visitRoot(root.val)) {
                    break;
                }
                root = root.right;
            } else {
                ptr = root.left;
                while (ptr.right != null && ptr.right != root) {
                    ptr = ptr.right;
                }
                if (ptr.right == null) {
                    // thread
                    ptr.right = root;
                    root = root.left;
                } else {
                    // thread already exists
                    ptr.right = null;
                    // visit before moving root right
                    if (visitRoot(root.val)) {
                        break;
                    }
                    root = root.right;
                }
            }
        }

        return new ArrayList<>(Arrays.asList(wrong1, wrong2));
    }

    private boolean visitRoot(int v) {
        if (v < visit) {
            if (!firstFault) {
                // assign both wrong values in case swapped pairs are adjacent
                wrong1 = v;
                wrong2 = visit; // wrong2 is greater value
                firstFault = true;
            } else {
                // wrong pair is not adjacent, 
                // update wrong1 to new smaller value
                wrong1 = v;
                return true; // no need to continue searching
            }
        }
        // always update visit
        visit = v;

        return false;
    }
}
