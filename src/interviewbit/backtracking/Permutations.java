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
public class Permutations {

    private int size;
    private boolean[] used;
    private ArrayList<ArrayList<Integer>> allCombo;
    private ArrayList<Integer> input;

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
        size = a.size();
        used = new boolean[size];
        allCombo = new ArrayList<>();
        this.input = a;
        ArrayList<Integer> build = new ArrayList<>(Collections.nCopies(size, 0));
        findCombo(0, build);
        return allCombo;
    }

    private void findCombo(int level, ArrayList<Integer> build) {
        if (level == size) {
            allCombo.add(new ArrayList<>(build));
        }

        for (int i = 0; i < size; i++) {
            if (!used[i]) {
                used[i] = !used[i];
                build.set(level, input.get(i));
                findCombo(level + 1, build);
                used[i] = !used[i];
            }
        }
    }
}
