/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Dang
 */
public class Anagrams {

    public ArrayList<ArrayList<Integer>> anagrams(final List<String> a) {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            String s = a.get(i);
            char[] ca = s.toCharArray();
            // anagrams are equal when letters are sorted
            Arrays.sort(ca);
            String k = new String(ca);
            ArrayList<Integer> v = map.get(k);
            if (v == null) {
                ArrayList<Integer> nv = new ArrayList<>();
                nv.add(i + 1);
                map.put(k, nv);
            } else {
                v.add(i + 1);
            }
        }
        return new ArrayList<>(map.values());
    }
}
