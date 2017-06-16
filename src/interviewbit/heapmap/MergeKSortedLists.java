/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.heapmap;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author Dang
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ArrayList<ListNode> a) {
        ListNode chain = new ListNode(0);
        ListNode primer = chain;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (int i = 0; i < a.size(); i++) {
            ListNode head = a.get(i);
            while (head != null) {
                int val = head.val;
                map.compute(val, (k, v) -> {
                    if (v == null) {
                        return 1;
                    } else {
                        return v + 1;
                    }                   
                });
                head = head.next;
            }
        }
        
        
        while (!map.isEmpty()) {
            Entry<Integer, Integer> e = map.firstEntry();
            int key = e.getKey();
            int val = e.getValue();
            if (val == 1) {
                map.remove(key);
            } else {
                map.put(key, val - 1);
            } 
            
            chain.next = new ListNode(key);
            chain = chain.next;
        }
        return primer.next;
    }

    class ListNode {

        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
