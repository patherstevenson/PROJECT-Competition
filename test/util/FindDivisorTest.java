/**
 * FindDivisorTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 */

package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import util.FindDivisor;

public class FindDivisorTest {

    @Test
    public void findNearestIntDivisor_Should_Give_The_Nearest_Int_Divisor_If_Divisor_Different_Of_One() {
        Assertions.assertEquals(6, FindDivisor.findNearestIntDivisor(12));
        Assertions.assertEquals(5, FindDivisor.findNearestIntDivisor(15));
    }

    @Test
    public void findNearestIntDivisor_Should_return_0_if_0_given() {
        Assertions.assertEquals(0, FindDivisor.findNearestIntDivisor(0));
    }

    @Test
    public void findNearestIntDivisor_Should_return_1_if_1_given() {
        Assertions.assertEquals(1, FindDivisor.findNearestIntDivisor(1));
    }

    @Test
    public void randomDivisor_Should_return_Number_in_a_Range_if_given_number_is_2() {
        int number = FindDivisor.randomDivisor(3);
        Assertions.assertTrue((number == 1) | (number == 2));
    }

    @Test
    public void randomDivisor_Should_return_Number_in_a_Range_if_given_number_is_3() {
        int number = FindDivisor.randomDivisor(3);
        Assertions.assertTrue((number == 1) | (number == 2) | (number == 3));
    }

    @Test
    public void randomDivisor_Should_return_Number_in_a_Range_if_given_number_is_5() {
        int number = FindDivisor.randomDivisor(5);
        Assertions.assertTrue((number == 1) | (number == 2) | (number == 3) | (number == 4) | (number == 5));
    }

    @Test
    public void randomDivisor_Should_return_Number_in_a_Range_if_given_number_is_N() {
        int number = FindDivisor.randomDivisor(100);
        Assertions.assertTrue(2 <= number && number <= 100);
    }
}
