/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

import java.util.Stack;

/**
 *
 * @author Dang
 */
public class RedundantBraces {

    public int braces(String a) {
        Stack<Stack> stacks = new Stack<>();
        Stack<Character> cStack = null;

        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            switch (c) {
                case '+':
                case '*':
                case '-':
                case '/':
                    // use '$' to indicate valid operator
                    if (cStack == null) {
                        // probably doesn't need braces
                    } else {
                        cStack.push('$');
                    }
                    break;
                case '(':
                    if (cStack != null) {
                        stacks.push(cStack);
                    }
                    cStack = new Stack<>();
                    cStack.push(c);
                    break;
                case ')':
                    if (cStack == null) {
                        return 1;
                    }                    
                    if (cStack.pop() != '$') {
                        return 1;
                    }   
                    if (cStack.pop() != '(') {
                        return 1;
                    }   
                    if (!stacks.isEmpty()) {
                        cStack = stacks.pop();
                    }                
            }
        }
        return 0;
    }
}
