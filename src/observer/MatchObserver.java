/**
 * MatchObserver Interface
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package observer;

import person.Competitor;

/**
 * Assists to a match and update it's activity withing the score of the match
 */

public interface MatchObserver {

	/**
	 * Updates it's activity
	 * 
	 * @param winner : Competitor
	 * @param loser  : Competitor
	 */

	public void update(Competitor winner, Competitor loser);

}
