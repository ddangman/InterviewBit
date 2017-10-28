/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Dang
 */
public class Sandbox {

    public String multiple(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 0) {
            return "0";
        }
        boolean[] isVisited = new boolean[n];
        LinkedList<Node> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        int now = 1 % n;
        Node root = new Node(now == 1, now, null);
        isVisited[now] = true;
        q.add(root);

        while (!q.isEmpty()) {
            Node pop = q.pop();

            // add 0
            int addZero = pop.mod * 10 % n;
            if (addZero == 0) {
                root = pop;
                sb.append('0');
                break;
            } else if (!isVisited[addZero]) {
                isVisited[addZero] = true;
                q.add(new Node(false, addZero, pop));
            }

            // add 1
            int addOne = (addZero + 1) % n;
            if (addOne == 0) {
                root = pop;
                sb.append('1');
                break;
            } else if (!isVisited[addOne]) {
                isVisited[addOne] = true;
                q.add(new Node(true, addOne, pop));
            }

        }
        
        while (root != null) {
            sb.append(root.isOne ? 1 : 0);
            root = root.parent;
        }
        
        return sb.reverse().toString();
    }

    private class Node {

        boolean isOne;
        int mod;
        Node parent;

        Node(boolean one, int md, Node par) {
            this.isOne = one;
            this.mod = md;
            this.parent = par;
        }
    }

}
