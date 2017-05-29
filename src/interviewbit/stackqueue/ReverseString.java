/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

import java.util.Stack;

/**
 *
 * @author Dang
 */
public class ReverseString {
    public String reverseString(String a) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < a.length(); i++) {
            st.add(a.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.toString();
    }
}
