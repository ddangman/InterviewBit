/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.heapmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Duy Dang
 */
public class GetModeArrayUpdates {

    HashMap<Integer, Integer> map;
    TreeMap<Integer, TreeMap<Integer, Integer>> mapmap;

    public ArrayList<Integer> getMode(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        // key: element, value: frequency
        map = new HashMap<Integer, Integer>();
        // key: frequency, value: map(element, 1)
        mapmap = new TreeMap<>();
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int e : A) {
            if (map.containsKey(e)) {
                map.put(e, map.get(e) + 1); // increase frequency
            } else {
                map.put(e, 1);
            }
        }
        for (Map.Entry ent : map.entrySet()) {
            int freq = (int) ent.getValue();
            TreeMap<Integer, Integer> inner = new TreeMap<>();
            if (mapmap.containsKey(freq) == true) {
                inner = mapmap.get(freq);
            }
            inner.put((int) ent.getKey(), 1);
            mapmap.put(freq, inner);
        }
        
        for (ArrayList<Integer> update : B) {
            int index = update.get(0); // index of insertion
            int insert = update.get(1); // inserted number
            int remove = A.get(index - 1); // number to be removed

            if (map.get(remove) != null) { // decrease frequency of 'remove'
                int rfreq = map.get(remove); // Remove FREquency
                TreeMap<Integer, Integer> rm = mapmap.get(rfreq); // Remove Map
                if (rm.size() == 1) {
                    mapmap.remove(rfreq);
                } else {
                    rm.remove(remove); // 'remove' no longer belongs to this frequencyMap
                    mapmap.put(rfreq, rm); // reinsert map for proper sorting
                }
                if (rfreq == 1) {
                    map.remove(remove);
                } else {
                    int rfso = rfreq - 1; // Remove Frequency Subtract One
                    map.put(remove, rfso);
                    TreeMap<Integer, Integer> rfsom = new TreeMap<>(); // Remove Frequency Subtract One Map
                    if (mapmap.containsKey(rfso)) {
                        rfsom = mapmap.get(rfso);
                    }
                    rfsom.put(remove, 1);
                    mapmap.put(rfso, rfsom);
                }
            }
            A.set(index - 1, insert); // update array
            if (map.containsKey(insert)) {
                int ifre = map.get(insert); // Insert FREquency
                TreeMap<Integer, Integer> ifm = mapmap.get(ifre); // Insert Frequency Map
                if (ifm.size() == 1) {
                    mapmap.remove(ifre);
                } else {
                    ifm.remove(insert);
                    mapmap.put(ifre, ifm);
                }
                map.put(insert, ifre + 1);
                TreeMap<Integer, Integer> ifpom = new TreeMap<>(); // Insert Frequency Plus One Map
                if (mapmap.containsKey(ifre + 1)) {
                    ifpom = mapmap.get(ifre + 1);
                }
                ifpom.put(insert, 1);
                mapmap.put(ifre + 1, ifpom);
            } else {
                map.put(insert, 1);
                TreeMap<Integer, Integer> ofm = new TreeMap<>(); // One Frequency Map
                if (mapmap.containsKey(1)) {
                    ofm = mapmap.get(1);
                }
                ofm.put(insert, 1);
                mapmap.put(1, ofm);
            }
            int hk = (int) mapmap.lastKey(); // highest key (frequency)
            TreeMap<Integer, Integer> hkm = mapmap.get(hk); // highest key map
            result.add((int) (hkm.firstKey()));
        }
        return result;
    }

}
