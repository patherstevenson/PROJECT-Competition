
/**
 * TournamentMain Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

import competition.Tournament;
import match.RandomMatch;
import person.NameList;
import util.LenghtNotPowerOf2Exception;
import java.util.Scanner;
import java.lang.Integer;

public class TournamentMain {
    public static void main(String[] args) throws LenghtNotPowerOf2Exception {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("\nEnter a number of competitors for the Tournament (MAX = 128) :\n");
            String nbCompetitors = input.nextLine();
            input.close();
            Tournament tournament = new Tournament(NameList.createListOfCompetitors(Integer.parseInt(nbCompetitors)),
                    new RandomMatch());

            tournament.play();
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println(
                    "\nThe limit of participants in a tournament is 128 due to the number of different names available in the NameList class and the power of 2 limitation.\nPlease choose a number less than or equal to 128\n");
        } catch (LenghtNotPowerOf2Exception err) {
            System.out.println(
                    "\nTournament must have a number of competitors that's a power of 2\nPlease choose a number which is power of 2\n");
        }
    }
}