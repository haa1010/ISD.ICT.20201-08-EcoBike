package common.exception;
/**
 * This exception is thrown when the credit card doesnt 
 * have enough money to process the transaction
 * @author Tran Thi Hang
 * @version 1.0
 */
public class NotEnoughBalanceException extends PaymentException{

	/**
	 * Exception Construction
	 */
	public NotEnoughBalanceException() {
		super("ERROR: Not enough balance in card!");
	}

}
