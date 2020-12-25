package common.exception;;
/**
 * this exception is thrown when there is error but the cause is unknown
 * @author Tran Thi Hang
 * @version 1.0
 */
public class UnrecognizedException extends RuntimeException {
	/**
	  * Exception Construction
	  */
	public UnrecognizedException() {
		super("ERROR: Something went wrong!");
	}
}
