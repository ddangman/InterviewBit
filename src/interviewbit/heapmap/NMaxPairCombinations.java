/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.heapmap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 *
 * @author Dang
 */
public class NMaxPairCombinations {

    private class Index {

        public int first;
        public int second;

        public Index(int f, int s) {
            first = f;
            second = s;
        }
    }

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        // sort in ascending order
        Collections.sort(A, (o1, o2) -> o2 - o1);
        Collections.sort(B, (o1, o2) -> o2 - o1);
        // stores solution
        ArrayList<Integer> solution = new ArrayList<>();
        // key is largest sum
        // value is queue of indices to be added back into map
        TreeMap<Integer, Queue<Index>> tm = new TreeMap<>();
        // tracks indices to avoid duplications
        HashSet<String> set = new HashSet<>();

        int N = A.size();

        // seed map for loop
        ArrayDeque<Index> deck = new ArrayDeque<>();
        deck.add(new Index(0, 0));
        tm.put(A.get(0) + B.get(0), deck);
        set.add("0$0");
        // loop until solution is correct size
        for (int i = 0; i < N; i++) {
            // access largest sum
            Entry<Integer, Queue<Index>> le = tm.lastEntry();
            Integer key = le.getKey();
            // add largest sum to solution
            solution.add(key);
            // remove to prevent duplicate entry
            Queue<Index> qi = le.getValue();
            Index idx = qi.poll();
            if (qi.isEmpty()) {
                tm.remove(key);
            }
            // indices added to solution
            int ai = idx.first;
            int bi = idx.second;

            /* add next index from both arrays to map if not already in map */
            if (ai + 1 < N && bi < N && !set.contains((ai + 1) + "$" + bi)) {
                int ax = ai + 1;
                int bx = bi;
                // add sum, Index to map
                while (bx >= 0) {
                    if (!set.contains(ax + "$" + bx)) {
                        int sum = A.get(ax) + B.get(bx);
                        Queue<Index> q = tm.get(sum);
                        if (q != null) {
                            q.add(new Index(ax, bx));
                        } else {
                            ArrayDeque<Index> nad = new ArrayDeque<>();
                            nad.add(new Index(ax, bx));
                            tm.put(sum, nad);
                        }
                        set.add(ax + "$" + bx);
                        bx--;
                    } else {
                        // no need to continue since all indices 
                        // lower than current is already processed
                        break;
                    }
                }
            }
            if (ai < N && bi + 1 < N && !set.contains(ai + "$" + (bi + 1))) {
                int ax = ai;
                int bx = bi + 1;
                while (ax >= 0) {
                    if (!set.contains(ax + "$" + bx)) {
                        int sum = A.get(ax) + B.get(bx);
                        Queue<Index> q = tm.get(sum);
                        if (q != null) {
                            q.add(new Index(ax, bx));
                        } else {
                            ArrayDeque<Index> nad = new ArrayDeque<>();
                            nad.add(new Index(ax, bx));
                            tm.put(sum, nad);
                        }
                        set.add(ax + "$" + bx);
                        ax--;
                    } else {
                        break;
                    }
                }
            }
        }

        return solution;
    }

    // brute force
    public ArrayList<Integer> solveBF(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A, (o1, o2) -> o2 - o1);
        Collections.sort(B, (o1, o2) -> o2 - o1);
        ArrayList<Integer> solution = new ArrayList<>();
        HashSet<String> set = new HashSet<>();

        int N = A.size();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!set.contains(i + "$" + j)) {
                    solution.add(A.get(i) + B.get(j));
                    set.add(i + "$" + j);
                }
            }
        }

        Collections.sort(solution, (o1, o2) -> o2 - o1);
        while (solution.size() != N) {
            solution.remove(solution.size() - 1);
        }
        return solution;
    }

    public ArrayList<Integer> solveStream(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);

        return IntStream.range(0, A.size()).mapToObj(i -> A.get(i) + B.get(i)).collect(Collectors.toCollection(ArrayList::new));
    }
}
