package subsystem;

import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author Tran Thi Hang
 *
 */
public class InterbankSubsystem implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	private InterbankSubsystemController ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		this.ctrl = new InterbankSubsystemController();
	}

	/**
	 * @see InterbankInterface#payOrder(Card, int,
	 *      java.lang.String)
	 */
	public TransactionInfo payOrder(Card card, int amount, String contents) {
		TransactionInfo transaction = ctrl.payOrder(card, amount, contents);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(Card, int,
	 *      java.lang.String)
	 */
	public TransactionInfo refund(Card card, int amount, String contents) {
		TransactionInfo transaction = ctrl.refund(card, amount, contents);
		return transaction;
	}
}
