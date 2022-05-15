/**
 * PersonBasisTest abstract Class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import person.PersonBasis;

public abstract class PersonBasisTest {

    private PersonBasis person;
    private PersonBasis person2;

    /**
     * Creates a new Character 
     */

    protected abstract PersonBasis createPerson(String name);

    @BeforeEach
    public void init() {
        this.person = this.createPerson("Monteverdi");
	    this.person2 = this.createPerson("Pergolesi");
    }

    @Test
    public void should_Create_New_Character() {
        Assertions.assertNotNull(this.person);
    }

    @Test
    public void two_Characters_With_Different_Names_Should_Not_Be_Equals() {
        Assertions.assertFalse(this.person.equals(this.person2));
    }
}
