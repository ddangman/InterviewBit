/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.LinkedList;

/**
 *
 * @author Duy Dang
 */
public class BuildIdenticalTrees {

    private int diff;

    public int cntMatrix(TreeNode a, TreeNode b) {
        diff = 0;
        if (match(a, b, Integer.MIN_VALUE, a.val)) {
            return diff;
        }
        return -1;
    }

    private boolean match(TreeNode a, TreeNode b, int min, int max) {
        if (a == null && b == null) {
            return true;
        }
        if (b == null) {
            return false;
        }
        if (a == null) {
            int need = count(b);
            diff += need;
            return true;
        }
        return match(a.left, b.left, min, a.val)
                && match(a.right, b.right, a.val, max);
    }

    private int count(TreeNode b) {
        int x = 0;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(b);
        while (!q.isEmpty()) {
            x++;
            TreeNode pop = q.pop();
            if (pop.left != null) {
                q.add(pop.left);
            }
            if (pop.right != null) {
                q.add(pop.right);
            }
        }

        return x;
    }

}
