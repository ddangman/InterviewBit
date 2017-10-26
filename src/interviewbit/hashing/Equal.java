/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Dang
 */
public class Equal {

    private class Indices {

        protected int first;
        protected int second;

        Indices(int f, int s) {
            this.first = f;
            this.second = s;
        }
    }

    public ArrayList<Integer> equal(ArrayList<Integer> a) {
        ArrayList<Integer> solution = new ArrayList<>();
        HashMap<Integer, Indices> map = new HashMap<>();

        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                int key = a.get(i) + a.get(j);
                Indices value = map.get(key);
                if (value == null) {
                    map.put(key, new Indices(i, j));
                } else {
                    if (value.first < i && value.second != j && value.second != i) {
                        if (!solution.isEmpty() && 
                                solution.get(0) >= value.first &&
                                solution.get(1) > value.second) {
                            solution.set(0, value.first);
                            solution.set(1, value.second);
                            solution.set(2, i);
                            solution.set(3, j);
                        } else if (solution.isEmpty()) {
                            solution.add(value.first);
                            solution.add(value.second);
                            solution.add(i);
                            solution.add(j);
                        }
                    }
                }
            }
        }
        return solution;
    }
}
