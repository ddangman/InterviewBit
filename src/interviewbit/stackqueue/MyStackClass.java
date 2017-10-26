/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

/**
 *
 * @author Dang
 */
public class MyStackClass {

    private jStack root;
    private int minimum;

    public void MinStack() {
        root = null;
        this.minimum = -1;
    }

    private class jStack {

        int value;
        jStack next = null;
    }

    public void push(int x) {
        if (root == null) {
            minimum = x;
        } else if (minimum > x) {
            minimum = x;
        }

        jStack newjs = new jStack();
        newjs.value = x;
        newjs.next = root;
        root = newjs;
    }

    public void pop() {

        if (root == null) {
            return;
        }
        boolean needsUpdate = (minimum == root.value);

        if (root.next != null) {
            root = root.next;
        } else {
            root = null;
            needsUpdate = false;
        }

        if (needsUpdate) {
            jStack temp = root;
            minimum = Integer.MAX_VALUE;
            do {
                if (temp.value < minimum) {
                    minimum = temp.value;
                }
                temp = temp.next;
            } while (temp != null);
        }
    }

    public int top() {
        if (root == null) {
            return -1;
        }
        return root.value;
    }

    public int getMin() {
        if (root == null) {
            return -1;
        }
        return minimum;
    }
}
