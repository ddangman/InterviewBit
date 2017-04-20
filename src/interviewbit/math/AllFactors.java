/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class AllFactors {

    public ArrayList<Integer> allFactors(int a) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        int sqrt = (int) Math.sqrt(a);
        for (int i = 1; i <= sqrt; i++) {
            if (a % i == 0) {
                if (!factors.contains(i)) {
                    factors.add(i);
                }
                if (!factors.contains(a / i)) {
                    factors.add(a / i);
                }

            }
        }
        Collections.sort(factors);
        return factors;
    }
}
