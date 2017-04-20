package interviewbit.math;

import java.util.ArrayList;

/**
 * 1. Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
 * 2. Initially, let p equal 2, the smallest prime number.
 * 3. Enumerate the multiples of p by counting to n from 2p in increments of p, 
 *    and mark them in the list (these will be 2p, 3p, 4p, ...; 
 *    the p itself should not be marked).
 * 4. Find the first number greater than p in the list that is not marked. 
 *    If there was no such number, stop. 
 *    Otherwise, let p now equal this new number (which is the next prime), 
 *    and repeat from step 3.
 * 5. When the algorithm terminates, 
 *    the numbers remaining not marked in the list are all the primes below n.
 */
public class SieveEratosthenes {

    // Return primes less than limit
    public ArrayList<Integer> sieve(int limit) {
        final int numPrimes = countPrimesUpperBound(limit);
        ArrayList<Integer> primes = new ArrayList<Integer>(numPrimes);
        boolean[] isComposite = new boolean[limit];   // all false
        final int sqrtLimit = (int) Math.sqrt(limit); // floor
        for (int i = 2; i <= sqrtLimit; i++) {
            if (!isComposite[i]) {
                primes.add(i);
                for (int j = i * i; j < limit; j += i) // `j+=i` can overflow
                {
                    isComposite[j] = true;
                }
            }
        }
        for (int i = sqrtLimit + 1; i < limit; i++) {
            if (!isComposite[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    static int countPrimesUpperBound(int max) {
        return max > 1 ? (int) (1.25506 * max / Math.log((double) max)) : 0;
    }

}
