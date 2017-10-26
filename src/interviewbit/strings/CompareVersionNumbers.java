/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class CompareVersionNumbers {

    public int compareVersion(String a, String b) {
        ArrayList<BigInteger> a1 = generateVersionArray(a);
        ArrayList<BigInteger> a2 = generateVersionArray(b);

        // run comparisons
        int n = 0;
        BigInteger zero = new BigInteger("0");
        if (a1.size() > a2.size()) {
            for (int i = 0; i < a1.size(); i++) {
                if (i >= a2.size()) {
                    // out of a2 range
                    n = a1.get(i).compareTo(zero);
                } else {
                    n = a1.get(i).compareTo(a2.get(i));
                }
                if (n != 0) {
                    return n;
                }
            }
        } else {
            for (int i = 0; i < a2.size(); i++) {
                if (i >= a1.size()) {
                    // out of a1 range
                    n = zero.compareTo(a2.get(i));
                } else {
                    n = a1.get(i).compareTo(a2.get(i));
                }
                if (n != 0) {
                    return n;
                }
            }
        }
        return 0;
    }

    private ArrayList<BigInteger> generateVersionArray(String s) {
        ArrayList<BigInteger> ar = new ArrayList<>();

        int i = 0;
        for (int j = 1; j < s.length(); j++) {
            if (s.charAt(j) == '.') {
                ar.add(new BigInteger(s.substring(i, j)));
                i = j + 1;
            }
        }
        if (i != s.length()) {
            ar.add(new BigInteger(s.substring(i, s.length())));
        }
        return ar;
    }
}
