/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

/**
 *
 * @author Dang
 */
public class PopulateNextRightPointersTree {

    // set next to connect all nodes of the same level from left to right
    public void connect(TreeLinkNode root) {
        TreeLinkNode ptr; // pointer

        // set next of all levels one by one
        while (root != null) {
            ptr = root;

            // connect all children nodes of root and
            // children nodes of all other nodes at same level as root
            while (ptr != null) {
                // set next for pointer's left child
                if (ptr.left != null) {
                    // if pointer has right child, then
                    // pointer's left child's next should be pointer's right child
                    if (ptr.right != null) {
                        ptr.left.next = ptr.right;
                    } else {
                        ptr.left.next = getNextChild(ptr);
                    }
                }
                if (ptr.right != null) {
                    ptr.right.next = getNextChild(ptr);
                }   
                
                // move pointer to next to set rest of level
                ptr = ptr.next;
            }
            
            // move root to next level
            if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = getNextChild(root);
            }          
        }

    }

    /**
     * traverse completed level starting at node. find and return first child
     * @param node current node's entire level should already be connected
     * @return next child of current node 
     */
    public TreeLinkNode getNextChild(TreeLinkNode node) {
        TreeLinkNode next = node.next;

        // traverse node's level to find first node with child
        // return leftmost child
        while (next != null) {
            // return first child found
            if (next.left != null) {
                return next.left;
            }
            if (next.right != null) {
                return next.right;
            }
            // no child found, go to next node
            next = next.next;
        }

        // all nodes at this level are leaf nodes, return null
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
