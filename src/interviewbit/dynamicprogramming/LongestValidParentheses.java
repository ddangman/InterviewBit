/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.Stack;

/**
 *
 * @author Duy Dang
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int n = s.length();
        int longest = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) { // scan string from beginning to end
            if (s.charAt(i) == '(') {
                st.push(i); // current character is '(', push its index onto the stack
            } else { // current character is ')'
                if (!st.empty() && s.charAt(st.peek()) == '(') {
                    st.pop(); // top of stack is '('. Matching pair is found. 
                } else {
                    st.push(i); // push unmatched index onto stack
                }
            }
        }
        
        // stack now contains indices of characters that were not initially matched.
        // substring between adjacent stack indices must be valid parentheses
        if (st.empty()) {
            return n; // entire string was matched, return string length
        } else {
            int z = n; // end index
            while (!st.empty()) {
                int a = st.pop(); // start index
                longest = Math.max(longest, z - a - 1); // find longest index gap
                z = a; // update end index
            }
            longest = Math.max(longest, z); // consider gap (0 - z)
        }
        return longest; // return longest index gap
    }
}
