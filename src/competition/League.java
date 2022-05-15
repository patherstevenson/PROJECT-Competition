/**
 * League Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package competition;

import java.util.List;
import java.util.Map;

import observer.CompetitionPublisher;
import match.Match;
import person.Competitor;

public class League extends CompetitionBasis {

    /**
     * Create a League for the given competitors list and that use the given match
     * type (for the game method)
     * 
     * @param competitors : the list of competitors who participates in the
     *                    tournament List<Competitors>
     * @param matchType   : the match type that is use in this tournament
     */
    public League(List<Competitor> competitors, Match match) {
        super(competitors, match);
    }

    /**
     * Constructor with CompetitionPublisher
     */
    public League(List<Competitor> competitors, Match match, CompetitionPublisher publisher) {
        super(competitors, match, publisher);
    }

    /**
     * play method that make play competitors in the given competitors list between
     * each other competitors
     */
    protected void play(List<Competitor> competitors) {
        for (Competitor c1 : competitors)
            this.competitorAgainstOthers(c1);
    }

    /**
     * play all the match that the given competitor can do in this league which is
     * against all the other competitors
     * 
     * @param c1 the given competitor for which we want to play all his matchs
     */
    private void competitorAgainstOthers(Competitor c1) {
        for (Competitor c2 : this.getCompetitors()) {
            if (!c1.equals(c2))
                this.playMatch(c1, c2);
        }
    }

    /**
     * print in the stdout the ranking of this League with the desired format and of
     * all the competitors for the given ranking map of results
     * 
     * @param ranking
     */
    protected void printRanking(Map<Competitor, Integer> ranking) {
        System.out.println("\n*** Ranking ***\n");

        ranking.forEach((c, score) -> {
            System.out.println(c.getName() + " - " + score);
        });
        System.out.println("\n");
    }

    /**
     * print the league announcement in the stdout with the desired format
     */
    protected void printAnnouncement() {
        System.out.println("\n--------------- LEAGUE ---------------\n");
    }
}
