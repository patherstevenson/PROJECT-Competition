/**
 * CompoundSelection Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package selection;

import java.util.ArrayList;
import java.util.List;

import competition.CompetitionBasis;
import person.Competitor;
import util.FlatList;

public class CompoundSelection implements Selection {

	private List<Selection> selectionList = new ArrayList<Selection>();

	public CompoundSelection(List<Selection> selections) {
		this.initSelectionList(selections);
	}

	/**
	 * set the given selectList to selectionList of this CompoundSelection. By using
	 * the add method to prevent duplicate SelectionType in the selectionList of
	 * this CompoundSelection
	 * 
	 * @param selectList : List<Selection>
	 */
	private void initSelectionList(List<Selection> selectList) {
		for (Selection s : selectList) {
			this.addSelection(s);
		}
	}

	/**
	 * Gets the selectionList that contains all the selection method we want to
	 * apply on a CompetitionBasis
	 * 
	 * @return children : List<Selection>
	 */

	public List<Selection> getSelectionList() {
		return this.selectionList;
	}

	/**
	 * Verify if the given SelectionType is contains in the selectionList of this
	 * CompoundSelection
	 * 
	 * @param select : Selection
	 * @return True if selectionList contains param select Otherwise false
	 */
	public boolean alreadyInSelectionList(Selection select) {
		for (Selection s : this.getSelectionList()) {
			if (s.getClass().equals(select.getClass())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds the given selection to the selectionList if she's already contains in
	 * the selectionList of this CompoundSelection
	 * 
	 * @param selection : Selection
	 */

	public void addSelection(Selection selection) {
		if (!alreadyInSelectionList(selection)) {
			this.getSelectionList().add(selection);
		}
	}

	/**
	 * Removes the given selection from the selectionList
	 * 
	 * @param selection : Selection
	 */

	public void removeSelection(Selection selects) {
		int i = 0;
		boolean find = false;
		while (i < this.getSelectionList().size() && !find) {
			if (this.getSelectionList().get(i).getClass().equals(selects.getClass())) {
				this.getSelectionList().remove(i);
				find = true;
			}
			i++;
		}
	}

	/**
	 * select competitors in the ranking map result of the given CompetitionBasis in
	 * function of Selection Type that this.selectionList contains.
	 * 
	 * @param competition : CompetitionBasis
	 * @return the list of selected competitors : List<Competitor>
	 */
	public List<Competitor> select(CompetitionBasis competition) {
		List<List<Competitor>> finalistsByCompetition = new ArrayList<List<Competitor>>();
		for (Selection s : this.getSelectionList()) {
			finalistsByCompetition.add(s.select(competition));
		}
		return FlatList.mergeLists(finalistsByCompetition);
	}
}
