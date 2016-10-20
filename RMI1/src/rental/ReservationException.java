package rental;

import java.io.Serializable;

public class ReservationException extends Exception implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReservationException(String string) {
        super(string);
    }
}