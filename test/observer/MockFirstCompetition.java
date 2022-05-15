/**
 * MockFirstCompetition Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package observer;

import java.util.List;
import java.util.Map;

import competition.CompetitionBasis;
import match.Match;
import observer.CompetitionPublisher;
import person.Competitor;

public class MockFirstCompetition extends CompetitionBasis {

	private int nbContest = 0;

	public MockFirstCompetition(List<Competitor> competitors, Match match, CompetitionPublisher publisher) {
		super(competitors, match, publisher);
	}

	@Override
	protected void play(List<Competitor> competitors) {
		this.playMatch(competitors.get(0), competitors.get(1));
		nbContest += 1;

		this.playMatch(competitors.get(0), competitors.get(2));
		nbContest += 1;

		this.playMatch(competitors.get(0), competitors.get(3));
		nbContest += 1;

		this.playMatch(competitors.get(0), competitors.get(4));
		nbContest += 1;

		this.playMatch(competitors.get(0), competitors.get(5));
		nbContest += 1;
	}

	public int nbMatches() {
		return nbContest;
	}

	@Override
	protected void printRanking(Map<Competitor, Integer> ranking) {
	}

}
