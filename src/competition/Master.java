/**
 * Master Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package competition;

import java.util.ArrayList;
import java.util.List;

import match.Match;
import observer.CompetitionPublisher;
import person.Competitor;
import selection.Selection;
import util.FindDivisor;
import util.FlatList;
import util.NumberOfCompetitorsNotSufficientException;
import util.LenghtNotPowerOf2Exception;
import util.RoundPowerOfTwo;

public class Master implements Competition {

	private final List<Competitor> competitors;
	private Selection selection;
	private Match match;
	private List<League> groups = new ArrayList<League>();
	private Tournament finalPhases;
	private CompetitionPublisher publisher;

	public Master(List<Competitor> competitors, Match match, Selection selection)
			throws NumberOfCompetitorsNotSufficientException {
		this.competitors = competitors;
		this.match = match;
		this.selection = selection;
		this.checkNumberOfCompetitors();
	}
	
	public Master(List<Competitor> competitors, Match match, Selection selection, CompetitionPublisher publisher) throws NumberOfCompetitorsNotSufficientException {
		this.competitors = competitors;
		this.match = match;
		this.selection = selection;
		this.publisher = publisher;
		this.checkNumberOfCompetitors();
	}

	/**
	 * Check if the competitors size list is < 1. If it's the case throws
	 * NumberOfCompetitorsNotSufficientException
	 * 
	 * @throws NumberOfCompetitorsNotSufficientException if competitors size list <
	 *                                                   1
	 */
	private void checkNumberOfCompetitors() throws NumberOfCompetitorsNotSufficientException {
		if (this.getCompetitors().size() < 1) {
			throw new NumberOfCompetitorsNotSufficientException();
		}
	}

	/**
	 * Gets the Groups list, List of Leagues that are pool stage of this Master
	 * 
	 * @return this.groups : List<League>
	 */
	public List<League> getGroups() {
		return this.groups;
	}

	/**
	 * add to this.groups to the given grps
	 * 
	 * @param grps : League
	 */
	private void addGroup(League grp) {
		this.getGroups().add(grp);
	}

	/**
	 * Gets finalPhases of this Master, which are a tournament
	 * 
	 * @return this.finalPhases : Tournament
	 */
	public Tournament getFinalPhases() {
		return this.finalPhases;
	}

	/**
	 * Set the given tournament as finalPhases for this Master
	 */
	private void setFinalPhases(Tournament tournament) {
		this.finalPhases = tournament;
	}

	/**
	 * return the match class instance who's use to contest match in this
	 * competition
	 * 
	 * @return match : Match
	 */
	public Match getMatch() {
		return this.match;
	}

	/**
	 * Gets the competitors
	 * 
	 * @return the competitors
	 */
	public List<Competitor> getCompetitors() {
		return competitors;
	}

	/**
	 * Gets the selection
	 * 
	 * @return selection : Selection
	 */
	public Selection getSelection() {
		return this.selection;
	}

	/**
	 * The play method which will divide competitors into GroupPhases, play it,
	 * select finalists from groups and make them play into the FinalPhases.
	 */
	public void play() {
		this.competitorsIntoGroups();
		playPrimaryPhases();
		this.playFinalPhases(this.selectFinalists());
	}

	/**
	 * Puts the competitors into groups by using the util.FindDivisor abstract class
	 * 
	 * Explain : Then we do this.getCompetitors() / divisor League And if
	 * this.getCompetitors().size() % divisor) != 0 we do one league with
	 * this.getCompetitors() - divisor * (this.getCompetitors() / divisor)
	 * competitors (the rest of competitors that is not in a group after
	 * this.generateGroups(comps, divisor) call)
	 */
	protected void competitorsIntoGroups() {
		List<Competitor> comps = this.getCompetitors();
		int divisor = FindDivisor.findNearestIntDivisor(this.getCompetitors().size());
		this.generateGroups(comps, divisor);
		if ((this.getCompetitors().size() % divisor) != 0) {
			this.addGroup(new League(comps.subList((this.getCompetitors().size() / divisor) * divisor, comps.size()),
					this.getMatch()));
		}
	}
	
	/**
	 * Generates groupes
	 * @param competitors
	 * @param divisor
	 */

	private void generateGroups(List<Competitor> competitors, int divisor) {
		int i, y;
		for (i = 0, y = divisor; y <= competitors.size(); y += divisor) {
			if(publisher == null) this.addGroup(new League(competitors.subList(i, y), this.getMatch()));
			else this.addGroup(new League(competitors.subList(i, y), this.getMatch(), publisher));
			i = y;
		}
	}

	/**
	 * Call the play method of each League in the groups list of this Master
	 */
	private void playPrimaryPhases() {
		int i = 1;
		for (League l : this.getGroups()) {
			this.printGroupNumber(i);
			l.play();
			i++;
		}
	}

	/**
	 * Selects all the finalists from the groups list of this Master. And merge the
	 * finalists list groups by using flattenList method and return the obtained
	 * merged list. If the merged list contains a number of Competitors which is not
	 * a power of 2 then we round this number to the nearest previous power of 2 by
	 * using the util.RoundPowerOfTwo class
	 * 
	 * @return finalists : List<Competitor>
	 */
	protected List<Competitor> selectFinalists() {
		List<List<Competitor>> finalistsByGroup = new ArrayList<List<Competitor>>();
		for (League l : this.getGroups())
			finalistsByGroup.add(this.getSelection().select(l));
		List<Competitor> finalists = FlatList.mergeLists(finalistsByGroup);
		return finalists.subList(0, RoundPowerOfTwo.findPreviousPowerOf2(finalists.size()));
	}

	/**
	 * Set the finalPhases attribut and Plays the final phase for the given
	 * finalists list for this Master. new Tournament can throw
	 * LenghtNotPowerOf2Exception if the given finalists list is not a power of 2
	 * but normaly this should not happen because we do a round to the nearest
	 * previous power of 2 in the selectFinalists method.
	 * 
	 * @param finalists   : List<Competitor>
	 * @param competition : CompetitionBasis
	 * @throws LenghtNotPowerOf2Exception
	 */
	private void playFinalPhases(List<Competitor> finalists) {
		try {
			if(publisher == null) this.setFinalPhases(new Tournament(finalists, this.getMatch()));
			if(publisher != null) this.setFinalPhases(new Tournament(finalists, this.getMatch(), publisher));
			this.printFinalPhasesAnnouncement();
			this.getFinalPhases().play();
		} catch (LenghtNotPowerOf2Exception err) {
			if (finalists.size() > 1) {
				// this case should never happen except of bug
				System.out.println("selectFinalists(): RoundPowerOfTwo.findPreviousPowerOf2(finalists.size())");
			}
			/**
			 * else finalists.size() == 1 then we don't need to start a tournament because
			 * tournament can have only one competitors
			 */

		}

	}

	private void printGroupNumber(int i) {
		System.out.println("\n--------------- POOL " + i + " ---------------");
	}

	private void printFinalPhasesAnnouncement() {
		System.out.println("\n------------- FINALPHASE -------------");
	}

}
