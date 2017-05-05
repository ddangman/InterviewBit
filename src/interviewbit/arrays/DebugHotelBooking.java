/*
 * This code was created by another InterviewBit user. Corrections are made by me.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dang
 */
public class DebugHotelBooking {

    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        // sort both input arrays
        Collections.sort(arrive);
        Collections.sort(depart);
        
        ArrayList<Interval> a = new ArrayList<Interval>();
        for (int i = 0; i < arrive.size(); i++) {
            Interval k = new Interval();
            k.start = arrive.get(i);
            k.end = depart.get(i);
            a.add(k);
        }
        
        Collections.sort(a);
        ArrayList<Integer> room = new ArrayList<Integer>();
        for (int i = 0; i < K; i++) {
            room.add(-1);
        }
        for (int i = 0; i < arrive.size(); i++) {
            int ArrivalDate = a.get(i).start;
            Collections.sort(room);
            int Index = BinarySearch(room, ArrivalDate);
            //System.out.println(Index);
            //System.out.println("AD" + ArrivalDate);
            if (Index >= K) {
                return false;
            }

            room.set(Index, a.get(i).end);
        }
        return true;
    }

    public int BinarySearch(ArrayList<Integer> x, int y) {
        int low = 0;
        int high = x.size() - 1;
        while (low <= high) {
            int mid = low + high / 2;
            if (x.get(mid) > y) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return x.size();
    }

    class Interval implements Comparable<Interval> {

        int start;
        int end;

        public int compareTo(Interval e) {
            if (start == e.start) {
                return 0;
            } else if (start < e.start) {
                return -1;
            } else {
                return 1;
            }
        }

    }

}
