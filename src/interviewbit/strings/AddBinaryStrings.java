/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

import java.util.Stack;

/**
 *
 * @author Dang
 */
public class AddBinaryStrings {

    public String addBinaryStack(String a, String b) {
        if (a.isEmpty()) {
            return b;
        }
        if (b.isEmpty()) {
            return a;
        }

        String cover;
        String limit;
        if (a.length() > b.length()) {
            cover = a;
            limit = b;
        } else {
            cover = b;
            limit = a;
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean carry = false;
        int j = cover.length() - 1;
        for (int i = limit.length() - 1; i >= 0; i--) {
            if (carry) {
                if (limit.charAt(i) == '1') {
                    if (cover.charAt(j) == '1') {
                        stack.push('1');
                    } else {
                        stack.push('0');
                    }
                } else {
                    if (cover.charAt(j) == '1') {
                        stack.push('0');
                    } else {
                        stack.push('1');
                        carry = false;
                    }
                }
            } else {
                if (limit.charAt(i) == '1') {
                    if (cover.charAt(j) == '1') {
                        stack.push('0');
                        carry = true;
                    } else {
                        stack.push('1');
                    }
                } else {
                    if (cover.charAt(j) == '1') {
                        stack.push('1');
                    } else {
                        stack.push('0');
                    }
                }
            }
            j--;
        }

        while (j >= 0) {
            if (carry) {
                if (cover.charAt(j) == '1') {
                    stack.push('0');
                } else {
                    stack.push('1');
                    carry = false;
                }
            } else {
                stack.push(cover.charAt(j));
            }
            j--;
        }

        if (carry) {
            stack.push('1');
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    public String addBinaryArray(String A, String B) {

        int nA, nB;
        char res[];
        int i, j, k;

        nA = A.length();
        nB = B.length();
        res = new char[Math.max(nA, nB) + 1];

        k = Math.max(nA, nB);
        i = nA - 1;
        j = nB - 1;
        int sum = 0, carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {

            sum = carry;

            if (i >= 0) {
                sum += (A.charAt(i) - '0');
            }
            if (j >= 0) {
                sum += (B.charAt(j) - '0');
            }

            res[k] = (char) ((sum % 2) + '0');

            carry = sum / 2;

            i--;
            j--;
            k--;

        }

        if (res[0] == '1') {
            return new String(res);
        }

        int len = Math.max(nA, nB);

        return new String(res, 1, len);

    }
}
