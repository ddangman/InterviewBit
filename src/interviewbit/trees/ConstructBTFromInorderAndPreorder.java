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
public class ConstructBTFromInorderAndPreorder {

    private ArrayList<Integer> preorder;
    private ArrayList<Integer> inorder;
    private int pri; // preorder root index

    public TreeNode buildTree(ArrayList<Integer> preorder, ArrayList<Integer> inorder) {
        this.inorder = inorder;
        this.preorder = preorder;
        pri = 0;

        return build(0, preorder.size() - 1);
    }

    private TreeNode build(int start, int end) {
        if (start > end) {
            return null;
        }

        // get root value from preorder array and increment preorder root index
        TreeNode root = new TreeNode(preorder.get(pri++));
        if (start == end) {
            return root;
        } 
        
        // find inorder root index for recursion
        int iri = end;
        for (int i = start; i < end; i++) {
            if (inorder.get(i) == root.val) {
                iri = i;
                break;
            }            
        }
        
        // enter left recursion first since preorder root is visited left side first
        root.left = build(start, iri - 1);
        root.right = build(iri + 1, end);
        
        return root;
    }
}
