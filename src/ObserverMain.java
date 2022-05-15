
/**
 * ObserverMain Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

import observer.*;
import competition.Master;
import match.RandomMatch;
import person.NameList;
import selection.*;
import util.NumberOfCompetitorsNotSufficientException;

import java.util.List;
import java.util.Scanner;
import java.lang.Integer;

public class ObserverMain {
    public static void main(String[] args) throws NumberFormatException, NumberOfCompetitorsNotSufficientException {
        try {
            Scanner input = new Scanner(System.in);

            String selectionString = "";
            List<String> l = List.of("W", "S", "WS");
            Selection selection = new WinnerSelection(); // just to initialize
            while (!l.contains(selectionString)) {
                System.out.println("\nActual avaible selection :\n" + "\n\t- W = WinnerSelection\n"
                        + "\t- S = SecondBestSelection\n" + "\t- WS = WinnerSelection + SecondBestSelection\n");

                System.out.println("\nEnter selection method for groups :\n");

                selectionString = input.nextLine();

                if (selectionString.equals("W")) {
                    selection = new WinnerSelection();
                } else if (selectionString.equals("S")) {
                    selection = new SecondBestSelection();
                } else if (selectionString.equals("WS")) {
                    selection = new CompoundSelection(List.of(new WinnerSelection(), new SecondBestSelection()));
                }
            }

            String nbCompetitors = "0";
            while (nbCompetitors.equals("0")) {
                System.out.println("\nEnter a number of competitors for the Master (MIN = 1, MAX = 200) :\n");
                nbCompetitors = input.nextLine();
            }
            input.close();

            CompetitionPublisher cp = new CompetitionPublisher();
            cp.addObserver(new Journalist("LeJournaliste"));
            cp.addObserver(new Bookmaker("LeBookmaker"));
            Master master = new Master(NameList.createListOfCompetitors(Integer.parseInt(nbCompetitors)),
                    new RandomMatch(), selection, cp);

            master.play();

        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println(
                    "\nThe limit of participants in a Master is 200 due to the number of different names available in the NameList class.\nPlease choose a number less than or equal to 200\n");
        }
    }
}