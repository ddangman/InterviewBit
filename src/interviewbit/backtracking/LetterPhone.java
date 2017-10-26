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
public class LetterPhone {

    private ArrayList<String> allCombo;

    public ArrayList<String> letterCombinations(String s) {
        allCombo = new ArrayList<>();
        char[] ca = new char[s.length()];

        convert(s, 0, ca);
        
        Collections.sort(allCombo);
        return allCombo;
    }

    private void convert(String s, int i, char[] ca) {
        if (i == s.length()) {
            allCombo.add(new String(ca));
            return;
        }
        char c = s.charAt(i);
        switch (c) {
            case '0':
                ca[i] = '0';
                convert(s, i + 1, ca);
                break;
            case '1':
                ca[i] = '1';
                convert(s, i + 1, ca);
                break;
            case '2':
                ca[i] = 'a';
                convert(s, i + 1, ca);
                ca[i] = 'b';
                convert(s, i + 1, ca);
                ca[i] = 'c';
                convert(s, i + 1, ca);
                break;
            case '3':
                ca[i] = 'd';
                convert(s, i + 1, ca);
                ca[i] = 'e';
                convert(s, i + 1, ca);
                ca[i] = 'f';
                convert(s, i + 1, ca);
                break;
            case '4':
                ca[i] = 'g';
                convert(s, i + 1, ca);
                ca[i] = 'h';
                convert(s, i + 1, ca);
                ca[i] = 'i';
                convert(s, i + 1, ca);
                break;
            case '5':
                ca[i] = 'j';
                convert(s, i + 1, ca);
                ca[i] = 'k';
                convert(s, i + 1, ca);
                ca[i] = 'l';
                convert(s, i + 1, ca);
                break;
            case '6':
                ca[i] = 'm';
                convert(s, i + 1, ca);
                ca[i] = 'n';
                convert(s, i + 1, ca);
                ca[i] = 'o';
                convert(s, i + 1, ca);
                break;
            case '7':
                ca[i] = 'p';
                convert(s, i + 1, ca);
                ca[i] = 'q';
                convert(s, i + 1, ca);
                ca[i] = 'r';
                convert(s, i + 1, ca);
                ca[i] = 's';
                convert(s, i + 1, ca);
                break;
            case '8':
                ca[i] = 't';
                convert(s, i + 1, ca);
                ca[i] = 'u';
                convert(s, i + 1, ca);
                ca[i] = 'v';
                convert(s, i + 1, ca);
                break;
            case '9':
                ca[i] = 'w';
                convert(s, i + 1, ca);
                ca[i] = 'x';
                convert(s, i + 1, ca);
                ca[i] = 'y';
                convert(s, i + 1, ca);
                ca[i] = 'z';
                convert(s, i + 1, ca);
                break;
        }
    }
}
