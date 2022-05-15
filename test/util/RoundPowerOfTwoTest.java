/**
 * RoundPowerOfTwoTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import util.RoundPowerOfTwo;

public class RoundPowerOfTwoTest {

    @Test
    public void Should_return_the_same_number_If_Number_is_already_power_Of_Two() {
        Assertions.assertEquals(RoundPowerOfTwo.findPreviousPowerOf2(0), 0);
        Assertions.assertEquals(RoundPowerOfTwo.findPreviousPowerOf2(2), 2);
        Assertions.assertEquals(RoundPowerOfTwo.findPreviousPowerOf2(4), 4);
        Assertions.assertEquals(RoundPowerOfTwo.findPreviousPowerOf2(8), 8);
        Assertions.assertEquals(RoundPowerOfTwo.findPreviousPowerOf2(16), 16);
    }

    @Test
    public void Should_round_to_the_nearest_previous_power_of_Two() {
        Assertions.assertEquals(RoundPowerOfTwo.findPreviousPowerOf2(53), 32);
        Assertions.assertEquals(RoundPowerOfTwo.findPreviousPowerOf2(20), 16);
        Assertions.assertEquals(RoundPowerOfTwo.findPreviousPowerOf2(15), 8);
    }
}
