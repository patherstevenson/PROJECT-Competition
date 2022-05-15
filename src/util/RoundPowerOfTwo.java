/**
 * RoundPowerOfTwo abstract Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package util;

public abstract class RoundPowerOfTwo {

    /**
     * Gets the power of two less than or equal to n
     * 
     * @param n the number to round : int
     * @return the rounded number : int
     */
    public static int findPreviousPowerOf2(int n) {
        while ((n & n - 1) != 0) {
            n = n & n - 1;
        }
        return n;
    }
}
