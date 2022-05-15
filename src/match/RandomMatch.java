/**
 * RandomMatch Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package match;

import java.util.Random;

import person.Competitor;

public class RandomMatch implements Match {

    /**
     * give the randomly chosen winner between the two given competitors c1 & c2
     * 
     * @param c1 first competitor : Competitor
     * @param c2 second competitor : Competitor
     * @return winner : Competitor
     */
    public Competitor game(Competitor c1, Competitor c2) {
        final Competitor[] competitors = new Competitor[] { c1, c2 };
        return competitors[new Random().nextInt(competitors.length)];
    }
}
