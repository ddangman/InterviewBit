/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

/**
 *
 * @author Dang
 */
public class Sandbox {

    public void connect(TreeLinkNode root) {
        TreeLinkNode ptr;

        while (root != null) {
            ptr = root;

            // connect child level
            while (ptr != null) {
                if (ptr.left != null) {
                    if (ptr.right != null) {
                        ptr.left.next = ptr.right;
                    } else {
                        ptr.left.next = getNextChild(ptr.next);
                    }
                }
                if (ptr.right != null) {
                    ptr.right.next = getNextChild(ptr.next);
                }
                ptr = ptr.next;
            }
            
            // set root to next level's leftmost node
            if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = getNextChild(root.next);
            }           
        }

    }
    
    public TreeLinkNode getNextChild(TreeLinkNode node) {
        while (node != null) {
            if (node.left != null) {
                return node.left;
            }            
            if (node.right != null) {
                return node.right;
            }            
            node = node.next;
        }
        return null;
    }

    public class TreeLinkNode {

        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }
}
