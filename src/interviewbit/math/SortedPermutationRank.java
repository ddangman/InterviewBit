/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class SortedPermutationRank {

    public int findRank(String a) {
        ArrayList<Character> chars = new ArrayList<Character>();
        for (char c : a.toCharArray()) {
            chars.add(c);
        }
        ArrayList<Character> sorted = new ArrayList<Character>(chars);
        Collections.sort(sorted);
        int len = sorted.size();
        long res = 1;
        for (int i = 0; i < len; i++) {
            int r = Collections.binarySearch(sorted, chars.get(i));
            sorted.remove(r);
            res += r * factorial(sorted.size());
        }

        return (int) res % 1000003;
    }

    private long factorial(int n) {
        long fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            // prevent bit overflow
            if (fact > 1000003) {
                fact = fact % 1000003;
            }
            fact *= i;
        }
        return fact % 1000003;
    }

    private int binarySearch(char[] a, char key) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            char midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return -(low + 1);  // key not found.
    }

    public int findBigRank(String a) {
        ArrayList<Character> chars = new ArrayList<Character>();
        for (char c : a.toCharArray()) {
            chars.add(c);
        }
        ArrayList<Character> sorted = new ArrayList<Character>(chars);
        Collections.sort(sorted);
        int len = sorted.size();
        BigInteger res = BigInteger.valueOf(0);
        for (int i = 0; i < len; i++) {
            int r = Collections.binarySearch(sorted, chars.get(i));
            sorted.remove(r);
            BigInteger mul = BigInteger.valueOf(r);
            mul = mul.multiply(bigFactorial(sorted.size()));
            res = res.add(mul);
        }
        res = res.remainder(BigInteger.valueOf(1000003));
        return res.intValue();
    }

    private BigInteger bigFactorial(int n) {
        BigInteger fact = BigInteger.valueOf(1); // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }

}
