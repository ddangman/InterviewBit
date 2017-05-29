/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Dang
 */
public class EvaluateExpression {

    public int evalRPN(ArrayList<String> a) {
        Stack<Integer> iStack = new Stack<>();
        int x = 0;
        int y = 0;
        int z = 0;
        for (String s : a) {
            switch (s) {
                case "+":
                case "-":
                case "/":
                case "*":
                    x = iStack.pop();
                    y = iStack.pop();
                    switch (s) {
                        case "+":
                            z = y + x;
                            break;
                        case "-":
                            z = y - x;
                            break;
                        case "*":
                            z = y * x;
                            break;
                        case "/":
                            z = y / x;
                            break;
                    }
                    iStack.push(z);
                    break;
                default:
                    iStack.add(Integer.parseInt(s));
            }
        }
        return iStack.pop();
    }

}
