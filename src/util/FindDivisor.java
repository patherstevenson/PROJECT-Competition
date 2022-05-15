/**
 * FindDivisor abstract Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package util;

import java.lang.Math;

public abstract class FindDivisor {

    /**
     * Gets the nearest divisor for which with n modulo is 0 if res == 1 we return
     * randomDivisor for n. In order to avoid a maximum case where we return 1
     * 
     * @param n the number : int
     * @return the nearest int where n%i == 0 or random divisor if res == 1
     */
    public static int findNearestIntDivisor(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            int res = n - 1;
            while (1 < res && (n % res != 0)) {
                res--;
            }
            return res != 1 ? res : randomDivisor(n);
        }
    }

    /**
     * Gets a random number between 1 and n if 1 <= n <= 5 Otherwise return a number
     * in range [2,n]
     * 
     * @param n the number : int
     * @return number between 1 and n for n in range [1,5]. Otherwise a number in
     *         range [2,n]
     */
    public static int randomDivisor(int n) {
        if (n == 1 | n == 2) {
            return (int) ((Math.random() * (2 - 1)) + 1);
        } else if (n == 3) {
            return (int) ((Math.random() * (3 - 1)) + 1);
        } else if (n <= 5 && n > 0) {
            return (int) ((Math.random() * (5 - 1)) + 1);
        } else {
            return (int) ((Math.random() * (n - 2)) + 2);
        }

    }
}
