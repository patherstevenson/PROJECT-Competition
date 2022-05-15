/**
 * MockObserver Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package observer;

import observer.MatchObserver;
import person.Competitor;

public class MockObserver implements MatchObserver {

	private int callsToUpdate = 0;

	@Override
	public void update(Competitor winner, Competitor loser) {
		callsToUpdate += 1;
	}

	public int getCallsOfUpdate() {
		return callsToUpdate;
	}

}
