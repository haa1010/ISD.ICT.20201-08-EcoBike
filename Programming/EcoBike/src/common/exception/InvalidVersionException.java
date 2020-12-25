package common.exception;;
/**
 * This exception is thrown when the transaction contains invalid version of information
 * @author hangntt
 * @version 1.0
 */
public class InvalidVersionException extends PaymentException{
	/**
	  * Exception Construction
	  */
	public InvalidVersionException() {
		super("ERROR: Invalid Version Information!");
	}
}
