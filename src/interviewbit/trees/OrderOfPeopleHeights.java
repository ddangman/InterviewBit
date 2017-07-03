/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author Dang
 */
public class OrderOfPeopleHeights {

    /* 
     * each Person node corresponds to a person and 
     * each node.value keeps track of how many people are in front of him 
     * (which is the size of the left subtree) as nodes are inserted.
    
     Insertion logic:
       1. Compare the infrons of the person with the current node's 
          (root at the beginning) value.
       2. If it is smaller than it insert the node to the left with value 1. 
          Increase the current node's value by 1.
       3. Else, descend to the right by decreasing the 
          person's infronts by current node's value. 
          This enables the node to be placed in the correct location.
     */
    public ArrayList<Integer> order(ArrayList<Integer> heights, ArrayList<Integer> infronts) {
        int n = heights.size();
        Person[] pa = new Person[n]; // Person Array

        for (int i = 0; i < n; i++) {
            pa[i] = new Person(heights.get(i), infronts.get(i));
        }

        // sort by heights in descending order
        Arrays.sort(pa, (p1, p2) -> {
            return Integer.compare(p2.height, p1.height);
        });

        // Start iterating the persons array in decreasing height order and 
        // insert each person into the tree starting from the root
        Node root = new Node(pa[0]);
        for (int i = 1; i < n; i++) {
            insert(root, pa[i]);
        }

        // inorder traversal
        ArrayList<Integer> visit = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node ptr = root;
        while (!stack.isEmpty() || ptr != null) {
            if (ptr != null) {
                // stack all left children
                stack.add(ptr);
                ptr = ptr.left;
            } else {
                ptr = stack.pop();
                visit.add(ptr.person.height);
                ptr = ptr.right;
            }
        }

        return visit;
    }

    private void insert(Node root, Person p) {
        // compare Person node's inFront to root node's value
        insert(root, p, p.inFront);
    }

    // since height is sorted in descending order,
    // this function is called in decreasing height order.
    // Person belonging after root (based on inFront value)
    // is pushed right, to be visited after root inorder traversal
    // Person belonging before root is pushed left to be visited before root
    // If root has child, person will traverse down tree recursively
    // else, person is wrapped in new Node with initial
    // value as one. This allows nodes with zero inFront to be pushed left.
    // when descending left, parent node (root) value is increased by one,
    // thus node.value will be number of left nodes plus one
    // once enough shorter people are pushed ahead of root, 
    // all other people will be forced right (inorder behind) root
    // when descending right, person's value will have parent's value subtracted
    // once value is reduced to zero (must be next after its parent), 
    // it will be forced to traverse left to the proper inorder position
    private void insert(Node root, Person p, int value) {
        if (value < root.value) { // should insert to the left
            if (root.left == null) {
                root.left = new Node(p);
            } else {
                insert(root.left, p, value); // descend to the left
            }
            root.value++; // Increase the root's value while descending left
        } else { // insert to the right
            if (root.right == null) {
                root.right = new Node(p);
            } else {
                // decend to the right 
                // decreasing person's inFront by current root's value
                insert(root.right, p, value - root.value);
            }
        }
    }

    private class Person {

        public final int height;
        public final int inFront;

        Person(int h, int f) {
            this.height = h;
            this.inFront = f;
        }
    }

    private class Node {

        Node left, right;
        int value;
        public final Person person;

        public Node(Person p) {
            // default value is initialized as 1
            this.value = 1;
            this.person = p;
        }
    }

    class SegmentTreeSolution {

        public ArrayList<Integer> order(ArrayList<Integer> heights, ArrayList<Integer> infronts) {

            ArrayList<Integer> order = new ArrayList<>();
            ArrayList<Node> nodes = new ArrayList<>();
            int n;

            if (heights == null || infronts == null) {
                return order;
            }

            n = heights.size();

            Bit bit = new Bit(n);

            for (int i = 1; i <= n; i++) {
                bit.update(i, 1);
            }

            for (int i = 0; i < n; i++) {
                Node node = new Node(heights.get(i), infronts.get(i));
                nodes.add(node);
                order.add(0);
            }

            Collections.sort(nodes);

            for (int i = 0; i < n; i++) {
                Node node = nodes.get(i);
                int index = getIth(bit, node.infronts + 1, n);
                order.set(index, node.height);
            }

            return order;
        }

        public int getIth(Bit bit, int index, int n) {

            int start = 1;
            int end = n;
            int count = end - start + 1;
            int res = 0;

            while (count > 0) {

                int mid = (start + end) / 2;
                int val = bit.query(mid);

                if (val < index) {
                    start = mid + 1;
                } else if (val > index) {
                    end = mid - 1;
                } else if (val == index) {
                    res = mid;
                    end = mid - 1;
                }

                count /= 2;

            }

            bit.update(res, -1);

            return res - 1;
        }

        class Node implements Comparable<Node> {

            int height;
            int infronts;

            public Node(int h, int i) {
                height = h;
                infronts = i;
            }

            @Override
            public int compareTo(Node node) {
                return Integer.compare(height, node.height);
            }
        }

        class Bit {

            int bit[];
            int N;

            public Bit(int N) {
                this.bit = new int[N + 10];
                this.N = N;
            }

            public void update(int idx, int value) {

                while (idx > 0 && idx <= N) {
                    bit[idx] += value;
                    idx += (idx & -idx);
                }
            }

            public int query(int idx) {

                int res = 0;

                while (idx > 0) {
                    res += bit[idx];
                    idx -= (idx & -idx);
                }

                return res;
            }
        }
    }
}
