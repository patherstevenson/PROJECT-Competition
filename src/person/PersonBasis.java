/**
 * PersonBasis abstract class
 *
 * @author Pather Stevenson, Larafi Zakaria
 * */

package person;

public abstract class PersonBasis implements Person {

	private final String name;

	/**
	 * An abstract PersonBasis with the given name
	 *
	 * @param name the Person's name
	 *
	 */
	public PersonBasis(String name) {

		this.name = name;
	}

	/**
	 * returns the name of the Person
	 * 
	 * @return the name of the Person
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * return true if this person is equals to the given object otherwise false
	 * 
	 * @return True or False
	 */
	public boolean equals(Object o) {
		if (o instanceof PersonBasis) {
			PersonBasis other = (PersonBasis) o;
			return this.getName().equals(other.getName());
		}
		return false;

	}
}
