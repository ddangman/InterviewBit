/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

import java.util.List;

/**
 *
 * @author Dang
 */
public class MedianOfArray {

    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
        if (a.isEmpty()) {
            return singleMedian(b);
        } else if (b.isEmpty()) {
            return singleMedian(a);
        }
        int allSize = a.size() + b.size();
        if (allSize == 1) {
            return a.isEmpty() ? b.get(0) : a.get(0);
        }
        boolean isEven = allSize % 2 == 0;

        int min = a.get(0) > b.get(0) ? b.get(0) : a.get(0);
        int max = a.get(a.size() - 1) > b.get(b.size() - 1)
                ? a.get(a.size() - 1) : b.get(b.size() - 1);
        int fixedMax = max;

        // median is lowest number greater than or equal to half of all elements
        int half = (allSize + 1) / 2;

        int lowestMedian = max;

        while (min < max) {
            int median = min + ((max - min) / 2);
            int count = lessOrEqualCount(a, median);
            count += lessOrEqualCount(b, median);
            if (count >= half) {
                // change ceiling to find possible lower median
                max = median;
                if (findMedian(a, median) || findMedian(b, median)) {
                    // median is in array
                    if (median < lowestMedian) {
                        // save lowest median
                        lowestMedian = median;
                    }
                }
            } else {
                // median is not greater than half of all elements
                // increase calculated median
                min = median + 1;
            }
        }

        double trueMedian = lowestMedian;
        if (isEven) {
            if (hasDuplicate(lowestMedian, a, b)) {
                return lowestMedian;
            }
            int next = lowestMedian + 1;
            while (next <= fixedMax) {
                if (findMedian(a, next) || findMedian(b, next)) {
                    trueMedian = lowestMedian + ((next - lowestMedian) / 2.0);
                    break;
                } else {
                    next++;
                }
            }

        }
        return trueMedian;
    }

    public double singleMedian(final List<Integer> list) {
        int half = list.size() / 2;
        if (list.size() % 2 == 1) {
            return list.get(half);
        } else {
            return list.get(half - 1) + (list.get(half) - list.get(half - 1)) / 2.0;
        }
    }

    public int lessOrEqualCount(List<Integer> arr, int key) {
        int start = 0;
        int end = arr.size() - 1;
        int mid = 0;

        while (start < end) { // final comparison done outside loop
            mid = start + ((end - start) / 2);
            if (arr.get(mid) <= key) {
                // search after
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        // final comparison
        if (arr.get(start) <= key) {
            return start + 1;
        } else {
            return start;
        }
    }

    public boolean findMedian(List<Integer> arr, int key) {
        int start = 0;
        int end = arr.size() - 1;
        int mid = 0;
        while (start <= end) { // final comparison done in loop
            mid = start + ((end - start) / 2);
            if (arr.get(mid) == key) {
                return true;
            }
            if (arr.get(mid) > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    public boolean hasDuplicate(int key, final List<Integer>... lists) {
        boolean foundSingle = false;
        for (List<Integer> l : lists) {
            int start = 0;
            int end = l.size() - 1;
            int mid = 0;
            while (start <= end) { // final comparison done in loop
                mid = start + ((end - start) / 2);
                if (l.get(mid) == key) {
                    if (foundSingle) {
                        return true;
                    } else {
                        foundSingle = true;
                        if (mid > 0) {
                            if (l.get(mid-1) == key) {
                                return true;
                            }                            
                        }
                        if (mid < l.size()-1) {
                            if (l.get(mid+1) == key) {
                                return true;
                            }                            
                        }                        
                        break;
                    }                   
                }
                if (l.get(mid) > key) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return false;
    }
}
