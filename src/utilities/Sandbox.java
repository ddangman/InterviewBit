/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class Sandbox {

    private ArrayList<Integer> index;
    private int n, sum;
    private ArrayList<Integer> sorted;
    private ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    private int[][][] dp; // index, sum, size

    public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> a) {
        if (a == null || a.isEmpty()) {
            return res;
        }
        init(a);

        for (int sz = 1; sz < n; sz++) {
            int ss = sz * sum;
            if (ss % n != 0) {
                continue;
            }

            int sm = ss / n;
            if (isPossible(0, sm, sz)) {
                generateRes();
                break;
            }
        }

        return res;
    }

    private boolean isPossible(int i, int sm, int sz) {
        if (sz == 0) {
            return sm == 0;
        }
        if (i >= n) {
            return false;
        }
        if (dp[i][sm][sz] != 0) {
            return dp[i][sm][sz] == 1;
        }

        // recursion using element
        int element = sorted.get(i);
        if (sm >= element) {
            index.add(i);
            if (isPossible(i + 1, sm - element, sz - 1)) {
                dp[i][sm][sz] = 1;
                return true;
            }
            index.remove(index.size() - 1);
        }

        // recursion without element
        if (isPossible(i + 1, sm, sz)) {
            dp[i][sm][sz] = 1;
            return true;
        }

        dp[i][sm][sz] = -1;
        return false;
    }

    private void generateRes() {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        int ptr = 0;
        for (int i = 0; i < n; i++) {
            if (ptr < index.size() && i == index.get(ptr)) {
                a.add(sorted.get(i));
                ptr++;
            } else {
                b.add(sorted.get(i));
            }
        }

        res.add(a);
        res.add(b);
    }

    private void init(ArrayList<Integer> a) {
        this.index = new ArrayList<Integer>();
        this.n = a.size();
        Collections.sort(a);
        this.sorted = a;
        for (int i : sorted) {
            sum += i;
        }
        this.dp = new int[n][sum][n];

    }
}
