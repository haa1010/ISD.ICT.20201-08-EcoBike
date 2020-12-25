package common.exception;;
/**
 * This exception is thrown when there is error related to internal database server
 * @version 1.0
 */
public class InternalServerErrorException extends PaymentException {
	/**
	  * Exception Construction
	  */

	public InternalServerErrorException() {
		super("ERROR: Internal Server Error!");
	}

}
