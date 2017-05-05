/*
 * This code was created by another InterviewBit user. Corrections are made by me.
 */
package interviewbit.math;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class DebugUnderKNDigits {

    public int solve(ArrayList<Integer> A, int B, int C) {
        // corner case
        if (A.isEmpty()) {
            return 0;
        }        
        int len = length(C);
        int[] Carray = new int[len];
        int t = C;
        for (int i = 0; i < len; i++) {
            Carray[len - i - 1] = t % 10;
            t /= 10;
        }
        if (B < len) {
//            if (A.get(0) != 0 || B == 1) {
//                return (int) Math.pow(A.size(), B);
//            } else {
//                return (int) Math.pow(A.size(), B - 1) * A.size() - 1;
//            }
            // edge case: can't use 0 as 1st digit
            if (A.get(0) == 0) {
                if (B == 1) { // all digits are valid answer
                    return A.size();
                } else { //remove leading 0 combinations
                    return (int) Math.pow(A.size(), B-1) * (A.size()-1);
                }               
            } else{
                return (int) Math.pow(A.size(), B);
            } 
        }
        if (B > len) {
            return 0;
        } else { // B == len
            int num = 0;
            int[] smaller = new int[len];
            int temp = C;
            for (int i = 0; i < len; i++) {
                smaller[len - i - 1] = findSmaller(A, temp % 10);
                temp /= 10;
            }
            
            // edge case: can't use 0 as 1st digit moved
            // removed i==0
            if (A.get(0) == 0 && len != 1 ) {
                int s = smaller[0];
                s--;
                smaller[0] = s;
            }
//            for (int i = 0; i < len; i++) {
//                if (smaller[i] == 0) {
//                    if (!A.contains(Carray[i])) {
//                        return num;
//                    }
//                } else {
//                    // edge case: can't use 0 as 1st digit
//                    if (A.get(0) == 0 && len != 1 && i==0) {
//                        int s = smaller[0];
//                        s--;
//                        smaller[0] = s;
//                    }
//                    // include the case where digit is not in array
//                    if (A.contains(Carray[i])) {
//                        // calculate partial combinations!!!!
//                        int add = (int) Math.pow(A.size(), len - 1 - i) * (smaller[i]);
//                        num += add;                  
//                    } else { // all possible combinations, correct
//                        int add = (int) Math.pow(A.size(), len - 1 - i) * smaller[i];
//                        num += add;
//                    }
//                }
//
//            }
            return recursion(0, len, smaller, A, Carray);

        }

    }
    
    public int recursion(int i, int len, int[] smaller, ArrayList<Integer> A, int[] Carray){
        if (i == len) {
            return 0;
        }        
        if (A.contains(Carray[i])) {
            // calculate partial combinations!!!!
            int add = (int) Math.pow(A.size(), len - 1 - i) * (smaller[i]);
            return add + recursion(i +1, len, smaller, A, Carray);
        } else { // all possible combinations, correct
            int add = (int) Math.pow(A.size(), len - 1 - i) * smaller[i];
            return add;
        }
    }

    public int findSmaller(ArrayList<Integer> A, int a) {
        int count = 0;
        for (int i : A) {
            if (i < a) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public int length(int C) {
        int count = 0;
        while (C != 0) {
            C = C / 10;
            count++;
        }
        return count;
    }
}
