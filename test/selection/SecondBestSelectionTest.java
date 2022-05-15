/**
 * SecondBestSelectionTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package selection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import competition.League;
import person.Competitor;
import person.NameList;
import match.MockMatch;
import selection.SecondBestSelection;
import selection.Selection;

public class SecondBestSelectionTest extends SingleSelectionTest {

	private Selection selection;
	private League leagueWithCompetitors;

	protected Selection createSelection() {
		return new SecondBestSelection();
	}

	@BeforeEach
	public void initSBS() {
		this.selection = this.createSelection();
		this.leagueWithCompetitors = new League(competitors, new MockMatch());
	}

	@Test
	public void list_Of_Finalists_Should_Contain_Second_Best_Competitor_Of_Competition() {
		this.leagueWithCompetitors.play();
		Assertions.assertTrue(selection.select(leagueWithCompetitors)
				.contains(this.leagueWithCompetitors.competitorFromScoreAtIndex(1)));
	}

	@Test
	public void Select_should_return_empty_array_if_there_is_less_than_two_competitors() {
		League leagueWithOneCompetitors = new League(NameList.createListOfCompetitors(1), new MockMatch());
		leagueWithOneCompetitors.play();
		Assertions.assertTrue(selection.select(leagueWithOneCompetitors).isEmpty());
	}
}
