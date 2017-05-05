/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

/**
 *
 * @author Dang
 */
public class SquareRootInteger {

    public int sqrt(int a) {
        // anything higher will cause bit overflow
        if (a >= 2147395600) {
            return 46340;
        }
        
        int low = 1;
        int high = 46339 > a ? a : 46339;
        int mid = 0;
        int sq = 1;
        int nSq = 1;
        while (low <= high) {
            mid = low + ((high - low) / 2);
            sq = mid * mid;
            nSq = (mid + 1) * (mid + 1);
            if (sq == a) {
                return mid;
            }
            if (nSq == a) {
                return mid + 1;
            }
            if (sq < a) {
                if (a < nSq) {
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        return mid;
    }
}
