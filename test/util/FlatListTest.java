/**
 * FlatListTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 */

package util;

import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import util.FlatList;

public class FlatListTest {

    private static List<Integer> ints1;
    private static List<Integer> ints2;
    private static List<Integer> ints3;
    private static List<List<Integer>> l;
    private static List<Integer> mergedWithRemoveDuplicate;
    private static List<Integer> mergedWithOUTRemoveDuplicate;

    @BeforeAll
    public static void initAll() {
        ints1 = List.of(1, 3, 4);
        ints2 = List.of(2, 4);
        ints3 = List.of(3, 4);
        l = List.of(ints1, ints2, ints3);
        mergedWithRemoveDuplicate = FlatList.mergeLists(l);
        mergedWithOUTRemoveDuplicate = FlatList.mergeListsWithOutRemoveDuplicate(l);
    }

    @Test
    public void mergeList_return_an_instance_of_List_and_Not_List_of_List() {
        Assertions.assertTrue(FlatList.mergeLists(l) instanceof List);
    }

    @Test
    public void Should_Merge_All_Lists_in_the_given_List_in_One_List() {
        Assertions.assertTrue(mergedWithRemoveDuplicate.contains(1) && mergedWithRemoveDuplicate.contains(2)
                && mergedWithRemoveDuplicate.contains(3) && mergedWithRemoveDuplicate.contains(4));
    }

    @Test
    public void Should_not_contains_List_object_of_original_List_element() {
        Assertions.assertFalse(mergedWithRemoveDuplicate.contains(ints1));
        Assertions.assertFalse(mergedWithRemoveDuplicate.contains(ints2));
        Assertions.assertFalse(mergedWithRemoveDuplicate.contains(ints3));
    }

    @Test
    public void Frequency_Of_element_WithRemoveDuplicate_in_return_list_should_be_equal_to_One() {
        Assertions.assertTrue(
                mergedWithRemoveDuplicate.stream().filter(i -> Collections.frequency(mergedWithRemoveDuplicate, i) > 1)
                        .collect(Collectors.toList()).isEmpty());

    }

    @Test
    public void Frequency_Of_element_WithOUTRemoveDuplicate_in_return_list_should_be_SUP_to_One() {
        Assertions.assertFalse(mergedWithOUTRemoveDuplicate.stream()
                .filter(i -> Collections.frequency(mergedWithOUTRemoveDuplicate, i) > 1).collect(Collectors.toList())
                .isEmpty());

    }
}
