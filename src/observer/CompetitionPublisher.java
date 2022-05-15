/**
 * CompetitionPublisher Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package observer;

import java.util.ArrayList;
import java.util.List;

import person.Competitor;

/**
 * Notifies all the MatchObservers
 */

public class CompetitionPublisher {

	private List<MatchObserver> observers;

	public CompetitionPublisher() {
		observers = new ArrayList<MatchObserver>();
	}

	/**
	 * Adds an observer
	 * 
	 * @param observer : MatchObserver
	 */

	public void addObserver(MatchObserver observer) {
		observers.add(observer);
	}

	/**
	 * Removes an observer
	 * 
	 * @param observer : MatchObserver
	 */

	public void removeObserver(MatchObserver observer) {
		observers.remove(observer);
	}

	/**
	 * Notifies all the observers
	 * 
	 * @param winner : Competitor
	 * @param loser  : Competitor
	 */

	public void notify(Competitor winner, Competitor loser) {
		for (MatchObserver o : observers)
			o.update(winner, loser);
	}
}
