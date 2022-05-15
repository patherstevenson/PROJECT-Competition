/**
 * SecondBestSelection class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import competition.CompetitionBasis;
import person.Competitor;

public class SecondBestSelection implements Selection {

	/**
	 * select the second competitors in the ranking map result of the given played
	 * CompetitionBasis, if his competitors size is >= 2. Otherwise return empty
	 * array
	 * 
	 * @param competition : CompetitionBasis
	 * @return the list with the second winner of the given played CompetitionBasis
	 *         if getCompetitors >= 2. Otherwise return empty array
	 */
	public List<Competitor> select(CompetitionBasis competition) {
		if (competition.getCompetitors().size() < 2) {
			return new ArrayList<Competitor>();
		} else {
			return new ArrayList<Competitor>(Arrays.asList(competition.competitorFromScoreAtIndex(1)));
		}
	}

}
