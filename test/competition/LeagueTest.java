/**
 * LeagueTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package competition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Iterator;

import match.Match;
import match.MockMatch;
import competition.League;
import competition.CompetitionBasis;
import person.Competitor;
import person.NameList;

public class LeagueTest extends CompetitionBasisTest {

    private League league;
    private List<Competitor> competitors;
    private MockMatch mockMatch;

    private int nbOpponents;

    /**
     * Returns a new League with competitors given and mockMatch as Match
     * 
     * @return new League
     */

    protected CompetitionBasis createCompetition(List<Competitor> competitors, Match mockM) {
        return new League(competitors, mockM);
    }

    /**
     * Computes the score of each competitor in league competition
     * 
     * @return score : int
     */

    private int sumScore() {
        this.league.play(this.competitors);
        int sumScore = 0;
        for (Competitor c : this.league.getScore().keySet())
            sumScore += this.league.getCompetitorScore(c);
        return sumScore;
    }

    @BeforeEach
    public void initLeagueEach() {
        this.mockMatch = new MockMatch();
        this.competitors = NameList.createListOfCompetitors(5);
        this.league = new League(this.competitors, this.mockMatch);
        this.league = new League(competitors, this.mockMatch);
        this.nbOpponents = this.competitors.size();
    }

    @Test
    public void number_Of_Matches_Should_Be_Equal_To_NbCompetitors_Times_NbCompetitors_Less_One() {
        this.league.play(this.competitors);
        Assertions.assertTrue(this.mockMatch.getNbCallToGame() == (this.nbOpponents * (this.nbOpponents - 1)));
    }

    @Test
    public void number_Of_Matches_Should_Be_Equal_To_Total_Victories() {
        int sumScore = this.sumScore();
        Assertions.assertEquals(sumScore, this.nbOpponents * (this.nbOpponents - 1));
    }

    @Test
    public void each_Competitor_Should_Play_Two_Times_Against_All_Other_Competitors() {
        this.league.play(this.competitors);
        this.mockMatch.getNbPlayedMatchMap().forEach((c, nbMatchPlayed) -> {
            Assertions.assertEquals(2 * (this.nbOpponents - 1), nbMatchPlayed);
        });
    }

    @Test
    public void score_Of_Each_Competitor_Should_Not_Exceed_Two_Times_Number_Of_Opponents() {
        this.league.play(this.competitors);
        this.league.getScore().forEach((c, score) -> {
            Assertions.assertTrue((2 * (this.nbOpponents - 1)) >= score);
        });
    }

    @Test
    public void score_Of_Each_Competitor_Should_Be_Equal_To_Number_Of_Opponents_With_MockMatch() {
        this.league.play(this.competitors);
        this.league.getScore().forEach((c, score) -> {
            Assertions.assertEquals((this.nbOpponents - 1), score);
        });
    }

    @Test
    public void competitors_Should_Have_Score_Same_When_Ranking_With_MockMatch() {
        this.league.play(this.competitors);
        Map<Competitor, Integer> ranking = this.league.ranking();
        Iterator<Map.Entry<Competitor, Integer>> iterator = ranking.entrySet().iterator();

        while (iterator.hasNext()) {
            Assertions.assertEquals(this.nbOpponents - 1, iterator.next().getValue());
        }
    }
}
