/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class CombinationsRepeatable {

    ArrayList<ArrayList<Integer>> allCombo;
    int n;
    int k;

    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        allCombo = new ArrayList<>();
        ArrayList<Integer> build = new ArrayList<>();
        this.n = n;
        this.k = k;

        generate(build, 0);

        return allCombo;
    }

    public void generate(ArrayList<Integer> build, int level) {
        if (build.size() == k) {
            allCombo.add(new ArrayList<>(build));
            return;
        }

        for (int i = level + 1; i <= n; i++) {
            build.add(i);
            generate(build, i);
            build.remove(build.size() - 1);

        }
    }
}
