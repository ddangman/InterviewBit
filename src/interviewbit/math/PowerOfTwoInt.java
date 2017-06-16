package interviewbit.math;

/**
 *
 * @author Dang
 */
public class PowerOfTwoInt {

    public boolean isPower(int a) {
        if (a == 1) {
            return true;
        }
        int p = 2; //power
        int b = 2; // base
        int sqrt = floorSqrt(a);
        for (; b <= sqrt;) {
            int res = (int) Math.pow(b, p);
            if (res == a) {
                return true;
            } else if (res > a) {
                p = 2;
                b++;
            } else {
                p++;
            }
        }
        return false;
    }

    public static int floorSqrt(int x) {
        // Base Cases
        if (x == 0 || x == 1) {
            return x;
        }

        // Do Binary Search for floor(sqrt(x))
        int start = 1, end = x, ans = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            // If x is a perfect square
            if (mid * mid == x) {
                return mid;
            }

            // Since we need floor, we update answer when mid*mid is
            // smaller than x, and move closer to sqrt(x)
            if (mid * mid < x) {
                start = mid + 1;
                ans = mid;
            } else // If mid*mid is greater than x
            {
                end = mid - 1;
            }
        }
        return ans;
    }

}
