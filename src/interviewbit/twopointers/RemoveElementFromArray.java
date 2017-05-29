/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class RemoveElementFromArray {

    public int removeElement(ArrayList<Integer> a, int b) {
        boolean needSwap = false;
        int j = 0;
        int i = 0;
        while (true) {
            while (j < a.size() && a.get(j) == b) {
                needSwap = true;
                j++;
            }
            if (j >= a.size()) {
                break;
            }
            if (needSwap) {
                a.set(i, a.get(j));
                i++;
                j++;
            } else {
                j++;
                i++;
            }
        }
        while (a.size() > i) {
            a.remove(a.size() - 1);
        }
        return a.size();
    }

    public int removeDuplicates(ArrayList<Integer> a) {
        boolean needSwap = false;
        int copy = a.get(0);
        int i = 0;
        int j = 0;
        while (true) {
            while (j < a.size() && a.get(j) == copy) {
                needSwap = true;
                j++;
            }
            if (j >= a.size()) {
                break;
            }
            copy = a.get(j);
            if (needSwap) {
                i++;
                a.set(i, a.get(j));
                j++;
            } else {
                i++;
                j++;
            }
        }

        while (a.size() > i + 1) {
            a.remove(a.size() - 1);
        }
        return a.size();
    }

    public int allowDoubles(ArrayList<Integer> a) {
        boolean allowOnce = true;
        boolean needSwap = false;
        int copy = a.get(0);
        int i = 0;
        int j = 1;
        while (true) {
            while (j < a.size() && a.get(j) == copy) {
                if (allowOnce) {
                    a.set(++i, a.get(j));
                    allowOnce = false;
                }
                needSwap = true;
                j++;
            }
            if (j >= a.size()) {
                break;
            }
            copy = a.get(j);
            allowOnce = true;
            if (needSwap) {
                a.set(++i, a.get(j++));
            } else {
                i++;
                j++;
            }
        }

        while (a.size() > i + 1) {
            a.remove(a.size() - 1);
        }
        return a.size();
    }

//    public int removeDuplicates(ArrayList<Integer> a) {
//        for (ListIterator<Integer> iterator = a.listIterator(); iterator.hasNext();) {
//            int i = iterator.next();
//            int j = iterator.nextIndex();
//            if (j < a.size() && a.get(j) == i) {
//                iterator.remove();
//            }
//        }
//        return a.size();
//    }
    
//    public int allowDoubles(ArrayList<Integer> a) {
//        boolean mustDelete = false;
//        for (ListIterator<Integer> iterator = a.listIterator(); iterator.hasNext();) {
//            int i = iterator.next();
//            int j = iterator.nextIndex();
//            if (j < a.size() && a.get(j) == i) {
//                if (mustDelete) {
//                    iterator.remove();
//                } else {
//                    mustDelete = true;
//                }
//            } else {
//                mustDelete = false;
//            }
//        }
//        return a.size();
//    }
}
