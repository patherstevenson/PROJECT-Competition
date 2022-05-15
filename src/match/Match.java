/**
 * Match Interface
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package match;

import person.Competitor;

public interface Match {

	/**
	 * Makes a contest between two competitors 
	 * @param c1 : Competitor
	 * @param c2 : Competitor
	 */

	public Competitor game(Competitor c1, Competitor c2);

}
