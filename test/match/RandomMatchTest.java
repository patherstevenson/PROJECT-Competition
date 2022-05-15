/**
 * RandomMatchTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package match;

import person.Competitor;
import match.Match;

public class RandomMatchTest extends MatchTest {
	
	/**
	 * Creates a match with winner given randomly
	 * @return match : RandomMatch
	 */
	
	@Override
	protected Match createMatch() {
		return new RandomMatch();
	}
}
