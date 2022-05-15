/**
 * Tournament Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package competition;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

import observer.CompetitionPublisher;
import person.Competitor;
import match.Match;
import util.LenghtNotPowerOf2Exception;

public class Tournament extends CompetitionBasis {

    /**
     * cmp of phases of this tournament
     */
    private int phase = 1;

    /**
     * Create a Tournament for the given competitors list and that use the given
     * match type (for the game method)
     * 
     * @param competitors : the list of competitors who participates in the
     *                    tournament List<Competitors>
     * @param matchType   : the match type that is use in this tournament
     * @throws LenghtNotPowerOf2Exception : if the lenght of the competitors list
     *                                    argument is not a power of 2 then throws
     *                                    an LenghtNotPowerOf2Exception
     */
    public Tournament(List<Competitor> competitors, Match match) throws LenghtNotPowerOf2Exception {
        super(competitors, match);

        if (!this.checkCompetitorsLenghtPowerOf2())
            throw new LenghtNotPowerOf2Exception("Competitors lenght\n");
    }

    /**
     * Constructor with Publisher
     * 
     */
    public Tournament(List<Competitor> competitors, Match match, CompetitionPublisher publisher)
            throws LenghtNotPowerOf2Exception {
        super(competitors, match, publisher);

        if (!this.checkCompetitorsLenghtPowerOf2())
            throw new LenghtNotPowerOf2Exception("Competitors lenght\n");
    }

    /**
     * if the lenght of the competitors list of this tournament is a power of 2
     * return True otherwise False
     * 
     * @return True or False : boolean
     */
    private boolean checkCompetitorsLenghtPowerOf2() {
        int len = this.getCompetitors().size();
        return (len > 1) && ((len & (len - 1)) == 0);
    }

    /**
     * return the actual phase number of this tournament
     * 
     * @return the number phase : int
     */
    private int getPhase() {
        return this.phase;
    }

    /**
     * start playing the tournament for the given competitors list and while there
     * is not a single winner
     * 
     * @param competitors the competitors list who participates to the tournament
     */
    protected void play(List<Competitor> competitors) {
        List<Competitor> currentCompetitors = new ArrayList<Competitor>(competitors);
        while (!this.hasAWinner(currentCompetitors)) {
            this.playAllMatchOfPhase(currentCompetitors);
            currentCompetitors = this.winnersOfPhase(currentCompetitors);
            this.incrementPhase();
        }
    }

    /**
     * if the given competitors list have a single winner (size == 1) return true
     * otherwise return false
     * 
     * @param competitors the given competitors list where we check if there is only
     *                    one left copmetitor
     * @return True or False : boolean
     */
    private boolean hasAWinner(List<Competitor> competitors) {
        if (competitors.size() == 2) {
            this.printFinalAnnouncement();
        }
        if (competitors.size() >= 4) {
            this.printPhaseNumber();
        }
        return competitors.size() == 1;
    }

    /**
     * play all the matchs of a the current phase for the given competitors list
     * with n/2 calls to the super method playMatch where n == len competitors
     * 
     * @param competitors the given copmetitors list for which we want to play all
     *                    matchs
     */
    private void playAllMatchOfPhase(List<Competitor> competitors) {
        for (int i = 0; i <= competitors.size() - 2; i += 2)
            this.playMatch(competitors.get(i), competitors.get(i + 1));
    }

    /**
     * return the list of winners of the last phase that was played for the given
     * competitors list. If the given competitors list lenght is n then the list of
     * winners that is returned must have a lenght of n/2
     * 
     * @param competitors the given list of competitors where we need to take
     *                    winners
     * @return the list of the winners of the last phase : List<Competitor>
     */
    private List<Competitor> winnersOfPhase(List<Competitor> competitors) {
        List<Competitor> winnersOfPhaseList = new ArrayList<Competitor>();
        for (Competitor c : competitors) {
            if (this.getCompetitorScore(c) == this.getPhase())
                winnersOfPhaseList.add(c);
        }
        return winnersOfPhaseList;
    }

    /**
     * incremente the phase number of this tournament by 1
     */
    private void incrementPhase() {
        this.phase += 1;
    }

    /**
     * print in the stdout the podium of this tournament with the desired format
     * from a given ranking sorted map of the result
     * 
     * @param ranking the ranking sorted map of the result of this tournament
     */
    protected void printRanking(Map<Competitor, Integer> ranking) {
        Iterator<Map.Entry<Competitor, Integer>> iterator = ranking.entrySet().iterator();

        System.out.println("\n*** Podium ***\n" + "\nFirst  : " + iterator.next().getKey().getName() + "\nSecond : "
                + iterator.next().getKey().getName()
                + (iterator.hasNext() ? "\nThird  : " + iterator.next().getKey().getName() : ""));
    }

    /**
     * print in the stdout the number of the current phase with the desired format
     */
    private void printPhaseNumber() {
        System.out.println("\n--- PHASE " + this.getPhase() + " ---\n");
    }

    /**
     * print the final announcement in the stdout with the desired format
     */
    private void printFinalAnnouncement() {
        System.out.println("\n--- FINAL ---\n");
    }

    /**
     * print the tournament announcement in the stdout with the desired format
     */
    protected void printAnnouncement() {
        System.out.println("\n------------- Tournament -------------");
    }
}
