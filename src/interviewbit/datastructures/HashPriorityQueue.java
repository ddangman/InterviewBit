/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.datastructures;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author Duy Dang
 */
public class HashPriorityQueue<E> extends PriorityQueue<E> {

    private HashSet<E> set = new HashSet<>();

    /**
     * Inserts unique element into this priority queue.
     *
     * @return {@code true} (as specified by {@link Queue#offer})
     * @throws ClassCastException if the specified element cannot be
     *         compared with elements currently in this priority queue
     *         according to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean offer(E e) {
        boolean isAdded = false;
        if (!set.contains(e)) {
            set.add(e);
            isAdded = super.offer(e);
        }
        return isAdded;
    }

    
    /**
     * Inserts unique element into this priority queue.
     *
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws ClassCastException if the specified element cannot be
     *         compared with elements currently in this priority queue
     *         according to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean add(E e) {
        // calls offer so don't add to set yet
        if (!set.contains(e)) {
            return super.add(e);
        }
        return false;
    }

}
