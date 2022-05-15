
/**
 * LeagueMain Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

import competition.League;
import match.RandomMatch;
import person.NameList;
import util.LenghtNotPowerOf2Exception;
import java.util.Scanner;
import java.lang.Integer;

public class LeagueMain {
    public static void main(String[] args) throws LenghtNotPowerOf2Exception {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("\nEnter a number of competitors for the League (MAX = 200) :\n");
            String nbCompetitors = input.nextLine();
            input.close();
            League league = new League(NameList.createListOfCompetitors(Integer.parseInt(nbCompetitors)),
                    new RandomMatch());

            league.play();
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println(
                    "\nThe limit of participants in a league is 200 due to the number of different names available in the NameList class.\nPlease choose a number less than or equal to 200\n");
        }
    }
}