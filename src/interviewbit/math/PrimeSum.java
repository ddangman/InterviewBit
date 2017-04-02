/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interviewbit.math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class PrimeSum {

    public ArrayList<Integer> primesum(int a) {
        int half = a / 2;
        for (int i = 2; i <= half; i++) {
            if (isPrime(i)) {
                int coPrime = a - i;
                if (isPrime(coPrime)) {
                    ArrayList<Integer> lowestPair = new ArrayList<>(
                            Arrays.asList(i, coPrime));
                    return lowestPair;
                }
            }
        }
        return new ArrayList<>();
    }

    public boolean isPrime(int n) {
        if (n==0||n==1) {
            return false;
        }
        // fast even test.
        if (n > 2 && (n & 1) == 0) {
            return false;
        }
        // only odd factors need to be tested up to n^0.5
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
