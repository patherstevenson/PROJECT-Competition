/**
 * SingleSelectionTest abstract Class
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
import selection.Selection;

public abstract class SingleSelectionTest extends SelectionTest {

	private Selection selection;
	private League leagueWithCompetitors;

	/**
	 * Creates a Selection to test
	 * 
	 * @return selection : Selection
	 */
	protected abstract Selection createSelection();

	@BeforeEach
	public void initS() {
		this.selection = this.createSelection();
		this.leagueWithCompetitors = new League(competitors, new MockMatch());
	}

	@Test
	public void list_Of_Finalists_Should_Contain_Only_One_Competitor() {
		this.leagueWithCompetitors.play();
		Assertions.assertFalse(this.selection.select(this.leagueWithCompetitors).size() != 1);
	}

}
