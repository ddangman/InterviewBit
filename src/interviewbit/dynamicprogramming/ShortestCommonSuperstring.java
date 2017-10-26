/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Duy Dang
 */
public class ShortestCommonSuperstring {

    public int solve(ArrayList<String> A) {
        int n = A.size();
        if (n == 1) {
            return A.get(0).length();
        }

        for (int i = 0; i < n - 1; i++) {
            mergeWords(A);
        }
        return A.get(0).length();
    }

    private void mergeWords(ArrayList<String> A) {
        int w11 = 0, w22 = 0;
        int max = -1;
        boolean isWord2Suffix = false;
        for (int i = 0; i < A.size() - 1; i++) {
            for (int j = i + 1; j < A.size(); j++) {
                String word1 = A.get(i);
                String word2 = A.get(j);

                int currIntersection = getIntersection(word2, word1);
                if (currIntersection > max) {
                    max = currIntersection;
                    w11 = i;
                    w22 = j;
                    isWord2Suffix = false;
                }

                currIntersection = getIntersection(word1, word2);
                if (currIntersection > max) {
                    max = currIntersection;
                    w11 = i;
                    w22 = j;
                    isWord2Suffix = true;
                }
            }
        }
        String merged;
        if (isWord2Suffix) {
            merged = getMerge(A.get(w11), A.get(w22), max);
        } else {
            merged = getMerge(A.get(w22), A.get(w11), max);
        }

        A.remove(w11);
        A.add(w11, merged);
        A.remove(w22);
    }

    private int getIntersection(String word1, String word2) {
        int max = 0;
        for (int i = 0; i <= Math.min(word1.length(), word2.length()); i++) {
            if (isValidIntersection(word1, word2, i)) {
                max = i;
            }
        }
        return max;
    }

    private boolean isValidIntersection(String word1, String word2, int offset) {
        for (int i = 0; i < offset; i++) {
            if (word1.charAt(word1.length() - offset + i) != word2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private String getMerge(String word1, String word2, int max) {
        word2 = word2.substring(max);
        word1 += word2;
        return word1;
    }
}
