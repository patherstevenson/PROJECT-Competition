/**
 * Selection Interface
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package selection;

import java.util.List;

import competition.CompetitionBasis;
import person.Competitor;

/**
 * Objects that select the finalists of a competition to participate to the
 * final phase of master tournament
 */

public interface Selection {

	/**
	 * Selects competitors from a competition to participate to final phase of
	 * master tournament. If the number of competitors are not a power of 2 we take
	 * the lowering number which is power of 2
	 * 
	 * @param competition : CompetitionBasis
	 * @return finalists : List<Competitor>
	 */

	public List<Competitor> select(CompetitionBasis competition);

}
