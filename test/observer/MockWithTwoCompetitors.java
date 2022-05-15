/**
 * MockWithTwoCompetitors Class
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

public class MockWithTwoCompetitors extends CompetitionBasis {

	private int nbContests = 0;

	public MockWithTwoCompetitors(List<Competitor> competitors, Match match, CompetitionPublisher publisher) {
		super(competitors, match, publisher);
	}

	@Override
	protected void initScore() {
	}

	public void play() {
		this.play(null);
	}

	@Override
	protected void play(List<Competitor> competitors) {
		Competitor Zakaria = new Competitor("Zakaria");
		Competitor Stevenson = new Competitor("Stevenson");
		// Odds
		this.playMatch(Zakaria, Stevenson); // Zakaria 4 - Stevenson 6
		nbContests += 1;

		this.playMatch(Zakaria, Stevenson); // Zakaria 3 - Stevenson 7
		nbContests += 1;

		this.playMatch(Zakaria, Stevenson); // Zakaria 2 - Stevnson 8
		nbContests += 1;

		this.playMatch(Zakaria, Stevenson); // Zakaria 1 - Stevenson 9
		nbContests += 1;

		this.playMatch(Zakaria, Stevenson); // Zakaria 1 - Stevenson 10
		nbContests += 1;

		this.playMatch(Stevenson, Zakaria); // Zakaria 2 - Stevenson 9
		nbContests += 1;
	}

	@Override
	protected void playMatch(Competitor c1, Competitor c2) {
		Competitor winner = this.getMatch().game(c1, c2);
		Competitor loser = loserFromWinner(winner, c1, c2);
		publisherNotify(winner, loser);
	}

	public int nbMatches() {
		return nbContests;
	}

	@Override
	protected void printRanking(Map<Competitor, Integer> ranking) {
	}

}
