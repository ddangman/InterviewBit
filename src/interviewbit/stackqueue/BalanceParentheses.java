/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

import java.util.Stack;

/**
 *
 * @author Dang
 */
public class BalanceParentheses {

    public int isValid(String a) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            switch (c) {
                case '(':
                case '{':
                case '[':
                    st.push(c);
                    break;
                case ')':
                    if (st.isEmpty()) {
                        return 0;
                    }
                    if (st.pop() != '(') {
                        return 0;
                    }
                    break;
                case '}':
                    if (st.isEmpty()) {
                        return 0;
                    }
                    if (st.pop() != '{') {
                        return 0;
                    }
                    break;
                case ']':
                    if (st.isEmpty()) {
                        return 0;
                    }
                    if (st.pop() != '[') {
                        return 0;
                    }
                    break;
            }
        }
        if (!st.isEmpty()) {
            return 0;
        }
        return 1;
    }
}
