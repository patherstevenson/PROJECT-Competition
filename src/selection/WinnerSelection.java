/**
 * WinnerSelection class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import competition.CompetitionBasis;
import person.Competitor;

public class WinnerSelection implements Selection {

	/**
	 * select the first competitors in the ranking map result of the given played
	 * CompetitionBasis
	 * 
	 * @param competition : CompetitionBasis
	 * @return the list with the winner of the given played CompetitionBasis :
	 *         List<Competitor>
	 */
	public List<Competitor> select(CompetitionBasis competition) {
		List<Competitor> winner = new ArrayList<Competitor>();
		try {
			winner = new ArrayList<Competitor>(Arrays.asList(competition.competitorFromScoreAtIndex(0)));
		} catch (NoSuchElementException err) {
			return winner;
		}
		return winner;
	}

}
