package common.exception;;
/**
 * The EcobikeException wraps all unchecked exceptions You can use this
 * exception to inform
 *
 * @author hangtt
 * @version 1.0
 */
public class InvalidTransactionAmountException extends PaymentException {
	public InvalidTransactionAmountException() {
		super("ERROR: Invalid Transaction Amount!");
	}
}
