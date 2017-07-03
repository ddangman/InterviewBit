/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class MinimumCharactersToBePalindromic {

    // Knuth Morris Pratt longest prefix-suffix pattern matching
    // The basic idea behind KMP’s algorithm is: 
    // whenever we detect a mismatch (after some matches), 
    // we already know some of the characters in the text of next window. 
    // We take advantage of this information to avoid matching 
    // the characters that we know will anyway match.
    public int mirroredStringKMP(String a) {
        // take the string and reverse of a string and 
        // combine them with a sentinal character in between them
        StringBuilder sb = new StringBuilder(a);
        sb.append("|");
        sb.append(new StringBuilder(a).reverse().toString());
        String mirror = sb.toString();

        // Each index of LPS array contains the longest prefix 
        // which is also a suffix
        int[] lps = new int[mirror.length()];

        // compute the LPS array of this combined string
        int i = 1; // iteration index
        int j = 0; // matching index
        while (i < mirror.length()) {
            if (mirror.charAt(i) == mirror.charAt(j)) {
                // increment matching index so when rollback occurs, 
                // char after matching prefix-suffix is compared
                j++;                
                lps[i] = j; // store index to roll back if mismatch
                i++;
            } else if (j != 0) {
                // roll back matching index to last matching prefix-suffix +1
                j = lps[j - 1];
            } else {
                // matching index is 0, increment iteration index
                i++;
            }
        }

        // take the last value of the LPS array and 
        // subtract it with the length of the original string
        //This will give us the minimum number of insertions 
        //required in the begining of the string
        return a.length() - lps[lps.length - 1];
    }

    // One way to find a palindrome is to start from the center of the string 
    // and compare characters in both directions one by one. 
    // If corresponding characters on both sides 
    // (left and right of the center) match, then they will make a palindrome.
    public int modifiedManacher(String a) {
        // use reverse string in Manacher's algorithm
        StringBuilder sb = new StringBuilder();
        sb.append('|');
        for (int i = a.length() - 1; i >= 0; i--) {
            sb.append(a.charAt(i));
            sb.append('|');
        }
        String rs = sb.toString(); // reverse string
        
        int max = 1; // max palindrome length
        int[] ca = new int[rs.length()]; // center array
        
        for (int i = 0; i < rs.length();) {
            // calculate left and right edge of center
            int left = i - ca[i] / 2;
            int right = i + ca[i] / 2;
            // expand palindrome from center
            while (left > 0 && right + 1 < rs.length()
                    && rs.charAt(left - 1) == rs.charAt(right + 1)) {
                left--;
                right++;
            }
            ca[i] = right - left + 1;

            // current palindrome reaches end of array
            if (right == rs.length() - 1) {
                // keep best palindrome touching right edge
                if (ca[i] > max) {
                    max = ca[i];
                }                
                break;
            }

            // default next center
            // if index is even, it is spacing character. Skip it
            int newI = right + (i % 2 == 0 ? 1 : 0); // new index

            // fill out center values for indices from index to right
            // find best center under current palindrome
            for (int j = i + 1; j <= right; j++) {
                // [i - (j - i)] is left mirror of [j]
                // in case left mirror exceeds current palindrome,
                // store distance from j to right of current palindrome
                ca[j] = Math.min(ca[i - (j - i)], 2 * (right - j) + 1);
                // update newCenter when case 3 is found
                // this check will prevent case 1 or 4 is NOT newCenter
                if (j + ca[i - (j - i)] / 2 == right) {
                    // center at j reaches right edge
                    newI = j;
                    break;
                }
            }
            
            i = newI;
        }
        return (rs.length() - max) / 2;
    }
}
