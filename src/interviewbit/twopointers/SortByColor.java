/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class SortByColor {

    public void sortColors(ArrayList<Integer> al) {
        int s = 0;
        int e = al.size() - 1;
        int m = 1;
        while (m < e) {
            // increment start pointer
            while (al.get(s) != 1) {
                if (al.get(s) == 2) {
                    al.set(s, al.get(e));
                    al.set(e--, 2);
                } else {
                    s++;
                }
            }
            // decrement start pointer
            while (al.get(e) != 1) {
                if (al.get(e) == 0) {
                    al.set(e, al.get(s));
                    al.set(s++, 0);
                } else {
                    e--;
                }
            }

            while (al.get(s) == al.get(e)) {
                if (m < s) {
                    m = s + 1;
                }
                if (al.get(m) == 0) {
                    al.set(m, al.get(s));
                    al.set(s++, 0);
                } else if (al.get(m) == 2) {
                    al.set(m, al.get(e));
                    al.set(e--, 2);
                } else {
                    m++;
                }
                if (m >= e) {
                    break;
                }
            }
        }
    }

    public void sortNumbers(ArrayList<Integer> A) {

        int zero = 0;
        int two = A.size() - 1;

        for (int i = 0; i <= two;) {
            if (A.get(i) == 0) {
                int temp = A.get(zero);
                A.set(zero, 0);
                A.set(i, temp);
                zero++;
                i++;
            } else if (A.get(i) == 2) {
                int temp = A.get(two);
                A.set(two, 2);
                A.set(i, temp);
                two--;
            } else {
                i++;
            }
        }

    }
}
