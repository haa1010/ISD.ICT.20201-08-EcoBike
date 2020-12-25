package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;

/**
 * The {@code InterbankInterface} class is used to communicate with the
 * {@link subsystem.InterbankSubsystem InterbankSubsystem} to make transaction
 * 
 *
 * @author Tran Thi Hang
 */
public interface InterbankInterface {

	/**
	 * Pay order, and then return the payment transaction
	 * 
	 * @param card     - the credit card used for payment
	 * @param amount   - the amount to pay
	 * @param contents - the transaction contents
	 * @return {@link TransactionInfo PaymentTransaction} - if the
	 *         payment is successful
	 * @throws PaymentException      if responded with a pre-defined error code
	 * @throws UnrecognizedException if responded with an unknown error code or
	 *                               something goes wrong
	 */
	TransactionInfo payOrder(Card card, int amount, String contents)
			throws PaymentException, UnrecognizedException;

	/**
	 * Refund, and then return the payment transaction
	 * 
	 * @param card     - the credit card which would be refunded to
	 * @param amount   - the amount to refund
	 * @param contents - the transaction contents
	 * @return {@link TransactionInfo PaymentTransaction} - if the
	 *         payment is successful
	 * @throws PaymentException      if responded with a pre-defined error code
	 * @throws UnrecognizedException if responded with an unknown error code or
	 *                               something goes wrong
	 */
	TransactionInfo refund(Card card, int amount, String contents)
			throws PaymentException, UnrecognizedException;

}
