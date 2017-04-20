/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.List;

/**
 *
 * @author Dang
 */
public class N3RepeatNumber {

    public int repeatedNumber(final List<Integer> a) {
        double count = 0;
        int majorElement = 0;
        double min = a.size() / 3.0;

        // Moore's voting algorithm Order(n), Auxiliary space: Order(1)
        // step:1-To get candidate for the majority element
        for (int i = 0; i < a.size(); i++) {
            if (count == 0) {
                majorElement = a.get(i);
            }
            if (a.get(i) == majorElement) {
                count++;
            } else {
                count--;
            }
            if (count > min) {
                return majorElement;
            }
        }

        // Step:2- To check whether this candidate occurs max no. of times 
        count = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == majorElement) {
                count++;
            }
        }

        if (count > min) {
            return majorElement;
        }
        return -1;
    }

    public int dualMajorityElement(List<Integer> nums) {
        double min = nums.size() / 3.0;

        Integer n1 = null, n2 = null;
        int c1 = 0, c2 = 0;

        for (int i : nums) {
            if (n1 != null && i == n1.intValue()) {
                c1++;
            } else if (n2 != null && i == n2.intValue()) {
                c2++;
            } else if (c1 == 0) {
                c1 = 1;
                n1 = i;
            } else if (c2 == 0) {
                c2 = 1;
                n2 = i;
            } else {
                c1--;
                c2--;
            }
        }

        c1 = c2 = 0;

        for (int i : nums) {
            if (i == n1.intValue()) {
                c1++;
            } else if (i == n2.intValue()) {
                c2++;
            }
        }

        if (c1 > min) {
            return n1;
        }
        if (c2 > min) {
            return n2;
        }

        return -1;
    }
}
