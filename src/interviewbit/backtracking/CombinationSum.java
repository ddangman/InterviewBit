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
public class CombinationSum {
    
    private ArrayList<ArrayList<Integer>> allCombos;
    private ArrayList<Integer> input;
    private int target;

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        
        Collections.sort(a);
        removeDuplicates(a);
        allCombos = new ArrayList<>();
        input = a;
        target = b;
        
        generateCombo(0, 0, new ArrayList<>());
        
        return allCombos;
    }
    
    private void generateCombo(int sum, int index, ArrayList<Integer> build) {
        if (sum == target) {
            allCombos.add(new ArrayList<>(build));
            return;
        } else if (sum > target) {
            return;
        } 
        
        for (int i = index; i < input.size(); i++) {
            int n = input.get(i);
            build.add(n);
            generateCombo(sum + n, i, build);
            
            build.remove(build.size() - 1);
        }
    }

    private void removeDuplicates(ArrayList<Integer> a) {
        boolean needSwap = false;
        int copy = a.get(0);
        int i = 0;
        int j = 0;
        while (true) {
            while (j < a.size() && a.get(j) == copy) {
                needSwap = true;
                j++;
            }
            if (j >= a.size()) {
                break;
            }
            copy = a.get(j);
            if (needSwap) {
                i++;
                a.set(i, a.get(j));
                j++;
            } else {
                i++;
                j++;
            }
        }

        while (a.size() > i + 1) {
            a.remove(a.size() - 1);
        }
    }

    public class HashSolution {

        private HashSet<ArrayList<Integer>> set;
        private ArrayList<Integer> input;
        private int target;

        public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
            Collections.sort(a);
            ArrayList<ArrayList<Integer>> allCombo = new ArrayList<>();
            set = new HashSet<>();
            target = b;
            input = a;
            ArrayList<Integer> build = new ArrayList<>();

            generate(build, 0, 0);

            for (ArrayList<Integer> element : set) {
                allCombo.add(element);
            }
            Collections.sort(allCombo, (o1, o2) -> {
                int min = o1.size() > o2.size() ? o2.size() : o1.size();
                int rank;
                for (int i = 0; i < min; i++) {
                    rank = o1.get(i) - o2.get(i);
                    if (rank != 0) {
                        return rank;
                    }
                }
                return o1.size() - o2.size();
            });

            return allCombo;
        }

        private void generate(ArrayList<Integer> build, int sum, int index) {
            if (sum > target) {
                return;
            } else if (sum == target) {
                set.add(new ArrayList<>(build));
                return;
            }

            for (int i = index; i < input.size(); i++) {
                build.add(input.get(i));
                sum += input.get(i);
                generate(build, sum, i);

                sum -= input.get(i);
                build.remove(build.size() - 1);
            }
        }
    }

}
