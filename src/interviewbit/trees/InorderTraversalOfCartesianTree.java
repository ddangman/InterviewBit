/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.ArrayList;

/* http://www.geeksforgeeks.org/cartesian-tree/
 * A Cartesian tree is a tree data structure created from a set of data that
 * obeys the following structural invariants:
   1. The tree obeys in the min (or max) heap property – each node is less
      (or greater) than its children.
   2. An inorder traversal of the nodes yields the values in the same order
      in which they appear in the initial sequence.
 */
public class InorderTraversalOfCartesianTree {

    public class IterativeInsertion {

        public TreeNode buildTreeIterative(ArrayList<Integer> input) {
            TreeNode root = new TreeNode(input.get(0));
            TreeNode greater;

            for (int i = 1; i < input.size(); i++) {
                TreeNode node = new TreeNode(input.get(i));

                if (node.val > root.val) {
                    // new node is greatest, make it root
                    node.left = root;
                    root = node;
                } else {
                    // find first node greater than new node
                    greater = root;
                    while (greater.right != null
                            && greater.right.val > node.val) {
                        greater = greater.right;
                    }
                    if (greater.right == null) {
                        // new node has smallest value, insert as rightmost leaf
                        greater.right = node;
                    } else { // replace greater's right child with new node
                        // inherit greater's previous right child as new node's left child
                        node.left = greater.right;
                        // insert new node immediately right of next greater value
                        greater.right = node;
                    }
                }
            }

            return root;
        }
    }

    private ArrayList<Integer> input;
    private int[] leftChild;
    private int[] rightChild;

    public TreeNode buildTree(ArrayList<Integer> input) {
        this.input = input;
        int n = input.size();

        // Arrays to hold the index of parent, left-child,
        // right-child of each number in the input array
        int[] parent = new int[n];
        leftChild = new int[n];
        rightChild = new int[n];
        // Initialize all array values as -1
        for (int i = 0; i < n; i++) {
            parent[i] = leftChild[i] = rightChild[i] = -1;
        }

        // 'root' and 'last' stores the index of the root and the
        // last processed of the Cartesian Tree.
        // Initially we take root of the Cartesian Tree as the
        // first element of the input array. This can change
        // according to the algorithm
        int root = 0;
        int last;

        // Scan the given sequence from left to right adding new nodes as follows:
        // 1. Position the node as the right child of the rightmost node.
        // 2. Scan upward from the node’s parent up to the root of the tree 
        //    until a node is found whose value is greater than the current value.
        // 3. If such a node is found, set its right child to be the new node, 
        //    and set the new node’s left child to be the previous right child.
        // 4. If no such node is found, set the new child to be the root, 
        //    and set the new node’s left child to be the previous tree.
        for (int i = 1; i < n; i++) {
            last = i - 1;

            // Step 2
            // Scan upward from the node's parent up to
            // the root of the tree until a node is found
            // whose value is greater than the current one
            while (input.get(last) <= input.get(i) && last != root) {
                last = parent[last];
            }

            // Step 4
            // input.get(i) is the largest element yet; 
            // make it new root
            if (input.get(root) <= input.get(i)) {
                parent[root] = i; // current is now parent of previous root
                leftChild[i] = root; // previous root is now leftChild of current
                root = i; // set current as root
            } // Step 1
            // current is smallest element yet;
            // make it rightmost leaf
            else if (last + 1 == i) {
                rightChild[last] = i;
                parent[i] = last;
                leftChild[i] = -1;
            } // Step 3
            // greater node found that is not root
            // current will take greaterNode's rightChild as its leftChild
            // and become greaterNode's new rightChild
            else {
                // set greaterNode's previous rightChild's parent as current
                parent[rightChild[last]] = i;
                // set current's leftChild as greaterNode's previous rightChild
                leftChild[i] = rightChild[last];
                rightChild[last] = i; // set greaterNode's rightChild as current
                parent[i] = last; // set current's parent as greaterNode
            }
        }

        return buildUtil(root);
    }

    private TreeNode buildUtil(int root) {
        if (root == -1) {
            return null;
        }

        // create new node with root's data
        TreeNode node = new TreeNode(input.get(root));

        // recursively construct left and right subtrees
        node.left = buildUtil(leftChild[root]);
        node.right = buildUtil(rightChild[root]);

        return node;
    }

    public class QuadraticRecursion {

        public TreeNode buildTreeQuad(ArrayList<Integer> arr) {
            if (arr.isEmpty()) {
                return null;
            }
            return build(arr, 0, arr.size() - 1);
        }

        private TreeNode build(ArrayList<Integer> arr, int start, int end) {
            if (end < start) {
                return null;
            }

            // find the greatest number in the sequence
            int max = Integer.MIN_VALUE;
            int maxIndex = -1;
            for (int i = start; i <= end; i++) {
                if (arr.get(i) > max) {
                    max = arr.get(i);
                    maxIndex = i;
                }
            }

            // make max the root of the tree
            TreeNode root = new TreeNode(max);

            // push all indices left of root into root.left recursively
            root.left = build(arr, start, maxIndex - 1);
            // push all indices right of root into root.right recursively
            root.right = build(arr, maxIndex + 1, end);

            return root;
        }
    }
}
