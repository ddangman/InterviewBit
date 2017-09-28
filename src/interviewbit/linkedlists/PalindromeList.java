/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.linkedlists;

import interviewbit.datastructures.ListNode;

/**
 *
 * @author Dang
 */
public class PalindromeList {

    public int lPalin(ListNode A) {
        StringBuilder sb = new StringBuilder();
        while (A != null) {
            String s = String.valueOf(A.val);
            sb.append(s);
            // mirror string if it has more than one char
            if (s.length() > 1) {
                sb.append(new StringBuilder(s).reverse().toString());
            }            
            A = A.next;
        }
        
        return checkString(sb.toString());
    }
    
    public int checkString(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(r) == s.charAt(l)) {
                l++;
                r--;
            } else {
                return 0;
            }           
        }
        return 1;
    }
}
