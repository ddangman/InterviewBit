/*
 * Maximum gap will be maximum when all elements are either MIN or MAX making maxgap = MAX - MIN
 * Maximum gap will be minimum when all the elements are equally spaced apart between MIN and MAX
 *    minimumMaxGap: spaceGap = (MAX - MIN) / (size - 1)
 * Since spaceGap is the minimum mathematical MaxGap,
 *    numbers with differences less than spaceGap can be put in the same bucket
 * [MIN, MIN + SpaceGap], [MIN + SpaceGap, MIN + 2SpaceGap]
 * [MIN + K*SpaceGap, MIN + (K+1)*SpaceGap]
 * BucketIndex = (ListValue - MIN) / SpaceGap
      floorDivision will drop remainder less than SpaceGap
 * Only lowest and highest numbers in each Bucket is relevant
 * 
 */
package interviewbit.arrays;

import java.util.List;

/**
 *
 * @author Dang
 */
public class MaxConsecutiveGap {

    class Bucket {

        int low;
        int high;

        public Bucket() {
            low = -1;
            high = -1;
        }
    }

    public int maximumGap(final List<Integer> a) {
        if (a.size() < 2) {
            return 0;
        }

        // get min and max value in array
        int max = a.get(0);
        int min = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            max = max > a.get(i) ? max : a.get(i);
            min = min < a.get(i) ? min : a.get(i);
        }

        // initialize array of bucket
        Bucket[] buckets = new Bucket[a.size() + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }

        // sort every number into bucket array
        double spaceGap = (double) (max - min) / (a.size() - 1);
        for (int i = 0; i < a.size(); i++) {
            int index = (int) ((a.get(i) - min) / spaceGap);

            // empty bucket
            if (buckets[index].low == -1) {
                buckets[index].low = a.get(i);
                buckets[index].high = a.get(i);
            } else { // update low and high values
                buckets[index].low = buckets[index].low < a.get(i)
                        ? buckets[index].low : a.get(i);
                buckets[index].high = buckets[index].high > a.get(i)
                        ? buckets[index].high : a.get(i);
            }
        }

        // scan buckets to find maximum gap
        int maxGap = 0;
        int prevHigh = buckets[0].high;
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i].low != -1) {
                // store maximum gap
                int currentGap = buckets[i].low - prevHigh;
                maxGap = maxGap > currentGap ? maxGap : currentGap;
                prevHigh = buckets[i].high;
            }
        }

        return maxGap;
    }

}
