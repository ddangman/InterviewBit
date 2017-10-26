/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Dang
 */
public class SubsetRemovingDuplicates {

    private ArrayList<ArrayList<Integer>> allCombos;
    private ArrayList<Integer> input;
    private int n;

    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> a) {
        Collections.sort(a);
        allCombos = new ArrayList<>();
        n = a.size();
        input = a;
        ArrayList<Integer> build = new ArrayList<>();

        findSubsets(0, build);

        Collections.sort(allCombos, new Comparator<List<Integer>>() {

            @Override
            public int compare(List<Integer> a, List<Integer> b) {
                int size = Math.min(a.size(), b.size());
                for (int i = 0; i < size; i++) {
                    int cmp = Integer.compare(a.get(i), b.get(i));
                    if (cmp != 0) {
                        return cmp;
                    }
                }
                return Integer.compare(a.size(), b.size());
            }
        });

        return allCombos;
    }

    private void findSubsets(int i, ArrayList<Integer> build) {
        if (i == n) {
            allCombos.add(new ArrayList<>(build));
            return;
        }

        int uniqueI = i + 1;
        while (uniqueI < n && input.get(i) == input.get(uniqueI)) {
            uniqueI++;
        }

        int diff = uniqueI - i;

        // recursion to add j number of duplicates
        // with 0 <= j <= diff
        for (int j = 0; j <= diff; j++) {
            for (int k = 0; k < j; k++) {
                build.add(input.get(i));
            }

            findSubsets(uniqueI, build);

            for (int k = 0; k < j; k++) {
                build.remove(build.size() - 1);
            }
        }
    }

    public class HashSolution {

        HashSet<ArrayList<Integer>> hs;
        ArrayList<Integer> input;
        int n;

        public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> a) {
            Collections.sort(a);
            ArrayList<ArrayList<Integer>> allCombos = new ArrayList<>();
            hs = new HashSet<>();
            n = a.size();
            input = a;
            ArrayList<Integer> build = new ArrayList<>();

            findSubsets(0, build);

            for (ArrayList<Integer> subset : hs) {
                allCombos.add(subset);
            }
            Collections.sort(allCombos, (o1, o2) -> {
                int min = o1.size() > o2.size() ? o2.size() : o1.size();
                for (int i = 0; i < min; i++) {
                    int j = o1.get(i).compareTo(o2.get(i));
                    if (j != 0) {
                        return j;
                    }
                }
                if (o1.size() == min) {
                    return -1;
                }
                return 1;
            });
            return allCombos;
        }

        private void findSubsets(int i, ArrayList<Integer> build) {
            if (i == n) {
                hs.add(new ArrayList<>(build));
                return;
            }

            build.add(input.get(i));
            findSubsets(i + 1, build);

            build.remove(build.size() - 1);
            findSubsets(i + 1, build);
        }
    }

}
