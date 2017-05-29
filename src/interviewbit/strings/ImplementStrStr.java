/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class ImplementStrStr {

    // RabinKarp substring search pattern matching
    public int strStr(final String haystack, final String needle) {
        // edge cases
        if (haystack.isEmpty() || needle.isEmpty()) {
            return -1;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        } 
        if (needle.length() == haystack.length()) {
            if (charCompare(haystack, needle, 0)) {
                return 0;
            } else {
                return -1;
            }           
        }        

        int p = 101; // prime number
        long nHash = 0; // needle hash
        long hHash = 0; // haystack hash
        int i = 0; // previous index
        int j = needle.length(); // next index
        int k = 0; // power

        // initialize hash
        for (; k < needle.length(); k++) {
            int n = needle.charAt(k) - 'A';
            int h = haystack.charAt(k) - 'A';
            nHash += n * Math.pow(p, k);
            hHash += h * Math.pow(p, k);
        }
        k--;

        while (true) {
            if (hHash == nHash && charCompare(haystack, needle, i)) {
                return i;
            } else {
                // rolling hash
                if (j == haystack.length()) {
                    return -1;
                }
                // subtract previous char
                hHash -= haystack.charAt(i) - 'A';
                // divide to reduce all powers by 1
                hHash /= p;
                // add next char
                int h = haystack.charAt(j) - 'A';
                hHash += h * Math.pow(p, k);
                i++;
                j++;
            }
        }
    }

    public boolean charCompare(final String haystack, final String needle, int i) {
        int j = 0;
        while (j < needle.length()) {
            if (needle.charAt(j) != haystack.charAt(i)) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
}
