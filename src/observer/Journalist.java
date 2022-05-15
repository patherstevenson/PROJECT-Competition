/**
 * Journalist Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package observer;

import person.Competitor;

/**
 * Implements MatchObserver
 * Displays the
 */

public class Journalist implements MatchObserver {

	private String id;

	public Journalist(String id) {
		this.id = id;
	}

	/**
	 * Gets the id of the journalist
	 * 
	 * @return id : String
	 */

	public String getId() {
		return id;
	}

	@Override
	public void update(Competitor winner, Competitor loser) {
		System.out.println(winner.getName() + " vs " + loser.getName() + " --> " + winner.getName() + " wins!");
	}

}
