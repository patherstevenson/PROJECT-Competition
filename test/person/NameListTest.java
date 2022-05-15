/**
 * NameListTest Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import person.NameList;
import person.Competitor;

public class NameListTest {

    @Test
    public void testGetAName() {
        int index = new Random().nextInt(NameList.getNameList().size());
        String name = NameList.getAName(index);
        Assertions.assertEquals(name, NameList.getNameList().get(index));
    }

    @Test
    public void testAllNamesAreDifferent() {
        /**
         * there are 200 name strings in the list so if we transform this list into a
         * hashset then if all the names are different we must find a hashset size of
         * 200
         */
        Assertions.assertEquals(200, new HashSet<String>(NameList.getNameList()).size());
    }

    @Test
    public void testCreateListOfCompetitors() {
        List<Competitor> competitors = NameList.createListOfCompetitors(200);
        for (Competitor c : competitors) {
            Assertions.assertTrue(NameList.getNameList().contains(c.getName()));
        }
    }
}
