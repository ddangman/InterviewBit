/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class MergeIntervals {

    public ArrayList<Interval> insertBinary(ArrayList<Interval> intervals, Interval newInterval) {

        // Size of interval array is 0
        if (intervals.isEmpty()) {
            return new ArrayList<Interval>(Arrays.asList(newInterval));
        }
        int s = newInterval.start;
        int e = newInterval.end;

        // flipped values
        if (s > e) {
            newInterval.end = s;
            newInterval.start = e;
            s = e;
            e = newInterval.end;
        }
        int fgs = firstGreater(intervals, s);
        int fge = firstGreater(intervals, e);

        // newInterval being an interval preceding all intervals in the array.
        if (fge == 1) {
            ArrayList<Interval> ret = new ArrayList<Interval>(Arrays.asList(newInterval));
            intervals.stream().forEachOrdered(ret::add);
            return ret;
        }

        // newInterval being an interval succeeding all intervals in the array.
        if (intervals.size() * 2 < fgs && fge != 0) {
            intervals.add(newInterval);
            return intervals;
        }

        // newInterval covering all given intervals.
        if (fgs <= 1 && intervals.size() * 2 <= fge) {
            return new ArrayList<Interval>(Arrays.asList(newInterval));
        }

        //newInterval not overlapping with any interval 
        // and falling in between 2 intervals in the array.
        if (fgs == fge && fgs % 2 == 1) {
            int indexNew = (fgs / 2);
            ArrayList<Interval> ret = new ArrayList<Interval>();
            for (int i = 0; i < indexNew; i++) {
                ret.add(intervals.get(i));
            }
            ret.add(newInterval);
            for (int i = indexNew; i < intervals.size(); i++) {
                ret.add(intervals.get(i));
            }
            return ret;
        }

        // only need to change one value
        if (fgs + 1 == fge) {
            if (fgs % 2 == 1) {
                // replace index.start with s
                int index = fgs / 2;
                intervals.get(index).start = s;
            } else {
                // replace index.end with s
                int index = (fgs / 2) - 1;
                intervals.get(index).end = e;
            }
            return intervals;
        }
        if (fgs % 2 == 0) {
            int inS = (fgs / 2) - 1;
            if (fge % 2 == 0) {
                // slice first and last
                int inE = (fge / 2) - 1;
                // update values
                boolean add = false;
                int ee = intervals.get(inE).end;
                if (fgs == fge) {
                    add = true;
                    intervals.get(inS).end = s;
                } else {
                    intervals.get(inS).end = intervals.get(inE).end;
                }

                ArrayList<Interval> ret = new ArrayList<Interval>();
                for (int i = 0; i <= inS; i++) {
                    ret.add(intervals.get(i));
                }
                if (add) {
                    ret.add(new Interval(e, ee));
                }
                for (int i = inE + 1; i < intervals.size(); i++) {
                    ret.add(intervals.get(i));
                }
                return ret;
            } else {
                // slice first only
                int inE = (fge / 2);

                // update values
                intervals.get(inS).end = e;

                ArrayList<Interval> ret = new ArrayList<Interval>();
                for (int i = 0; i <= inS; i++) {
                    ret.add(intervals.get(i));
                }
                for (int i = inE; i < intervals.size(); i++) {
                    ret.add(intervals.get(i));
                }
                return ret;
            }

        } else {
            int inS = (fgs / 2);
            if (fge % 2 == 0) {
                // slice last only
                int inE = (fge / 2) - 1;

                // update values
                intervals.get(inS).end = intervals.get(inE).end;
                if (s < intervals.get(inS).start) {
                    intervals.get(inS).start = s;
                }

                ArrayList<Interval> ret = new ArrayList<Interval>();
                for (int i = 0; i <= inS; i++) {
                    ret.add(intervals.get(i));
                }
                for (int i = inE + 1; i < intervals.size(); i++) {
                    ret.add(intervals.get(i));
                }
                return ret;

            } else {
                // no slicing
                int inE = (fge / 2);

                ArrayList<Interval> ret = new ArrayList<Interval>();
                for (int i = 0; i < inS; i++) {
                    ret.add(intervals.get(i));
                }
                ret.add(newInterval);
                for (int i = inE; i < intervals.size(); i++) {
                    ret.add(intervals.get(i));
                }
                return ret;
            }
        }

    }

    // binary search for first greater index
    // if match at end, go to next
    private int firstGreater(ArrayList<Interval> intervals, int n) {
        int start = 1;
        int end = intervals.size() * 2;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (mid == 1) {
                if (n <= intervals.get(0).start) {
                    return 1;
                } else if (n < intervals.get(0).end) {
                    return 2;
                }
            }

            // find match
            if (mid % 2 == 0) {
                // end value
                int index = (mid / 2) - 1;

                if (intervals.get(index).start < n
                        && n < intervals.get(index).end) {
                    // match found
                    return (index + 1) * 2;
                }
                if (n == intervals.get(index).end) {
                    return ((index + 1) * 2) + 1;
                }
                if (intervals.get(index).end < n) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                // start value              
                int index = mid / 2;
                if (intervals.get(index).end < n
                        && index + 1 == intervals.size()) {
                    // greater than end of array
                    return mid + 2;
                } else if (n < intervals.get(index).start && index == 0) {
                    // less than start of array
                    return 1;
                } else if (intervals.get(index - 1).end < n
                        && n < intervals.get(index).start) {
                    // match found
                    return mid;
                }
                if (n == intervals.get(index).start) {
                    return mid + 1;
                }
                if (n < intervals.get(index).start) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return 0;
    }

    // website's solution
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

        int start, end;
        int startIndex, endIndex;
        int i;

        start = newInterval.start;
        end = newInterval.end;
        i = 0;

        startIndex = endIndex = -1;

        for (Interval in : intervals) {

            if (start >= in.start && start <= in.end) {
                startIndex = i;
            }

            if (end >= in.start && end <= in.end) {
                endIndex = i;
            }

            i++;
        }

        if (startIndex == -1 && endIndex == -1) {

            startIndex = 0;

            for (i = 0; i < intervals.size(); i++) {
                if (start > intervals.get(i).end) {
                    startIndex = i + 1;
                }
            }

            endIndex = intervals.size() - 1;

            for (i = intervals.size() - 1; i >= 0; i--) {
                if (end < intervals.get(i).start) {
                    endIndex = i - 1;
                }
            }

            for (i = startIndex; i <= endIndex; i++) {
                intervals.remove(startIndex);
            }

            intervals.add(startIndex, newInterval);

            return intervals;
        }

        if (startIndex == -1) {
            for (i = intervals.size() - 1; i >= 0; i--) {
                if (start <= intervals.get(i).start) {
                    startIndex = i;
                }
            }
        }

        if (endIndex == -1) {
            for (i = 0; i < intervals.size(); i++) {
                if (end >= intervals.get(i).end) {
                    endIndex = i;
                }
            }
        }

        start = Math.min(intervals.get(startIndex).start, start);
        end = Math.max(intervals.get(endIndex).end, end);

        intervals.get(startIndex).start = start;
        intervals.get(startIndex).end = end;

        for (i = startIndex + 1; i <= endIndex; i++) {
            intervals.remove(startIndex + 1);
        }

        return intervals;

    }

    private static class Interval {

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
    }

}
