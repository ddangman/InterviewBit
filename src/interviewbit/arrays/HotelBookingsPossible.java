/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Dang
 */
public class HotelBookingsPossible {
    
    public class Pair{
        int value;
        boolean out; // is checkout time
        
        Pair(int v, boolean o) {
            this.value = v;
            this.out = o;
        }
    }
    
    public boolean overlapSolution(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        ArrayList<Pair> pair = new ArrayList<>();
        
        for (int i = 0; i < arrive.size(); i++) {
            pair.add(new Pair(arrive.get(i), false));
            pair.add(new Pair(depart.get(i), true));
        }
        
        // sort by value, if equal, give priority to checkout
        Collections.sort(pair, (p1, p2) -> 
                p1.value == p2.value ? p1.out ? -1 : 1 : p1.value - p2.value);
        
        int guestCount = 0;
        for (Pair p : pair) {
            if (p.out) {
                guestCount--;
            } else {
                guestCount++;
            } 
            if (guestCount > K) {
                return false;
            }            
        }
        return true;
    }

    // heapsort solution
    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        TreeMap<Integer, Integer> ts = new TreeMap<>(); // stores and polls checkout times
        sort(arrive, depart);
        int in = 0; // current number of guests
        int out = Integer.MAX_VALUE; // next checkout time
        for (int i = 0; i < arrive.size(); i++) {
            in++; // check in

            // new checkout time is earlier than current
            // no need to put it into ts just to take it out
            if (depart.get(i) < out) {
                // add current checkout to ts for future use
                ts.compute(out, (key, value) -> {
                    if (value == null) {
                        return 1;
                    } else {
                        return value + 1;
                    }
                });
                out = depart.get(i);
            } else {
                ts.compute(depart.get(i), (key, value) -> {
                    if (value == null) {
                        return 1;
                    } else {
                        return value + 1;
                    }
                });
            }

            if (out <= arrive.get(i)) { // check out
                in--;
                // poll next checkout time
                out = ts.firstKey();
                if (ts.get(out) == 1) {
                    ts.remove(out);
                } else {
                    ts.compute(out, (key, value) -> {
                        return value - 1;
                    });
                }                
            }
            if (in > K) { // guests exceed room
                return false;
            }
        }

        return true;
    }

    // heapsort that keeps arrive:depart index in sync
    public void sort(ArrayList<Integer> arrive, ArrayList<Integer> depart) {
        int n = arrive.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arrive, depart, n, i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arrive.get(0);
            arrive.set(0, arrive.get(i));
            arrive.set(i, temp);

            // maintain arrive:depart relationship
            int tempD = depart.get(0);
            depart.set(0, depart.get(i));
            depart.set(i, tempD);

            // call max heapify on the reduced heap
            heapify(arrive, depart, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(ArrayList<Integer> arrive, ArrayList<Integer> depart, int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2 * i + 1;  // left = 2*i + 1
        int r = 2 * i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arrive.get(l) > arrive.get(largest)) {
            largest = l;
        }

        // If right child is larger than largest so far
        if (r < n && arrive.get(r) > arrive.get(largest)) {
            largest = r;
        }

        // if tie, use earlier checkout
        if (l < n && arrive.get(l) == arrive.get(largest)
                && depart.get(l) > depart.get(largest)) {
            largest = l;
        }
        if (r < n && arrive.get(r) == arrive.get(largest)
                && depart.get(r) > depart.get(largest)) {
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arrive.get(i);
            arrive.set(i, arrive.get(largest));
            arrive.set(largest, swap);

            // maintain arrive:depart relationship
            int swapD = depart.get(i);
            depart.set(i, depart.get(largest));
            depart.set(largest, swapD);

            // Recursively heapify the affected sub-tree
            heapify(arrive, depart, n, largest);
        }
    }

    // used to print heapsort results
    public void printSort(ArrayList<Integer> arrive, ArrayList<Integer> depart) {
        sort(arrive, depart);
        for (int i = 0; i < arrive.size(); i++) {
            System.out.println(i + ": " + arrive.get(i) + " " + depart.get(i));
        }

    }

    public boolean hotelDebug(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        TreeSet<Integer> ts = new TreeSet<>(); // stores and polls checkout times
        sort(arrive, depart);
        int in = 0; // current number of guests
        int out = Integer.MAX_VALUE; // next checkout time
        for (int i = 0; i < arrive.size(); i++) {
            System.out.print(i + ": ");
            in++; // check in
            System.out.print(in + " room filled");

            // new checkout time is earlier than current
            // no need to put it into ts just to take it out
            if (depart.get(i) < out) {
                ts.add(out); // add current checkout to ts for future use
                out = depart.get(i);
            } else {
                ts.add(depart.get(i));
            }

            if (out <= arrive.get(i)) { // check out
                System.out.print(" checkout at:" + out);
                in--;
                out = ts.pollFirst(); // poll next checkout time
                System.out.print(" rooms filled:" + in);
                System.out.print(" next checkout:" + out);
            }
            if (in > K) { // guests exceed room
                return false;
            }
            System.out.println("");
        }
        return true;
    }
}
