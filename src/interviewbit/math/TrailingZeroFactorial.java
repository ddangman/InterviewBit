/*
 * Every time number is multiplied by 10, there is a trailing zero
 * since 5 * 2 = 10, 5 * anyEvenNumber yields a trailing zero
 * since multiples of 2 are so much more abundant than multiples of 5
 * only consider multiples of 5
 * 5^2 has 2 fives, so it must be considered twice
 * 5^n has n fives, so it must be considered n times
 */
package interviewbit.math;

/**
 *
 * @author Dang
 */
public class TrailingZeroFactorial {

    public int trailingZeroes(int a) {
        int res = 0;
        int i = 1;
        while (true){
            int t = a / (int) Math.pow(5, i);
            if (t < 1) {
                break;
            }
            res+=t;
            i++;
        }
        return res;
    }

}
