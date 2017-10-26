/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.greedy;

import java.util.List;

/**
 *
 * @author Dang
 */
public class GasStation {

    public int canCompleteCircuit(final List<Integer> gas, final List<Integer> cost) {
        if (gas.size() == 1) { // corner case
            return gas.get(0) - cost.get(0) >= 0 ? 0 : 1;
        }

        int n = gas.size();
        long inTank = 0;
        int[] consumption = new int[n]; // holds net fuel from each station

        for (int i = 0; i < n; i++) {
            consumption[i] = gas.get(i) - cost.get(i);
            inTank += consumption[i];
        }

        if (inTank < 0) {
            return -1; // no loop possible
        }

        // find lowest index that can complete loop 
        // without negative fuel inTank
        int index = 0;
        inTank = consumption[0];
        int i = 1;
        while (i != index) {
            while (inTank < 0) {
                // subtract index consumption, and increment it
                inTank -= consumption[index++];
                // no modulus needed since index should never exceed i
                // inTank will be zeroed when index == i - 1
            }
            inTank += consumption[i++];

            // modulus to allow array looping
            index %= n;
            i %= n;
        }
        return index;
    }
}
