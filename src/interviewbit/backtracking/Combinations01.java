/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author Dang
 */
public class Combinations01 {

    private ArrayList<Integer> input;
    private ArrayList<ArrayList<Integer>> allCombo;

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        allCombo = new ArrayList<>();
        input = a;
        Collections.sort(input);

        findCombo(0, b, new ArrayList<>());

        return allCombo;
    }

    private void findCombo(int index, int target, ArrayList<Integer> build) {
        if (target < 0) {
            return;
        }
        if (0 == target) {
            allCombo.add(new ArrayList<>(build));
            return;
        }

        for (int i = index; i < input.size(); i++) {
            // skip duplicates while allowing original value
            // without Index Out Of Bounds
            if (i > index && input.get(i) == input.get(i - 1)) {
                continue;
            }
            int n = input.get(i);
            build.add(n);
            findCombo(i + 1, target - n, build);

            build.remove(build.size() - 1);
        }
    }

    class HashSolution {

        private HashSet<ArrayList<Integer>> hs;
        private ArrayList<Integer> input;
        private int target;

        public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
            Collections.sort(a);
            this.input = a;
            this.target = b;
            this.hs = new HashSet<>();
            ArrayList<ArrayList<Integer>> allCombo = new ArrayList<>();

            findCombo(0, 0, new ArrayList<>());

            for (ArrayList<Integer> element : hs) {
                allCombo.add(element);
            }
            return allCombo;
        }

        private void findCombo(int index, int sum, ArrayList<Integer> build) {
            if (sum > target || index >= input.size()) {
                return;
            } else if (sum == target) {
                hs.add(new ArrayList<>(build));
                return;
            }

            for (int i = index; i < input.size(); i++) {
                int n = input.get(i);
                build.add(n);
                findCombo(i + 1, sum + n, build);

                build.remove(build.size() - 1);
            }
        }
    }
}
