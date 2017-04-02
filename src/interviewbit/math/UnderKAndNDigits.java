/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interviewbit.math;

import java.util.ArrayList;
import java.util.Collections;

public class UnderKAndNDigits {
    public int solve(ArrayList<Integer> A, int B, int C) {
        // corner case: empty array
        if (A.isEmpty()) {
            return 0;
        }        
        int length = (int) (Math.log10(C) + 1);
        
        // B is greater than C.length
        if (B > length) {
            return 0;
        }
        int digits = A.size(); // digits in A
        // B is smaller than C.length
        if (B < length) {
            // edge case: can't use 0 as 1st digit
            if (A.get(0) == 0) {
                if (B == 1) { // all digits are valid answer
                    return digits;
                } else { //remove leading 0 combinations
                    return (int) Math.pow(digits, B-1) * (digits-1);
                }               
            } else{
                return (int) Math.pow(digits, B);
            }    
        }
        
        // B is equal to C.length
        int index = 0;
        int remSlots = length -1; //remaining slots of digit to calculate
        ArrayList<Integer> ca = intToArray(C); //array of c digits
        //holds number of elements from A lower than ca at same index
        ArrayList<Integer> el = new ArrayList<Integer>(); //elements lower
        //initialize elements lower array
        for (int i = 0; i < length; i++) {
            el.add(binSearchLessThan(A, ca.get(i)));
        }
        // edge case: can't use 0 as 1st digit
        if (A.get(0) == 0 && length !=1) {
            el.set(0, el.get(0)-1);
        }
        //get ans using recursion
        return recursionCalculator(index, digits, remSlots, A, ca, el);
    }
    
    public int recursionCalculator(int index, int digits, int remSlots, ArrayList<Integer> A, ArrayList<Integer> ca, ArrayList<Integer> el){
        //base case
        if (remSlots < 0) {
            return 0;
        }
        // digit C at index is not in array A
        // all possible combination is: elements lower * digits ^ remaining slots
        if (binarySearch(A, ca.get(index)) == -1) {
            return el.get(index) * (int) Math.pow(digits, remSlots);
        } else { // add all possible lower combinations to all partial combinations
            return (el.get(index) * (int) Math.pow(digits, remSlots))
                    + recursionCalculator(index+1, digits, remSlots-1, A, ca, el);
        }       
    }
    
    public int binSearchLessThan(ArrayList<Integer> inputArr, int key) {
        int start = 0;
        int end = inputArr.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key == inputArr.get(mid)) {
                return mid;
            }
            //check if 1st less than
            if (inputArr.get(mid) < key) {
                if (mid + 1 < inputArr.size()) {
                    if (inputArr.get(mid + 1) > key) {
                        return mid + 1;
                    }
                } else {
                    return mid + 1;
                }

            }
            if (key < inputArr.get(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }

    public int binarySearch(ArrayList<Integer> inputArr, int key) {

        int start = 0;
        int end = inputArr.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key == inputArr.get(mid)) {
                return mid;
            }
            if (key < inputArr.get(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
    
    public static ArrayList<Integer> intToArray(int i) {
        int temp = i;
        ArrayList<Integer> array = new ArrayList<Integer>();
        do {
            array.add(temp % 10);
            temp /= 10;
        } while (temp > 0);
        Collections.reverse(array);
        return array;
    }
    
}
