/**
 * CompetitionBasis abstract Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package competition;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import match.Match;
import observer.CompetitionPublisher;
import person.Competitor;
import util.MapUtil;

public abstract class CompetitionBasis implements Competition {

	private final List<Competitor> competitors;
	private final Match match;
	private Map<Competitor, Integer> score;
	private CompetitionPublisher publisher;

	/**
	 * an abstract CompetitionBasis with a given List of Competitors and a Match
	 *
	 * @param competitors a list which contains the Competitors of the competition
	 */

	public CompetitionBasis(List<Competitor> competitors, Match match) {
		this.competitors = competitors;
		this.match = match;
		this.initScore();
	}
	
	/**
	 * Constructor with publisher
	 */
	
	public CompetitionBasis(List<Competitor> competitors, Match match, CompetitionPublisher publisher) {
		this.competitors = competitors;
		this.match = match;
		this.publisher = publisher;
		this.initScore();
	}

	/**
	 * generate a HashMap<Competitor,Integer> where we put the score of each
	 * competitors of this competition
	 */
	protected void initScore() {
		this.score = new HashMap<Competitor, Integer>();
		for (Competitor c : this.getCompetitors())
			this.getScore().put(c, 0);
	}

	/**
	 * return the competitors list of this competition
	 * 
	 * @return competitors : List<Competitor>
	 */
	public List<Competitor> getCompetitors() {
		return this.competitors;
	}

	/**
	 * Return the Map of scores in this competition
	 * 
	 * @return score : Map<Competitor, Integer>
	 */

	public Map<Competitor, Integer> getScore() {
		return this.score;
	}

	/**
	 * play method that start this competition
	 */
	public void play() {
		this.play(this.getCompetitors());
		this.printRanking(this.ranking());
	}

	/**
	 * Sort by desceding value the scores of competitors in the scoremap using
	 * MapUtil class and return the ranking Map produced
	 * 
	 * @return ranking map : Map<Competitor, Integer>
	 */
	public Map<Competitor, Integer> ranking() {
		return MapUtil.sortByDescendingValue(this.getScore());
	}

	/**
	 * play a match between the two competitors c1 & c2 which are given in param,
	 * incremente the score of the winner of the match and print the match result in
	 * stdout
	 * 
	 * @param c1 : Competitor
	 * @param c2 : Competitor
	 */
	protected void playMatch(Competitor c1, Competitor c2) {
		Competitor winner = this.getMatch().game(c1, c2);
		Competitor loser = loserFromWinner(winner, c1, c2);
		incrementScore(winner);
		publisherNotify(winner, loser);
	}
	
	/**
	 * Makes publisher notifies all the observers 
	 * @param winner : Competition
	 * @param loser : Competition
	 */
	
	public void publisherNotify(Competitor winner, Competitor loser) {
		if(publisher != null) publisher.notify(winner, loser);
	}
	
	/**
	 * Gets the loser from the winner of a contest
	 * @param winner : Competitor
	 * @param c1 : Competitor
	 * @param c2 : Competitor
	 * @return loser : Competitor
	 */
	
	protected Competitor loserFromWinner(Competitor winner, Competitor c1, Competitor c2) {
		Competitor loser = c1;
		if (winner.equals(c1)) loser = c2;
		return loser;
	}

	/**
	 * return the match class instance who's use to contest match in this
	 * competition
	 * 
	 * @return match : Match
	 */
	protected Match getMatch() {
		return this.match;
	}

	/**
	 * incremente the score of the given competitor by 1 in the scoremap of this
	 * competition
	 * 
	 * @param c
	 */
	protected void incrementScore(Competitor c) {
		this.getScore().put(c, this.getCompetitorScore(c) + 1);
	}

	/**
	 * return the score of the given competitor in param in this competition
	 * 
	 * @param c : the Competitor
	 * @return score : int
	 */
	protected int getCompetitorScore(Competitor c) {
		return this.getScore().get(c);
	}

	/**
	 * Gets the competitor at the given index from scor. If the index is > than
	 * score map then return the last possible competitor in the map travel
	 * 
	 * @param index : Integer
	 * @return competitor : Competitor
	 */

	public Competitor competitorFromScoreAtIndex(int index) {
		ArrayList<Competitor> setCompetitors = new ArrayList<Competitor>(this.ranking().keySet());
		ListIterator<Competitor> value = setCompetitors.listIterator();
		Competitor res;
		while (index != 0 && value.hasNext()) {
			value.next();
			index--;
		}
		try {
			res = (Competitor) value.next();
		} catch (NoSuchElementException err) {
			res = (Competitor) value.previous();
		}
		return res;
	}

	/**
	 * abstract play method that play a competition for a given competitors list and
	 * which will declared in subclass of this class to play the competition with
	 * the specificity of each sub class
	 * 
	 * @param competitors : List<Competitor> the list of competitors
	 */
	protected abstract void play(List<Competitor> competitors);

	/**
	 * abstract method that print in the stdout the given ranking result of this
	 * competition with the desired sub class format
	 * 
	 * @param ranking : the raking map Map<Competitor,Integer>
	 */
	protected abstract void printRanking(Map<Competitor, Integer> ranking);

}
