/**
 * FlatList Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FlatList {

    /**
     * Merge all List in the given List (List of List). All List in the given list
     * contains Generic Type T. This method use flatMap method with stream and
     * collectors. Can't contains duplicate elements
     * 
     * @param <T>   : Generic type T
     * @param lists : the list that contains all List<T>
     * @return a single list with all T elements in List of lists
     */
    public static <T> List<T> mergeLists(List<List<T>> lists) {
        return removeDuplicate(lists.stream().flatMap(List::stream).collect(Collectors.toList()));
    }

    /**
     * Merge all List in the given List (List of List). All List in the given list
     * contains Generic Type T. This method use flatMap method with stream and
     * collectors. Can contains duplicate elements
     * 
     * @param <T>   : Generic type T
     * @param lists : the list that contains all List<T>
     * @return a single list with all T elements in List of lists
     */
    public static <T> List<T> mergeListsWithOutRemoveDuplicate(List<List<T>> lists) {
        return lists.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * Return the given list with at most one frequency of each element then remove
     * duplicate element
     * 
     * @param <T>  Generic type
     * @param list : List<T>
     * @return the list is each element wih frequency == 1
     */
    private static <T> List<T> removeDuplicate(List<T> list) {
        List<T> l = new ArrayList<T>();
        for (T e : list) {
            if (!l.contains(e)) {
                l.add(e);
            }
        }
        return l;
    }
}
