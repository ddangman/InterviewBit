/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Dang
 */
public class SimplifyDirectoryPath {

    public String simplifyPath(String a) {
        Deque<String> dq = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        // parse string into double ended queue
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if (c == '/') {
                dqUtil(sb.toString(), dq);
                sb.setLength(0);
                sb.append('/');
            } else {
                sb.append(c);
            }
        }
        dqUtil(sb.toString(), dq);

        StringBuilder fBuild = new StringBuilder();
        while (!dq.isEmpty()) {
            fBuild.append(dq.poll());
        }
        String dir = fBuild.toString();
        if (dir.isEmpty()) {
            return "/";
        }
        return dir;
    }

    private void dqUtil(String s, Deque<String> dq) {
        switch (s) {
            case "/..":
                dq.pollLast();
            case "/":
            case "/.":
                break;
            default:
                dq.addLast(s);
        }
    }

    public String simplifyPathStack(String A) {
        Stack<String> stack = new Stack<>();
        int start, end;
        int n = A.length();
        String str;

        for (int i = 0; i < n;) {

            while (i < n && A.charAt(i) == '/') {
                i++;
            }

            start = i;

            if (i >= n) {
                break;
            }

            while (i < n && A.charAt(i) != '/') {
                i++;
            }

            str = A.substring(start, i);

            if (str.equalsIgnoreCase("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!str.equalsIgnoreCase(".")) {
                stack.push(str);
            }

        }

        String res = "";

        if (stack.isEmpty()) {
            res = "/";
        }

        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }

        return res;
    }
}
