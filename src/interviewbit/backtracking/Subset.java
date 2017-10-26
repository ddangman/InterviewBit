/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class Subset {

    private ArrayList<ArrayList<Integer>> allCombos;
    private ArrayList<Integer> input;
    private int n;

    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> a) {
        Collections.sort(a);
        allCombos = new ArrayList<>();
        n = a.size();
        input = a;
        ArrayList<Integer> build = new ArrayList<>();

        findSubsets(0, build);

        Collections.sort(allCombos, (o1, o2) -> {
            if (o1.isEmpty()) {
                return -1;
            } else if (o2.isEmpty()) {
                return 1;
            } else if (o1.get(0) == o2.get(0)) {
                int i = 1;
                while (true) {
                    if (i >= o1.size()) {
                        return -1;
                    } else if (i >= o2.size()) {
                        return 1;
                    }
                    int n = o1.get(i).compareTo(o2.get(i));
                    if (n != 0) {
                        return n;
                    }
                    i++;
                }
            }

            return o1.get(0).compareTo(o2.get(0));
        });
        return allCombos;
    }

    private void findSubsets(int i, ArrayList<Integer> build) {
        if (i == n) {
            allCombos.add(new ArrayList<>(build));
            return;
        }

        build.add(input.get(i));
        findSubsets(i + 1, build);

        build.remove(build.size() - 1);
        findSubsets(i + 1, build);
    }
}
