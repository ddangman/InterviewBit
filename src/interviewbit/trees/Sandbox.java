/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class Sandbox {

    private ArrayList<Integer> inorder;
    private ArrayList<Integer> postorder;
    private int pri; // postorder root index

    public TreeNode buildTree(ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        // postorder root is visited last
        this.pri = inorder.size() - 1;

        return build(0, pri);
    }

    private TreeNode build(int start, int end) {
        if (end < start) {
            return null;
        }

        TreeNode root = new TreeNode(postorder.get(pri--));
        if (end == start) {
            return root;
        }

        int iri = end; // inorder root index
        for (int i = start; i < end; i++) {
            if (inorder.get(i) == root.val) {
                iri = i;
                break;
            }
        }

        root.right = build(iri + 1, end);
        // left child root is visited before right, 
        // so right recursion must be stacked before left
        // to decrement postorder root index properly
        root.left = build(start, iri - 1);

        return root;
    }
}
