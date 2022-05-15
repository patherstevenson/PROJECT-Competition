/**
 * WinnerSelectionTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package selection;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competition.League;
import match.MockMatch;
import person.Competitor;
import selection.Selection;
import selection.WinnerSelection;

public class WinnerSelectionTest extends SingleSelectionTest {

	private Selection selection;
	private League leagueWithCompetitors;

	protected Selection createSelection() {
		return new WinnerSelection();
	}

	@BeforeEach
	public void initWS() {
		this.selection = new WinnerSelection();
		this.leagueWithCompetitors = new League(competitors, new MockMatch());
	}

	@Test
	public void list_Of_Finalists_Should_Contain_Winner_Of_Competition() {
		this.leagueWithCompetitors.play();
		List<Competitor> l = selection.select(this.leagueWithCompetitors);
		Assertions.assertTrue(l.contains(leagueWithCompetitors.competitorFromScoreAtIndex(0)));
	}
}
