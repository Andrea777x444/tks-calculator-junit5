package exceptions;

/**
 * Exception thrown when the user requests a mathematical operation 
 * or action character that the calculator does not support.
 * * @author Andrea Glisic
 * @version 1.1
 * @since 2026-03-13
 */
public class NotSupportedOperationException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new NotSupportedOperationException with the specified detail message.
     * * @param message The detail message explaining which operation is not supported.
     */
    public NotSupportedOperationException(String message) {
        super(message);
    }
}