/**
 * CompetitorTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package person;

import person.PersonBasis;
import person.Competitor;

public class CompetitorTest extends PersonBasisTest {

	protected PersonBasis createPerson(String name) {
		return new Competitor(name);
	}
}
