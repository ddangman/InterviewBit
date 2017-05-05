/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit;

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
}
