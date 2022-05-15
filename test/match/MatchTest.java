/**
 * MatchTest abstract Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package match;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import match.Match;
import person.Competitor;

public abstract class MatchTest {
	
	private Match match;
	private Competitor c1;
	private Competitor c2;
	
	/**
	 * Creates a match 
	 */

	protected abstract Match createMatch();
	
	@BeforeEach
	public void init() {
		this.c1 = new Competitor("Bach");
		this.c2 = new Competitor("Vivaldi");
		this.match = this.createMatch();
	}
	
	@Test
	public void should_Create_Match(){
		Assertions.assertNotNull(this.match);
	}

	@Test
	public void should_Have_A_Single_Winner_After_Contest() {
		Competitor winner = match.game(this.c1, this.c2);
		Assertions.assertTrue(winner.equals(c1) || winner.equals(c2));
	}
}
