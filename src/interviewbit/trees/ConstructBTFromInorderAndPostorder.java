/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class ConstructBTFromInorderAndPostorder {

    private ArrayList<Integer> in;
    private ArrayList<Integer> post;
    private int pri; // postorder root index

    public TreeNode buildTree(ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        this.in = inorder;
        this.post = postorder;
        pri = inorder.size() - 1;

        return buildUtil(0, inorder.size() - 1);
    }

    /* left child root is visited before right, 
     * so right recursion must be stacked before left
     * to decrement postorder root index properly */
    // takes start and end index of inorder array
    public TreeNode buildUtil(int start, int end) {
        if (end < start || pri < 0) {
            return null;
        }

        TreeNode root = new TreeNode(post.get(pri--));
        if (start == end) {
            return root;
        }

        int iri = end; // inorder root index
        for (int i = start; i < end; i++) {
            if (in.get(i) == root.val) {
                iri = i;
                break;
            }
        }
        
        // recursively call for right subtree before left subtree 
        // as we decrease index of postorder index whenever we create a new node
        root.right = buildUtil(iri + 1, end);
        // when right recursion is done, postorder index will decrement to point 
        // to the end of left subtree's postorder traversal, 
        // which will be the root value 
        root.left = buildUtil(start, iri - 1);

        return root;
    }
}
