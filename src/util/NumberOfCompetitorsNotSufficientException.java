package util;

/*@SuppressWarnings("serial")*/
public class NumberOfCompetitorsNotSufficientException extends Exception {

	public NumberOfCompetitorsNotSufficientException() {
		super("The number of competitors must be > 0\n");
	}

}
