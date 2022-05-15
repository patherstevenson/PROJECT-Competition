/**
 * TournamentTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package competition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Arrays;
import java.util.Iterator;

import util.LenghtNotPowerOf2Exception;

import competition.CompetitionBasis;
import competition.Tournament;

import person.Competitor;
import person.NameList;

import match.Match;
import match.MockMatch;

public class TournamentTest extends CompetitionBasisTest {

    private Tournament tournament;
    private List<Competitor> competitors;
    private MockMatch mockMatch;

    private int nbOpponents;

    /**
     * Creates a new tournamet with competitors and MockMatch as match
     * 
     * @return new Tournament
     */

    protected CompetitionBasis createCompetition(List<Competitor> competitors, Match mockM)
            throws LenghtNotPowerOf2Exception {
        return new Tournament(competitors, mockM);
    }

    /**
     * Computes the scores of each competitor
     * 
     * @return sum : int
     */

    private int sumScore() {
        this.tournament.play(this.competitors);
        int sumScore = 0;
        for (Competitor c : this.tournament.getCompetitors())
            sumScore += this.tournament.getCompetitorScore(c);
        return sumScore;
    }

    @BeforeEach
    public void initTournamentEach() throws LenghtNotPowerOf2Exception {
        this.mockMatch = new MockMatch();
        this.competitors = NameList.createListOfCompetitors(8);
        this.tournament = new Tournament(this.competitors, this.mockMatch);
        this.nbOpponents = this.tournament.getCompetitors().size() - 1;
    }

    @Test
    public void should_Throw_LenghtNotPowerOf2Competitors_When_NbCompetitors_Not_A_Power_Of_Two()
            throws LenghtNotPowerOf2Exception {
        Assertions.assertThrows(LenghtNotPowerOf2Exception.class, () -> {
            new Tournament(NameList.createListOfCompetitors(7), new MockMatch());
        });
    }

    @Test
    public void total_Score_Should_Be_Equal_To_Number_Of_Number_Of_Opponents() {
        int sumScore = this.sumScore();
        Assertions.assertTrue(sumScore == this.nbOpponents);
    }

    @Test
    public void should_Rank_Players_From_The_Winner_To_Losers() {
        this.tournament.play(this.competitors);
        Map<Competitor, Integer> ranking = this.tournament.ranking();
        Iterator<Map.Entry<Competitor, Integer>> iterator = ranking.entrySet().iterator();

        Assertions.assertEquals(3, iterator.next().getValue());
        Assertions.assertEquals(2, iterator.next().getValue());
        Assertions.assertEquals(1, iterator.next().getValue());
        Assertions.assertEquals(1, iterator.next().getValue());
        Assertions.assertEquals(0, iterator.next().getValue());
        Assertions.assertEquals(0, iterator.next().getValue());
        Assertions.assertEquals(0, iterator.next().getValue());
        Assertions.assertEquals(0, iterator.next().getValue());
        Assertions.assertFalse(iterator.hasNext());
    }
}
