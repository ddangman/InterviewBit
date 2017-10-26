/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class MergeOverlappingIntervals {

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        Collections.sort(intervals, (o1, o2)
                -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);

        ArrayList<Interval> keep = new ArrayList<>();

        int keepStart = intervals.get(0).start;
        int maxEnd = intervals.get(0).end;
        for (int i = 0; i < intervals.size(); i++) {
            if (keepStart == intervals.get(i).start) {
                // intervals should be sorted so that equal .start
                // would have increasing .end
                maxEnd = intervals.get(i).end;
            }
            if (intervals.get(i).start < maxEnd) { // overlap

                if (intervals.get(i).end > maxEnd) {
                    // new maxEnd
                    maxEnd = intervals.get(i).end;
                }
            } else { // no overlap
                // keep Interval
                keep.add(new Interval(keepStart, maxEnd));
                while (keepStart == intervals.get(i).start) {
                    i++;
                }
                keepStart = intervals.get(i).start;
                maxEnd = intervals.get(i).end;
            }
        }
        if (keep.isEmpty()) {
            keep.add(new Interval(keepStart, maxEnd));
        } else if (keep.get(keep.size() - 1).end != keepStart){
            keep.add(new Interval(keepStart, maxEnd));
        }

        return keep;
    }

    private void printSort(ArrayList<Interval> intervals) {
        Collections.sort(intervals, (o1, o2)
                -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);
        for (Interval i : intervals) {
            System.out.print("[" + i.start + "," + i.end + "] ");
        }
    }

    // if intervals.start is sorted and n is index of intervals
    // returns index of first Interval where .end is less than n.start or equal
    private int binarySearchStart(ArrayList<Interval> intervals, int n) {
        int start = 0;
        int end = n;
        int mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (intervals.get(n).start == intervals.get(mid).end) {
                return mid;
            }
            if (intervals.get(n).start < intervals.get(mid).end) {
                // continue searching for lower value
                end = mid - 1;
            } else {
                // check remaining values
                start = mid + 1;
            }
        }
        return mid;
    }

    // if intervals.start is sorted and n is index of intervals
    // returns index of first Interval where .start is less than n.end or equal
    private int binarySearchEnd(ArrayList<Interval> intervals, int n) {
        int start = 0;
        int end = n;
        int mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (intervals.get(n).end == intervals.get(mid).start) {
                return mid;
            }
            if (intervals.get(n).end > intervals.get(mid).start) {
                // continue searching for greater value
                start = mid + 1;
            } else {
                // check remaining values
                end = mid - 1;
            }
        }
        return mid;
    }

    private static class Interval implements Comparable<Interval> {

        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public int compareTo(Interval o) {
            // compareTo should return < 0 if this is supposed to be less than other,
            // > 0 if this is supposed to be greater than other
            //  and 0 if they are supposed to be equal            
            return this.start == o.start ? 0 : this.start - o.start;
        }
    }
}
