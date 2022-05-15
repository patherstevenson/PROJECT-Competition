/**
 * CompetitionBasisTest abstract Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package competition;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import competition.CompetitionBasis;
import match.MockMatch;
import match.Match;
import person.Competitor;
import person.NameList;
import util.LenghtNotPowerOf2Exception;

public abstract class CompetitionBasisTest {

    private CompetitionBasis competition;
    private List<Competitor> competitors;
    private MockMatch mockMatch;
    private Competitor c1, c2;

    protected abstract CompetitionBasis createCompetition(List<Competitor> competitors, Match mockM)
            throws LenghtNotPowerOf2Exception;

    /**
     * Makes three times playMatch method of competition between competitors c1 and
     * c2
     */

    private void playMatchThreeTimes() {
        this.competition.playMatch(this.c1, this.c2);
        this.competition.playMatch(this.c1, this.c2);
        this.competition.playMatch(this.c1, this.c2);
    }

    /**
     * Gives the sum of total victories scored by all the competitors
     * 
     * @return sumScore : int
     */

    private int sumScore() {
        this.competition.play();
        int sumScore = 0;
        for (Competitor c : this.competition.getCompetitors())
            sumScore += this.competition.getCompetitorScore(c);
        return sumScore;
    }

    @BeforeEach
    public void initEach() throws LenghtNotPowerOf2Exception {
        this.mockMatch = new MockMatch();
        this.competitors = NameList.createListOfCompetitors(4);
        this.competition = this.createCompetition(this.competitors, this.mockMatch);
        this.c1 = competitors.get(0);
        this.c2 = competitors.get(1);
    }

    @Test
    public void should_Create_Match() {
        Assertions.assertNotNull(this.competition.getMatch());
    }

    @Test
    public void should_Create_Map_Score() {
        Assertions.assertNotNull(this.competition.getScore());
    }

    @Test
    public void should_Create_List_Of_Competitors() {
        Assertions.assertNotNull(this.competitors);
    }

    @Test
    public void should_Create_A_Competition() {
        Assertions.assertNotNull(this.competition);
    }

    @Test
    public void should_Initialize_Competitors_In_Score() {
        for (Competitor c : this.competition.getCompetitors()) {
            Assertions.assertTrue(this.competition.getScore().containsKey(c));
        }
    }

    @Test
    public void should_Initialize_The_Number_Of_Victories_Of_Each_Competitor_To_0() {
        for (Competitor c : this.competition.getCompetitors()) {
            Assertions.assertEquals(0, this.competition.getCompetitorScore(c));
        }
    }

    @Test
    public void should_Score_A_Victory_For_Winner_When_Play_Match() {
        this.playMatchThreeTimes();
        Assertions.assertTrue(this.competition.getCompetitorScore(this.c1) == 3);
    }

    @Test
    public void should_Call_Game_Method_Of_Match_When_PlayMatch() {
        this.playMatchThreeTimes();
        Assertions.assertFalse(this.mockMatch.getNbCallToGame() != 3);
    }

    @Test
    public void competitors_Should_Score_When_Competition() {
        int totalScore = this.sumScore();
        Assertions.assertTrue(totalScore > 0);
    }

    @Test
    public void should_PlayMatches_When_Competition() {
        this.competition.play();
        Assertions.assertTrue(this.mockMatch.getNbCallToGame() > 0);
    }

    @Test
    public void total_Score_Equal_To_Number_Of_Played_Matchs_With_MockMatch() {
        int totalScore = this.sumScore();
        Assertions.assertTrue((totalScore > 0) & (totalScore == this.mockMatch.getNbCallToGame()));
    }

    @Test
    public void competitorFromScoreAtIndex_return_the_correct_competitor_from_the_ranking_map() {
        this.competition.play();
        Assertions.assertEquals(this.competition.competitorFromScoreAtIndex(2),
                this.competition.ranking().keySet().toArray()[2]);
    }

    @Test
    public void competitorFromScoreAtIndex_return_the_last_competitor_from_the_ranking_map_If_Given_Index_Is_Too_Larger_Than_Ranking_Map() {
        this.competition.play();
        Assertions.assertEquals(this.competition.competitorFromScoreAtIndex(100),
                this.competition.ranking().keySet().toArray()[this.competitors.size() - 1]);
    }

}
