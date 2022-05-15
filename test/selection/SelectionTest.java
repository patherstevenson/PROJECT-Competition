/**
 * SelectionTest abstract Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package selection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import competition.League;
import match.MockMatch;
import person.Competitor;
import person.NameList;
import selection.Selection;

public abstract class SelectionTest {

	private Selection selection;
	private League leagueWithNoCompetitors;
	protected static List<Competitor> competitors;
	private League leagueWithCompetitors;

	/**
	 * Creates a Selection to test
	 * 
	 * @return selection : Selection
	 */
	protected abstract Selection createSelection();

	@BeforeAll
	public static void initAll() {
		competitors = NameList.createListOfCompetitors(8);
	}

	@BeforeEach
	public void init() {
		this.selection = this.createSelection();
		this.leagueWithNoCompetitors = new League(new ArrayList<Competitor>(), new MockMatch());
		this.leagueWithCompetitors = new League(competitors, new MockMatch());
	}

	@Test
	public void Select_in_a_Not_Played_LeagueWithNoCompetitors_return_Empty_Array() {
		Assertions.assertTrue(this.selection.select(this.leagueWithNoCompetitors).isEmpty());
	}

	@Test
	public void Select_in_a_played_LeagueWithNoCompetitors_throw_Nothing() {
		this.leagueWithNoCompetitors.play();
		Assertions.assertDoesNotThrow(() -> {
			this.selection.select(this.leagueWithNoCompetitors);
		});
	}

	@Test
	public void Select_in_a_LeagueWithNoCompetitors_return_Empty_Array_After_PlayMethod_Called() {
		this.leagueWithNoCompetitors.play();
		Assertions.assertTrue(this.selection.select(this.leagueWithNoCompetitors).isEmpty());
	}
}
