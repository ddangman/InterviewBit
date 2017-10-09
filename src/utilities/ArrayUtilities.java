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
        ArrayList<Integer> build = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            build.add(i * increment);
        }
        return build;
    }

    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println("");
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    public static void printArray(long[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    public static void printArray(String[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    public static void printArray(boolean[] a) {
        for (int j = 0; j < a.length; j++) {
            if (a[j]) {
                System.out.print("1 ");
            } else {
                System.out.print("0 ");
            }
        }
        System.out.println("");
    }

    public static void printMatrix(ArrayList<ArrayList<Integer>> m) {
        for (int i = 0; i < m.size(); i++) {
            for (int j = 0; j < m.get(i).size(); j++) {
                System.out.print(m.get(i).get(j) + " ");
            }
            System.out.println("");
        }
    }

    public static ArrayList<ArrayList<Character>> stringArrayToCharMatrix(ArrayList<String> stAr) {
        ArrayList<ArrayList<Character>> charMatrix = new ArrayList<>();
        stAr.stream().map((s) -> {
            ArrayList<Character> ca = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                ca.add(s.charAt(i));
            }
            return ca;
        }).forEachOrdered((ca) -> {
            charMatrix.add(ca);
        });
        return charMatrix;
    }
}
