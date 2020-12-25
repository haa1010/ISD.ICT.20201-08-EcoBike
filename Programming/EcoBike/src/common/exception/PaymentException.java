package common.exception;;
/**
 * This exception is thrown when there is error related to payment process
 *
 * @author Tran Thi Hang
 * @version 1.0
 */
public class PaymentException extends RuntimeException {
	/**
	 * Exception Construction
	 * @param String message
	 */
	public PaymentException(String message) {
		super(message);
	}
}
