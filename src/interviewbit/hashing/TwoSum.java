/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Dang
 */
public class TwoSum {

    public ArrayList<Integer> twoSum(final List<Integer> a, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> solution = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            int val = a.get(i);
            int find = target - val;
            Integer iMap = map.get(find);
            if (iMap != null) {
                solution.add(iMap + 1);
                solution.add(i + 1);
                break;
            } else if (!map.containsKey(val)) {
                map.put(val, i);
            }
        }
        return solution;
    }
}
