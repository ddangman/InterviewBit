/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.heapmap;

import java.util.HashMap;

/**
 *
 * @author Dang
 */
public class LRUcacheTester {

    public void run() {
        char[] test = "2 S 2 1 S 1 1 S 2 3 S 4 1 G 1 G 2".toCharArray(); // -1 3
        Solution sol = new Solution(Character.getNumericValue(test[0]));
        for (int i = 2; i < test.length;) {
            if (test[i] == 'S') {
                sol.set(Character.getNumericValue(test[i + 2]),
                        Character.getNumericValue(test[i + 4]));
                i += 6;
            } else if (test[i] == 'G') {
                System.out.println(sol.get(
                        Character.getNumericValue(test[i + 2])));
                i += 4;
            } else {
                i++;
            }

        }
    }

    public class Solution {

        private class DoublyLinkedList {

            private int key;
            private int value;
            private DoublyLinkedList next;
            private DoublyLinkedList prev;

            DoublyLinkedList(int k, int v) {
                this.key = k;
                this.value = v;
                next = null;
                prev = null;
            }
        }

        DoublyLinkedList head;
        DoublyLinkedList tail;
        HashMap<Integer, DoublyLinkedList> map;
        int cap;

        public Solution(int capacity) {
            map = new HashMap<>();
            head = tail = null;
            this.cap = capacity;
        }

        public int get(int key) {
            DoublyLinkedList node = map.get(key);
            if (node != null) {
                bringToHead(node);
                return node.value;
            }
            return -1;
        }

        public void set(int key, int value) {
            DoublyLinkedList old = map.get(key);
            if (old != null) {
                old.value = value;
                bringToHead(old);
            } else {
                if (map.size() == cap) {
                    remove(tail, true);
                }
                DoublyLinkedList created = new DoublyLinkedList(key, value);
                setAsHead(created);
                map.put(key, created);
                if (tail == null) {
                    tail = created;
                }
            }
        }

        public void bringToHead(DoublyLinkedList node) {
            if (node != null) {
                remove(node, false);
                setAsHead(node);
            }
        }

        public void setAsHead(DoublyLinkedList node) {
            if (head == null && tail == null) {
                head = tail = node;
                return;
            }
            if (node != null) {
                node.next = head;
                node.prev = null;
                head.prev = node;
                head = node;
            }
        }

        public void remove(DoublyLinkedList node, boolean wipeMap) {
            if (node == null) {
                return;
            }
            if (node.prev == null && node.next == null) {
                // last node in list
                head = tail = null;
            } else if (node.prev == null) {
                // node is head
                head = node.next;
                head.prev = null;
            } else if (node.next == null) {
                // node is tail
                tail = tail.prev;
                tail.next = null;
            } else {
                // node is sandwiched
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            if (wipeMap && map.containsKey(node.key)) {
                map.remove(node.key);
            }
        }
    }
}
