/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Dang
 */
public class DiffkII {

    public int diffPossible(final List<Integer> input, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            int n = input.get(i);
            Integer add = map.get(target + n);
            Integer sub = map.get(n - target);
            if (add != null && add != i) {
                int m = input.get(add);
                if (m - n == target) {
                    return 1;
                }
            } else if (sub != null && sub != i) {
                int m = input.get(sub);
                if (n - m == target) {
                    return 1;
                }
            } else {
                map.put(n, i);
            }
        }
        return 0;
    }
}
