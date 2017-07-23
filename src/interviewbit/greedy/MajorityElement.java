/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.greedy;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Dang
 */
public class MajorityElement {

    public int majorityElement(final List<Integer> a) {
        if (a.size() == 1) {
            return a.get(0);
        }

        int count = -1;
        int major = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < a.size(); i++) {
            int k = a.get(i);
            Integer v = map.get(k);
            if (v == null) {
                map.put(k, 1);
            } else {
                v++;
                map.put(k, v);
                if (v > count) {
                    count = v;
                    major = k;
                }
            }
        }
        return major;
    }

    // Moore's voting algorithm Order(n), Auxiliary space: Order(1)
    // If we cancel out each occurrence of an element e with all the other 
    // elements that are different from e then e will exist till end 
    // if it is a majority element
    public int moore(final List<Integer> A) {

        if (A == null) {
            return -1;
        }

        int major = A.get(0);
        int count = 1;
        int n = A.size();

        // Loop through each element and maintains a count of the element 
        // that has the potential of being the majority element
        for (int i = 1; i < n; i++) {
            // If next element is same then increments the count, 
            // otherwise decrements the count. 
            // If the count reaches 0 then update the potential index 
            // to the current element and reset count to 1
            if (A.get(i) == major) {
                count++;
            } else if (count == 1) {
                major = A.get(i);
            } else {
                count--;
            }
        }

        // solution is guaranteed, otherwise test major
        return major;

        // count major occurances and compare count to criteria
//        count = 0;
//        for (int i = 0; i < n; i++) {
//            if (A.get(i) == major) {
//                count++;
//            }
//        }
//
//        if (count > n / 2) { // majority criteria
//            return major;
//        }
//
//        return -1; // inadequate count
    }
}
