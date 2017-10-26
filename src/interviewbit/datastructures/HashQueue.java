/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.datastructures;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Only allows unique elements into queue once. Reentry is not allowed.
 *
 * @author Duy Dang
 */
public class HashQueue<E> extends LinkedList<E> {

    private HashSet<E> set = new HashSet<>();

    @Override
    public boolean offerLast(E e) {
        if (set.contains(e)) {
            return false;
        }
        set.add(e);
        return super.offerLast(e);
    }

    @Override
    public boolean offerFirst(E e) {
        if (set.contains(e)) {
            return false;
        }
        set.add(e);
        return super.offerFirst(e);
    }

    @Override
    public boolean add(E e) {
        if (set.contains(e)) {
            return false;
        }
        set.add(e);
        return super.add(e);
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

}
