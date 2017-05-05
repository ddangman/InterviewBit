/*
 *
     * Case 1 : Right side mirror palindrome is totally contained under current palindrome.
                It can not expand past boundary of current palindrome
                unless it touches or exceed current palindrome's border.
                In this case do not consider this as center.
                If it does touch boundary of current palindrome,
                make sure it's mirror touches the boundary as well (case 3)
                Exceeding current boundary results in case 4.
                If left palindrome exceeds current, current palindrome stopped
                because next character needed is invalid;
                considering such center will only stop at the same invalid character.
     * Case 2 : Current palindrome is proper suffix of input.
                Terminate the loop in this case.
                No better palindrome will be found on right.
     * Case 3 : Right side palindrome is proper suffix and
                its corresponding left side palindrome
                is proper prefix of current palindrome.
                Meaning current palindrome, minus center char(s)
                makes 2 other palindromes that touches
                left and right edge of current palindrome.
                Since several may exist,
                make largest such point as next center.
                Palindrome must touch right edge so it
                can expand past right edge.
     * Case 4 : Right side palindrome is proper suffix (fits inside right edge)
                but its left corresponding palindrome is
                beyond current palindrome's left edge
                If the right palindrome could expand to mirror the left palindrome,
                the current palindrome already would have expanded
                Do not consider this as center because
                it will not extend at all.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String a) {
        StringBuilder sb = new StringBuilder();
        sb.append('|');
        for (int i = 0; i < a.length(); i++) {
            sb.append(a.charAt(i));
            sb.append('|');
        }
        String bs = sb.toString();

        int max = 1;
        int maxI = 0;
        int[] ca = new int[bs.length()];

        for (int i = 0; i < bs.length();) {
            // left and right edge of center
            int left = i - ca[i] / 2;
            int right = i + ca[i] / 2;
            // expand palindrome from center
            while (left > 0 && right + 1 < bs.length()
                    && bs.charAt(left - 1) == bs.charAt(right + 1)) {
                left--;
                right++;
            }
            ca[i] = right - left + 1;

            // keep best palindrome
            if (ca[i] > max) {
                max = ca[i];
                maxI = i;
            }

            // current palindrome reaches end of array
            if (right == bs.length() - 1) {
                break;
            }

            // default next center
            // if index is even, it is spacing character. Skip it
            int newI = right + (i % 2 == 0 ? 1 : 0);

            // fill out center values for indices from index to right
            // find best center under current palindrome
            for (int j = i + 1; j <= right; j++) {
                // [i - (j - i)] is left mirror of [j]
                // in case left mirror exceeds current palindrome,
                // store distance from k to right of current palindrome
                ca[j] = Math.min(ca[i - (j - i)], 2 * (right - j) + 1);
                // update newCenter when case 3 is found
                // this check will prevent case 1 or 4 is NOT newCenter
                if (j + ca[i - (j - i)] / 2 == right) {
                    // center at k reaches right edge
                    newI = j;
                    break;
                }
            }

            i = newI;
        }
        max /= 2;
        maxI /= 2;
        int start = maxI - (max / 2);
        return a.substring(start, start + max);
    }
}
