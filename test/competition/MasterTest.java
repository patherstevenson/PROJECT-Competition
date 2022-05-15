/**
 * MasterTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package competition;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.NumberOfCompetitorsNotSufficientException;
import util.FindDivisor;
import util.FlatList;
import competition.Master;
import match.MockMatch;
import person.Competitor;
import person.NameList;
import selection.*;

public class MasterTest {

    private Master master_WS; // with winnerSelection

    private Master master_WS_SBS; // With WinnerSelection and SecondBestSelection

    private int sumScore(CompetitionBasis competition) {
        competition.play();
        int sumScore = 0;
        for (Competitor c : competition.getCompetitors())
            sumScore += competition.getCompetitorScore(c);
        return sumScore;
    }

    @BeforeEach
    public void init() throws NumberOfCompetitorsNotSufficientException {
        this.master_WS = new Master(NameList.createListOfCompetitors(15), new MockMatch(), new WinnerSelection());
        this.master_WS_SBS = new Master(NameList.createListOfCompetitors(53), new MockMatch(),
                new CompoundSelection(List.of(new WinnerSelection(), new SecondBestSelection())));

    }

    @Test
    public void List_Of_Zero_Competitors_Should_Throws_Exception() throws NumberOfCompetitorsNotSufficientException {
        Assertions.assertThrows(NumberOfCompetitorsNotSufficientException.class, () -> {
            new Master(NameList.createListOfCompetitors(0), new MockMatch(), new WinnerSelection());
        });
    }

    @Test
    public void List_Of_Negative_Number_Competitors_Should_Throws_Exception()
            throws NumberOfCompetitorsNotSufficientException {
        Assertions.assertThrows(NumberOfCompetitorsNotSufficientException.class, () -> {
            new Master(NameList.createListOfCompetitors(-1), new MockMatch(), new WinnerSelection());
        });
    }

    @Test
    public void competitorsIntoGroups_Should_GenerateGroups_and_Add_Them_To_GroupsList() {
        Assertions.assertTrue(this.master_WS.getGroups().isEmpty());
        this.master_WS.competitorsIntoGroups();
        Assertions.assertFalse(this.master_WS.getGroups().isEmpty());
    }

    @Test
    public void competitorsIntoGroups_Should_Generate_A_Positive_Number_Of_League_Less_Than_Number_Of_Competitors() {
        this.master_WS.competitorsIntoGroups();
        Assertions.assertTrue(
                /**
                 * MAX case : where we get a divisor == 1 then we create n league with 1
                 * competitor. Where n == master.getCompetitors().size()
                 */
                this.master_WS.getGroups().size() <= this.master_WS.getCompetitors().size() &&
                /**
                 * at least we have one league, because at least we have one competitor in the
                 * CompetitorsList of master
                 */
                        this.master_WS.getGroups().size() >= 1);
    }

    @Test
    public void competitorsIntoGroups_Should_Generate_N_Groups_Formula_Verify() {
        this.master_WS.competitorsIntoGroups();
        int divisor = FindDivisor.findNearestIntDivisor(this.master_WS.getCompetitors().size());
        int n = 0;
        if ((this.master_WS.getCompetitors().size() % divisor) != 0) {
            n = 1;
        }
        Assertions.assertEquals(this.master_WS.getGroups().size(),
                ((this.master_WS.getCompetitors().size() / divisor) + n));
    }

    @Test
    public void All_Competitors_Should_Be_in_A_Group_After_competitorsIntoGroups_Called() {
        this.master_WS.competitorsIntoGroups();
        List<List<Competitor>> lcomp = new ArrayList<List<Competitor>>();
        for (League l : this.master_WS.getGroups()) {
            lcomp.add(l.getCompetitors());
        }
        Assertions.assertEquals(this.master_WS.getCompetitors(), FlatList.mergeListsWithOutRemoveDuplicate(lcomp));
    }

    @Test
    public void selectFinalists_Should_Always_Return_a_List_With_Length_Power_Of_2() {
        this.master_WS.competitorsIntoGroups();
        this.master_WS_SBS.competitorsIntoGroups();
        /**
         * master.playPrimaryPhase() private visiblity And we will test the play()
         * method master at the end of this class
         */
        for (League l : FlatList.mergeListsWithOutRemoveDuplicate(
                List.of(this.master_WS.getGroups(), this.master_WS_SBS.getGroups()))) {
            l.play();
        }
        /**
         * Tournament constructor method check is the given list of Competitors have a
         * length power then what we search to know is when in play() Method of Master
         * we will call the playFinalPhases() method if the Tournament that we give to
         * the setFainlPhases() method with the finalists list given in param of
         * playFinalPhases() which will be the return of the selectFinalists() method in
         * the play() method. If this tournament constructor call throw
         * LenghtNotPowerOf2Exception then we can deduce that selectFinalists() return a
         * not power of 2 list of competitors. The follow assertions check if it's the
         * case
         */
        Assertions.assertDoesNotThrow(() -> {
            new Tournament(this.master_WS.selectFinalists(), new MockMatch());
            new Tournament(this.master_WS_SBS.selectFinalists(), new MockMatch());
        });/**
            * check RoundPowerOfTwoTest for a better understanding
            */

    }

    @Test
    public void Play_method_call_should_throw_nothing() {
        /**
         * we do no want to interrupt the master program if something happen like a
         * selection can be realize cause of length of competitor in groups Or if the
         * selected finalist with selection constraints give not a power of 2 of
         * competitors then selectFinalists return must be power of 2 and pass in param
         * of playFinalPhases method and then not throw... Note : Like global
         * verification of
         * selectFinalists_Should_Always_Return_a_List_With_Length_Power_Of_2()
         */
        Assertions.assertDoesNotThrow(() -> {
            this.master_WS.play();
        });
    }

    @Test
    public void play_method_call_should_call_CompetitorsIntoGroups_method() {
        Assertions.assertTrue(this.master_WS.getGroups().isEmpty());
        this.master_WS.play();
        Assertions.assertFalse(this.master_WS.getGroups().isEmpty());
    }

    @Test
    void sumscore_of_each_groups_must_be_equal_to_zero_if_play_method_not_called() {
        // we verify before sideeffect of play()
        for (League l : this.master_WS.getGroups()) {
            Assertions.assertTrue(this.sumScore(l) == 0);
        }
        // we verify sumscore after play call in the next unit test
    }

    @Test
    public void play_method_call_should_play_all_groups() {
        this.master_WS.play();
        /**
         * sumscore for each groups shoud be > 0 because we have 15 competitors in
         * this.master_WS then the divisor will be 5 and we will have 3 Groups. This
         * test can be verify for certains size of given competitors in the master
         * constructor because of the competitorsIntoGroups() method. But for
         * this.master_WS we are sure that we do not have a league with one competitor
         * (possible case where some groups (with only 1 competitor) can have sumscore
         * == 0 after play() call)
         */
        for (League l : this.master_WS.getGroups()) {
            Assertions.assertTrue(this.sumScore(l) > 0);
        }
    }

    @Test
    public void play_method_call_should_call_playFinalPhases() {
        this.master_WS.play();
        /**
         * sumScore for the tournament (finalphases) should be > 0 because for
         * this.master_WS we are sure that we
         */
        Assertions.assertTrue(this.sumScore(this.master_WS.getFinalPhases()) > 0);

    }
}
