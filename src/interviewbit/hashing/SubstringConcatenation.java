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
public class SubstringConcatenation {

    public ArrayList<Integer> findSubstring(String a, final List<String> b) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = b.get(0).length();

        // initialize hashmap
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : b) {
            map.compute(s, (k, v) -> {
                if (v != null) {
                    return v + 1;
                }
                return 1;
            });
        }

        for (int i = 0, j = n * b.size(); j <= a.length(); i++, j++) {
            HashMap<String, Integer> vmap = new HashMap<>();
            for (int k = i; k < (n * b.size()) + i; k += n) {
                String valid = a.substring(k, k + n);
                if (!map.containsKey(valid)) {
                    break;
                } else {
                    vmap.compute(valid, (key, v) -> {
                        if (v != null) {
                            return v + 1;
                        }
                        return 1;
                    });
                }
            }
            if (vmap.equals(map)) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<Integer> findSubstringHashTwice(String a, final List<String> b) {
        ArrayList<Integer> result = new ArrayList<>();

        // initialize hashmap
        int prime = 101;
        int n = b.get(0).length();
        long sumHash = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : b) {
            long hash = 0;
            for (int i = 0; i < n; i++) {
                hash += s.charAt(i) * Math.pow(prime, i);
            }
            map.compute(s, (k, v) -> {
                if (v != null) {
                    return v + 1;
                }
                return 1;
            });
            sumHash += hash;
        }

        for (int i = 0, j = n * b.size(); j <= a.length(); i++, j++) {
            long loopHash = 0;
            int mod = 0;
            for (int x = i; x < j; x++) {
                loopHash += a.charAt(x) * Math.pow(prime, mod++ % n);
            }

            VALIDATE:
            if (loopHash == sumHash) {
                HashMap<String, Integer> vmap = new HashMap<>();
                for (int k = i; k < (n * b.size()) + i; k += n) {
                    String valid = a.substring(k, k + n);
                    if (!map.containsKey(valid)) {
                        break VALIDATE;
                    } else {
                        vmap.compute(valid, (key, v) -> {
                            if (v != null) {
                                return v + 1;
                            }
                            return 1;
                        });
                    }
                }
                if (vmap.equals(map)) {
                    result.add(i);
                }
            }
        }
        return result;
    }
}
