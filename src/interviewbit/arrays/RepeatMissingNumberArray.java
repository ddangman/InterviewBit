/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Dang
 */
public class RepeatMissingNumberArray {

    public ArrayList<Integer> repeatedNumber(final List<Integer> a) {
        int xor = a.get(0); // hold xor of all elements and numbers 1 to a.size

        // get xor of all array elements
        for (int i = 1; i < a.size(); i++) {
            xor ^= a.get(i);
        }

        // xor numbers 1 to n
        for (int i = 1; i <= a.size(); i++) {
            xor ^= i;
        }

        // xor is used on all elements and their corresponding index numbers
        // single elements and their matching index number will nulllify each other
        // only missing and double element will remain in xor
        // xor holds the difference in set bit between those two elements
        // so any set bit of xor can be used to divide out ( using bitwise AND )
        // missing or non-nullified elements
        int rsb = xor & ~(xor - 1); // get rightmost set bit (rightmost 1) of xor
                                    // alternatively: rsb = xor & (-xor)
        int x = 0;
        int y = 0;

        // xor double element nullifies each other 
        // xor index loop will nullify single element
        // only index not nulllified will be the double and missing element         
        for (int i = 0; i < a.size(); i++) {
            // divide elements by comparing rightmost set bit of xor
            // with bit at same position in each element.
            int and = a.get(i) & rsb;
            // since & bitwise AND is used,
            // 'and' can either be equal to rsb (same bit set)
            // or 0 (same bit not set) like initial value of x/y 
            if (and == 0) {
                x ^= a.get(i);
            } else {
                y ^= a.get(i);
            }
        }
        // xor index 1 to a.size to nullify single element
        // and manifest missing element and nullified double element
        for (int i = 1; i <= a.size(); i++) {
            int and = i & rsb;
            if (and == 0) {
                x ^= i;
            } else {
                y ^= i;
            }
        }

        // xor doesn't indicate which element the set bit belongs to,
        // double or missing element.
        // Iterate to find first present element as it must be the double element
        for (int i : a) {
            if (i == x) {
                return new ArrayList<>(Arrays.asList(x, y));
            }
            if (i == y) {
                return new ArrayList<>(Arrays.asList(y, x));
            }
        }

        return new ArrayList<>();
    }

    public ArrayList<Integer> repeatedNumberPrint(final List<Integer> a) {
        int xor = a.get(0); // hold xor of all elements and numbers 1 to a.size
        System.out.print("xor element ");
        System.out.println(Integer.toBinaryString(xor));

        // get xor of all array elements
        for (int i = 1; i < a.size(); i++) {
            xor ^= a.get(i);
            System.out.print("xor element ");
            System.out.println(Integer.toBinaryString(xor));
        }

        // xor numbers 1 to n
        for (int i = 1; i <= a.size(); i++) {
            xor ^= i;
            System.out.print("xor i ");
            System.out.println(Integer.toBinaryString(xor));
        }

        // get rightmost set bit
        int rsb = xor & ~(xor - 1);
        System.out.print("Rightmost set bit ");
        System.out.println(Integer.toBinaryString(rsb));
        int x = 0;
        int y = 0;

        // divide elements by comparing rightmost set bit of xor
        // with bit at same position in each element.
        for (int i = 0; i < a.size(); i++) {
            int and = a.get(i) & rsb;
            System.out.print("element and ");
            System.out.println(Integer.toBinaryString(and));
            if (and == 0) {
                x ^= a.get(i);
            } else {
                y ^= a.get(i);
            }
        }
        for (int i = 1; i <= a.size(); i++) {
            int and = i & rsb;
            System.out.print("index and ");
            System.out.println(Integer.toBinaryString(and));
            if (and == 0) {
                x ^= i;
            } else {
                y ^= i;
            }
        }

        System.out.print("return bits ");
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(y));

        // algorithm doesn't differentiate between 0 and double occurance
        // since double xor is the same as no xor
        for (int i : a) {
            if (i == x) {
                return new ArrayList<>(Arrays.asList(x, y));
            }
            if (i == y) {
                return new ArrayList<>(Arrays.asList(y, x));
            }
        }

        return new ArrayList<>();
    }
}
