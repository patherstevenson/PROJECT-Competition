/**
 * CompoundSelectionTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package selection;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import person.Competitor;
import person.NameList;
import competition.League;
import match.MockMatch;
import selection.*;

public class CompoundSelectionTest extends SelectionTest {

	private CompoundSelection compoundSelect;
	private League leagueWithCompetitors;

	protected Selection createSelection() {
		return new CompoundSelection(List.of(new WinnerSelection(), new SecondBestSelection()));
	}

	@BeforeEach
	public void initCS() {
		this.compoundSelect = new CompoundSelection(List.of(new WinnerSelection(), new SecondBestSelection()));
		this.leagueWithCompetitors = new League(competitors, new MockMatch());
	}

	@Test
	public void setSelection_call_in_the_constructor_remove_the_duplicates_selection_if_there_is_in_the_given_selection_list() {
		Assertions.assertTrue(new CompoundSelection(List.of(new WinnerSelection(), new WinnerSelection()))
				.getSelectionList().size() == 1);
	}

	@Test
	public void remove_Selection_Should_Remove_a_Given_SelectionType_in_the_List_Of_Selection_In_SelectionList() {
		Assertions.assertEquals(2, this.compoundSelect.getSelectionList().size());
		compoundSelect.removeSelection(new SecondBestSelection());
		Assertions.assertEquals(1, this.compoundSelect.getSelectionList().size());
		Assertions.assertFalse(
				this.compoundSelect.getSelectionList().get(0).getClass() == new SecondBestSelection().getClass());
	}

	@Test
	public void addSelection_Should_not_add_a_Given_SelectionType_That_is_Already_contains_in_the_selectionList() {
		Assertions.assertTrue(this.compoundSelect.getSelectionList().size() == 2);
		this.compoundSelect.addSelection(new WinnerSelection());
		// shoud not be add then still selectionList still size 2
		Assertions.assertTrue(this.compoundSelect.getSelectionList().size() == 2);
	}

	@Test
	public void addSelection_Should_Add_a_given_SelectionType_in_the_List_of_SelectionList_If_She_Not_Already_In_selectionList() {
		this.compoundSelect.removeSelection(new WinnerSelection()); // previously tested
		Assertions.assertTrue(
				this.compoundSelect.getSelectionList().get(0).getClass() == new SecondBestSelection().getClass());
		this.compoundSelect.addSelection(new WinnerSelection());
		Assertions.assertEquals(2, this.compoundSelect.getSelectionList().size());
		Assertions.assertTrue(
				this.compoundSelect.getSelectionList().get(1).getClass() == new WinnerSelection().getClass());
	}

	@Test
	public void winner_Of_Competition_Should_Be_In_Selected_List() {
		this.leagueWithCompetitors.play();
		List<Competitor> finalists = compoundSelect.select(this.leagueWithCompetitors);
		Assertions.assertTrue(finalists.contains(this.leagueWithCompetitors.competitorFromScoreAtIndex(0)));
	}

	@Test
	public void second_Best_Of_Competition_Should_Be_In_Selected_List() {
		this.leagueWithCompetitors.play();
		List<Competitor> finalists = compoundSelect.select(this.leagueWithCompetitors);
		Assertions.assertTrue(finalists.contains(this.leagueWithCompetitors.competitorFromScoreAtIndex(1)));
	}

	@Test
	public void Select_Should_return_only_one_competitor_if_there_is_less_than_two_competitors() {
		League leagueWithOneCompetitors = new League(NameList.createListOfCompetitors(1), new MockMatch());
		leagueWithOneCompetitors.play();
		Assertions.assertTrue(compoundSelect.select(leagueWithOneCompetitors).size() == 1);
	}

}
