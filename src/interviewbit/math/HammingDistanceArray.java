package interviewbit.math;

import java.util.List;

/**
 *
 * @author Dang
 */
public class HammingDistanceArray {

    public int hammingDistance(final List<Integer> A) {
        int sum = 0;
        int current = 0;
        for (int i = 0; i < A.size(); i++) {
            for (int j = i+1; j < A.size(); j++) {
                if (A.get(i) != A.get(j)) {
                    current = binaryXor(A.get(i), A.get(j));
                    sum += current + current;
                }                
                
            }
        }
        return sum;
    }

    int binaryXor(int a, int b) {
        int c = a ^ b;
        return NumberOfSetBits(c);
    }

    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 1; i < 33; i++) {
            if (getBit(n, i) == true) {
                count++;
            }
        }
        return count;
    }

    public boolean getBit(int n, int i) {
        return (n & (1 << i)) != 0;
    }
    
    int NumberOfSetBits(int i)
{
     // Java: use >>> instead of >>
     // C or C++: use uint32_t
     i = i - ((i >>> 1) & 0x55555555);
     i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
     return (((i + (i >>> 4)) & 0x0F0F0F0F) * 0x01010101) >>> 24;
}

}
