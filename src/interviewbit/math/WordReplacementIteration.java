/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interviewbit.math;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class WordReplacementIteration {
    public ArrayList<String> fizzBuzz(int A) {
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 1; i <= A; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }

}
