/**
 * NameList abstract class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package person;

import java.util.List;
import java.util.ArrayList;

public abstract class NameList {

    /**
     * the final static list that contains 200 diffent String names
     */
    private final static List<String> names = List.of("James", "Mary", "Robert", "Patricia", "John", "Jennifer",
            "Michael", "Linda", "William", "Elizabeth", "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica",
            "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Lisa", "Matthew", "Betty",
            "Anthony", "Margaret", "Mark", "Sandra", "Donald", "Ashley", "Steven", "Kimberly", "Paul", "Emily",
            "Andrew", "Donna", "Joshua", "Michelle", "Kenneth", "Dorothy", "Kevin", "Carol", "Brian", "Amanda",
            "George", "Melissa", "Edward", "Deborah", "Ronald", "Stephanie", "Timothy", "Rebecca", "Jason", "Sharon",
            "Jeffrey", "Laura", "Ryan", "Cynthia", "Jacob", "Kathleen", "Gary", "Amy", "Nicholas", "Shirley", "Eric",
            "Angela", "Jonathan", "Helen", "Stephen", "Anna", "Larry", "Brenda", "Justin", "Pamela", "Scott", "Nicole",
            "Brandon", "Emma", "Benjamin", "Samantha", "Samuel", "Katherine", "Gregory", "Christine", "Frank", "Debra",
            "Alexander", "Rachel", "Raymond", "Catherine", "Patrick", "Carolyn", "Jack", "Janet", "Dennis", "Ruth",
            "Jerry", "Maria", "Tyler", "Heather", "Aaron", "Diane", "Jose", "Virginia", "Adam", "Julie", "Henry",
            "Joyce", "Nathan", "Victoria", "Douglas", "Olivia", "Zachary", "Kelly", "Peter", "Christina", "Kyle",
            "Lauren", "Walter", "Joan", "Ethan", "Evelyn", "Jeremy", "Judith", "Harold", "Megan", "Keith", "Cheryl",
            "Christian", "Andrea", "Roger", "Hannah", "Noah", "Martha", "Gerald", "Jacqueline", "Carl", "Frances",
            "Terry", "Gloria", "Sean", "Ann", "Austin", "Teresa", "Arthur", "Kathryn", "Lawrence", "Sara", "Jesse",
            "Janice", "Dylan", "Jean", "Bryan", "Alice", "Joe", "Madison", "Jordan", "Doris", "Billy", "Abigail",
            "Bruce", "Julia", "Albert", "Judy", "Willie", "Grace", "Gabriel", "Denise", "Logan", "Amber", "Alan",
            "Marilyn", "Juan", "Beverly", "Wayne", "Danielle", "Roy", "Theresa", "Ralph", "Sophia", "Randy", "Marie",
            "Eugene", "Diana", "Vincent", "Brittany", "Russell", "Natalie", "Elijah", "Isabella", "Louis", "Charlotte",
            "Bobby", "Rose", "Philip", "Alexis", "Johnny", "Kayla");

    /**
     * return the final static names list
     * 
     * @return names : List<String>
     */
    public static List<String> getNameList() {
        return NameList.names;
    }

    /**
     * return the name at the given index position in the names list
     * 
     * @param index
     * @return the name at index position : String
     */
    public static String getAName(int index) {
        return NameList.getNameList().get(index);
    }

    /**
     * create a n list of Competitors with the first n String names of the names
     * list
     * 
     * @param n : the number of competitors that we want
     * @return a list of n diffents Competitors : List<Competitor>
     */
    public static List<Competitor> createListOfCompetitors(int n) {
        List<Competitor> competitors = new ArrayList<Competitor>();
        for (int i = 0; i < n; i++) {
            competitors.add(new Competitor(NameList.getAName(i)));
        }
        return competitors;
    }
}
