package exceptions;

/**
 * Exception thrown when a numerical value is outside the allowed range 
 * for a specific operation (e.g., factorial range [0, 10] or characteristics >= 1).
 * * @author Andrea Glisic
 * @version 1.1
 * @since 2026-03-13
 */
public class NumberNotInAreaException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new NumberNotInAreaException with the specified detail message.
     * * @param message The detail message explaining the range violation.
     */
    public NumberNotInAreaException(String message) {
        super(message);
    }
}