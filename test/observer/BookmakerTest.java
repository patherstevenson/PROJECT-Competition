/**
 * BookmakerTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import observer.Bookmaker;
import observer.CompetitionPublisher;
import person.Competitor;
import match.MockMatch;

public class BookmakerTest {

	private Competitor c1;
	private Competitor c2;
	private Competitor c3;
	private Competitor c4;
	private Competitor c5;
	private Competitor c6;

	private List<Competitor> l1;

	private MockWithTwoCompetitors competitionOf2;
	private MockFirstCompetition firstCompet;
	private MockLastCompetition lastCompet;

	private CompetitionPublisher publisher;
	private Bookmaker bookMaker;
	private MockObserver mockObserver;

	private static int INITODD = 5;
	private static int LIMITODD = 1;

	private Competitor winner;
	private Competitor loser;

	@BeforeEach
	public void init() {
		bookMaker = new Bookmaker("bookmaker");
		mockObserver = new MockObserver();
		publisher = new CompetitionPublisher();
		publisher.addObserver(bookMaker);
		publisher.addObserver(mockObserver);
		competitionOf2 = new MockWithTwoCompetitors(new ArrayList<Competitor>(), new MockMatch(), publisher);

		winner = new Competitor("winner");
		loser = new Competitor("loser");

		initCompetitors();

		firstCompet = new MockFirstCompetition(l1, new MockMatch(), publisher);
		lastCompet = new MockLastCompetition(l1, new MockMatch(), publisher);

	}

	private void initCompetitors() {

		c1 = new Competitor("c1");
		c2 = new Competitor("c2");
		c3 = new Competitor("c3");
		c4 = new Competitor("c4");
		c5 = new Competitor("c5");
		c6 = new Competitor("c6");

		l1 = new ArrayList<Competitor>();
		l1.add(c1);
		l1.add(c2);
		l1.add(c3);
		l1.add(c4);
		l1.add(c5);
		l1.add(c6);
	}

	@Test
	public void should_Create_Publisher() {
		assertNotNull(publisher);
	}

	@Test
	public void should_Create_Observers() {
		assertNotNull(bookMaker);
		assertNotNull(mockObserver);
	}

	@Test
	public void should_Create_Competition() {
		assertNotNull(competitionOf2);
	}

	@Test
	public void observer_Should_Assist_To_All_Contests() {
		competitionOf2.play();
		assertTrue(mockObserver.getCallsOfUpdate() == competitionOf2.nbMatches());
	}

	@Test
	public void odd_Of_Winner_Of_Contest_Should_Lower() {
		competitionOf2.playMatch(winner, loser);
		competitionOf2.playMatch(winner, loser);
		assertTrue(bookMaker.competitorOdd(winner) < INITODD);
	}

	@Test
	public void odd_Of_Loser_Of_Contest_Should_Upper() {
		competitionOf2.playMatch(winner, loser);
		competitionOf2.playMatch(winner, loser);
		assertTrue(bookMaker.competitorOdd(loser) > INITODD);
	}

	@Test
	public void odd_Of_Competitors_Should_Variy_From_Match_To_Match() {
	}

	@Test
	public void odd_Of_Competitor_Should_Not_Be_Under_1() {
		competitionOf2.playMatch(winner, loser);
		competitionOf2.playMatch(winner, loser);
		competitionOf2.playMatch(winner, loser);
		competitionOf2.playMatch(winner, loser);
		competitionOf2.playMatch(winner, loser);
		competitionOf2.playMatch(winner, loser);
		assertTrue(bookMaker.competitorOdd(winner) == LIMITODD);
	}

	@Test
	public void observer_Should_Assist_To_Different_Competitions() {
		firstCompet.play();
		lastCompet.play();
		int totalMatches = firstCompet.nbMatches() + lastCompet.nbMatches();
		assertTrue(mockObserver.getCallsOfUpdate() == totalMatches);
	}

	@Test
	public void odd_Of_Competitor_Should_Evolve_Through_Different_Competitions() {
		// firstCompet making 6 contests having first competitor meeting each other
		// competitor and winning every contest
		firstCompet.play();
		assertTrue(bookMaker.competitorOdd(c1) == LIMITODD);
		assertTrue(bookMaker.competitorOdd(c2) == 6);
		assertTrue(bookMaker.competitorOdd(c3) == 6);
		assertTrue(bookMaker.competitorOdd(c4) == 6);
		assertTrue(bookMaker.competitorOdd(c5) == 6);
		assertTrue(bookMaker.competitorOdd(c6) == 6);

		// lastCompet keeping two first competitors of firstCompet having 3 contests
		// between both and making second competitor winnigng every match
		lastCompet.play();
		assertTrue(bookMaker.competitorOdd(c1) == (LIMITODD + 3));
		assertTrue(bookMaker.competitorOdd(c2) == (6 - 3));
	}

}
