/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class PalindromePartitioning {

    ArrayList<ArrayList<String>> allPalindromes;
    int n;
    String input;
    ArrayList<String> build;

    public ArrayList<ArrayList<String>> partition(String s) {
        n = s.length();
        allPalindromes = new ArrayList<>();
        this.input = s;
        build = new ArrayList<>();
        findPalindrome(0);
        return allPalindromes;
    }

    public void findPalindrome(int start) {
        if (start == n) {
            allPalindromes.add(new ArrayList<>(build));
        } 

        for (int i = start + 1; i <= n; i++) {
            String s = input.substring(start, i);
            if (isPalindrome(s)) {
                build.add(s);
                findPalindrome(i);
                build.remove(build.size() - 1);
            }  
        }
    }

    public boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }        
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
