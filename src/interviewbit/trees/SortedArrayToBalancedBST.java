/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import java.util.List;

/**
 *
 * @author Dang
 */
public class SortedArrayToBalancedBST {

    public TreeNode sortedArrayToBST(final List<Integer> a) {
        return build(a, 0, a.size());
    }

    public TreeNode build(final List<Integer> a, int start, int end) {
        // base case
        if (start > end || start >= a.size()) {
            return null;
        }
        // leaf node
        if (start == end) {
            return new TreeNode(a.get(start));
        }

        // create root at midpoint
        int m = (start + end) / 2;
        TreeNode root = new TreeNode(a.get(m));
        // recursively build tree from remaining array lengths
        root.left = build(a, start, m - 1);
        root.right = build(a, m + 1, end);

        return root;
    }
}
