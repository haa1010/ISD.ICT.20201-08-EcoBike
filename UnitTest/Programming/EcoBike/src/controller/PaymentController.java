package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import common.exception.InvalidCardException;
import common.exception.NotEnoughBalanceException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;


/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our EcoBikeRental Software.
 * 
 * @author hangtt
 *
 */
public class PaymentController extends BaseController {

	/**
	 * Represent the card used for payment
	 */
	private Card card;

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;
	private double amount;
	private String content;

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link java.lang.String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link java.lang.String String} represents the input date
	 * @return {@link java.lang.String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
	private String getExpirationDate(String date) throws InvalidCardException {
		String[] strs = date.split("/");
		if (strs.length != 2) {
			throw new InvalidCardException();
		}

		String expirationDate = null;
		int month = -1;
		int year = -1;

		try {
			month = Integer.parseInt(strs[0]);
			year = Integer.parseInt(strs[1]);
			if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
				throw new InvalidCardException();
			}
			expirationDate = strs[0] + strs[1];

		} catch (Exception ex) {
			throw new InvalidCardException();
		}

		return expirationDate;
	}

	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @param bankName	     - the interbank name of card
	 * @return {@link java.util.Map Map} represent the payment result with a
	 *         message.
	 */
	public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode, String bankName) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
//		try {
//			this.card = new Card(cardNumber, cardHolderName, Integer.parseInt(securityCode),
//					getExpirationDate(expirationDate));
//
//			this.interbank = new InterbankSubsystem();
//			TransactionInfo transaction = interbank.payOrder(card, amount, contents);
//
//			result.put("RESULT", "PAYMENT SUCCESSFUL!");
//			result.put("MESSAGE", "You have succesffully paid the order!");
//		} catch (PaymentException | UnrecognizedException ex) {
//			result.put("MESSAGE", ex.getMessage());
//		}
		return result;
	}


	public boolean validateName(String name) {
		try {
			return ((!name.equals("")) && (name.matches("^[ A-Za-z]+$")));
		} catch (NullPointerException e) {
			return false;
		}
	}

	public boolean validateNumberField(String number) {

		try {
			Integer.parseInt(number);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
		return true;
	}

	public boolean validateExpirationDate(String time) {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateTime = LocalDate.parse(time, formatter);
			LocalDate now = LocalDate.now();
			boolean isAfter = dateTime.isAfter(now);
			return isAfter;
		} catch (DateTimeParseException | NullPointerException e) {
			return false;
		}
	}

	public void getBalance() {}

	public boolean validateBalance(Card card, double money) {
		try {
			if (card.getBalance() >= money) return true;
			return false;
		} catch (NotEnoughBalanceException e) {
			return false;
		}
	}
}