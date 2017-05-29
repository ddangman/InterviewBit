/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class GenerateAllParentheses {

    ArrayList<String> allCombo;
    int n;

    public ArrayList<String> generateParenthesis(int n) {
        allCombo = new ArrayList<>();
        this.n = n;
        char[] ca = new char[2 * n];

        fillArray(ca, 0, 0, 0);
        
        Collections.sort(allCombo);
        return allCombo;
    }

    public void fillArray(char[] ca, int i, int open, int close) {
        if (close == n) {
            allCombo.add(new String(ca));
            return;
        }

        if (open > close) {
            ca[i] = ')';
            fillArray(ca, i + 1, open, close + 1);
        }
        if (open < n) {
            ca[i] = '(';
            fillArray(ca, i + 1, open + 1, close);
        }

    }
}
