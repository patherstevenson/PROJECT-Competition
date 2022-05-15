/**
 * MockMatch Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package match;

import java.util.Map;
import java.util.HashMap;
import person.Competitor;

public class MockMatch implements Match {

	private int nbCallToGame = 0;
	private Map<Competitor, Integer> NbPlayedMatchMap = new HashMap<Competitor, Integer>();

	/**
	 * The winner of the game is automatically first competitor Increments the
	 * number of calls to game method
	 * 
	 * @return c1 : Competitor
	 */

	public Competitor game(Competitor c1, Competitor c2) {
		this.incrementeNbPlayedMatch(c1, c2);
		this.incrementNbCallToGame();
		return c1;
	}

	/**
	 * Increments the number of matches played by competitor when contest between
	 * competitors c1 and c2
	 * 
	 * @param c1 : Competitor
	 * @param c2 : Competitor
	 */

	private void incrementeNbPlayedMatch(Competitor c1, Competitor c2) {
		this.getNbPlayedMatchMap().put(c1,
				(this.getNbPlayedMatchMap().containsKey(c1) ? this.getNbPlayedMatchMap().get(c1) + 1 : 1));
		this.getNbPlayedMatchMap().put(c2,
				(this.getNbPlayedMatchMap().containsKey(c2) ? this.getNbPlayedMatchMap().get(c2) + 1 : 1));
	}

	/**
	 * Increments the number of call to game method
	 */

	private void incrementNbCallToGame() {
		this.nbCallToGame += 1;
	}

	/**
	 * Gets the number of played match of each competitor
	 * 
	 * @return nbPlayedMatch : Map<Competitor, Integer>
	 */
	public Map<Competitor, Integer> getNbPlayedMatchMap() {
		return this.NbPlayedMatchMap;
	}

	/**
	 * Gets the number of call to game method
	 * 
	 * @return nbCallToGame : int
	 */
	public int getNbCallToGame() {
		return this.nbCallToGame;
	}
}
