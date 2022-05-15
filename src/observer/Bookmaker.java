/**
 * Bookmaker Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package observer;

import java.util.HashMap;
import java.util.Map;

import person.Competitor;

/**
 * Implements MatchObserver
 * Updates the odds of the players
 */

public class Bookmaker implements MatchObserver {

	private String id;
	private Map<Competitor, Integer> odds;
	private static int INITODD = 5;

	public Bookmaker(String id) {
		this.id = id;
		odds = new HashMap<Competitor, Integer>();
	}

	/**
	 * Gets the id of the bookmaker
	 * 
	 * @return id : String
	 */

	public String getId() {
		return id;
	}

	/**
	 * Adds both competitors if not already in the odds
	 * Lowers the winner's odd and uppers the lower's odd
	 * 
	 * @param winner : Competitor
	 * @param loser  : Competitor
	 */

	@Override
	public void update(Competitor winner, Competitor loser) {
		addCompetitorToOdds(winner);
		addCompetitorToOdds(loser);

		int winOddAncient = competitorOdd(winner);
		int loseOddAncient = competitorOdd(loser);

		lowerWinnerOdd(winner);
		upperLoserOdd(loser);

		int winOddNew = competitorOdd(winner);
		int losOddNew = competitorOdd(loser);

		print(winner, loser, winOddAncient, loseOddAncient, winOddNew, losOddNew);
	}

	/**
	 * print the old odd and new odd after a played match between winner and loser
	 * 
	 * @param winner         : Competitor
	 * @param loser          : Competitor
	 * @param winOddAncient  : Int
	 * @param loseOddAncient : Int
	 * @param winOddNew      : Int
	 * @param losOddNew      : Int
	 */
	private void print(Competitor winner, Competitor loser, int winOddAncient, int loseOddAncient, int winOddNew,
			int losOddNew) {
		System.out.println(
				"\nVictoire de " + winner.getName() + " (cote " + winOddAncient + ") face à " + loser.getName() +
						" (cote " + loseOddAncient + "). La cote de " + winner.getName() + " passe à " + winOddNew
						+ " , celle " + loser.getName() + " à "
						+ losOddNew + "\n");
	}

	/**
	 * Adds a competitor to the odds
	 * 
	 * @param competitor : Competitor
	 */

	private void addCompetitorToOdds(Competitor competitor) {
		if (!odds.containsKey(competitor))
			odds.put(competitor, INITODD);
	}

	/**
	 * Lowers the odd of the winner
	 * Odd cannot be under 1
	 * 
	 * @param winner : Competitor
	 */

	private void lowerWinnerOdd(Competitor winner) {
		int currentOdd = odds.get(winner);
		if (currentOdd > 1)
			odds.replace(winner, currentOdd - 1);
	}

	/**
	 * Uppers the odd of the loser
	 * 
	 * @param loser : Competitor
	 */

	private void upperLoserOdd(Competitor loser) {
		int currentOdd = odds.get(loser);
		odds.replace(loser, currentOdd + 1);
	}

	/**
	 * Gets the odd of the competitor given
	 * 
	 * @param c : Competition
	 * @returns odd : int
	 */

	public int competitorOdd(Competitor c) {
		return odds.get(c);
	}

}
