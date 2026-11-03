package exceptions;

/**
 * Exception thrown when an attempt is made to divide a number by zero.
 * This ensures the calculator's arithmetic integrity.
 * * @author Andrea Glisic
 * @version 1.1
 * @since 2026-03-13
 */
public class DivisionByZeroException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new DivisionByZeroException with the specified detail message.
     * * @param message The detail message explaining the cause of the exception.
     */
    public DivisionByZeroException(String message) {
        super(message);
    }
}