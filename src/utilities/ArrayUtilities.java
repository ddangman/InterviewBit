/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package utilities;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class ArrayUtilities {

    public static ArrayList<Integer> generateNumberList(int length, int increment) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            numbers.add(i * increment);
        }
        return numbers;
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    public static void printIntegerMatrix(ArrayList<ArrayList<Integer>> m) {
        for (int i = 0; i < m.size(); i++) {
            for (int j = 0; j < m.get(i).size(); j++) {
                System.out.print(m.get(i).get(j) + " ");
            }
            System.out.println("");
        }
    }

    public static ArrayList<ArrayList<Character>> stringArrayToCharMatrix(ArrayList<String> stAr) {
        ArrayList<ArrayList<Character>> charMatrix = new ArrayList<>();
        for (String s : stAr) {
            ArrayList<Character> ca = new ArrayList<Character>();
            for (int i = 0; i < s.length(); i++) {
                ca.add(s.charAt(i));
            }
            charMatrix.add(ca);
        }
        return charMatrix;
    }
}
