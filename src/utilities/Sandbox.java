/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author Dang
 */
public class Sandbox {

    // Order of People Heights
    public ArrayList<Integer> order(ArrayList<Integer> heights, ArrayList<Integer> infronts) {
        int n = heights.size();

        Person[] pa = new Person[n];
        for (int i = 0; i < n; i++) {
            pa[i] = new Person(heights.get(i), infronts.get(i));
        }
        // sort by descending height
        Arrays.sort(pa, (p1, p2) -> {
            return p2.height - p1.height;
        });
        
        // build tree
        Node root = new Node(pa[0]);
        for (int i = 1; i < n; i++) {
            insert(root, pa[i]);
        }
        
        // inorder traversal
        ArrayList<Integer> visit = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            // stack left until null
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                root = stack.pop();
                visit.add(root.person.height);
                root = root.right;
            }           
        }
        
        return visit;
    }
    
    public void insert(Node root, Person p) {
        insertUtil(root, p, p.inFront);
    }
    
    public void insertUtil (Node root, Person p, int value) {
        // person belongs in front of root, traverse left
        if (value < root.value) {
            if (root.left == null) {
                root.left = new Node(p);
            } else {
                // push left
                insertUtil(root.left, p, value);
            } 
            // increase left children count
            root.value++;
        } else {
            if (root.right == null) {
                root.right = new Node(p);
            } else {
                // push right
                insertUtil(root.right, p, value - root.value);
            }           
        }       
    }
    
    class Person {
        public final int height;
        public final int inFront;
        
        Person(int h, int i) {
            this.height = h;
            this.inFront = i;
        }
    }
    
    class Node {
        public int value;
        public final Person person;
        public Node left, right;
        
        Node(Person p) {
            this.person = p;
            this.value = 1;
        }
    }
}
