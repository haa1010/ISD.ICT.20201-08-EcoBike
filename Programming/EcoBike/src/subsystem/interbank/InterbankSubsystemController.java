package subsystem.interbank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.InvalidTransactionAmountException;
import common.exception.InvalidVersionException;
import common.exception.NotEnoughBalanceException;
import common.exception.NotEnoughTransactionInfoException;
import common.exception.SuspiciousTransactionException;
import common.exception.UnrecognizedException;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import utils.Configs;
import utils.MyMap;
import utils.Utils;

public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "AQzdE8O/fR8=";
	private static final String SECRET_KEY = "BtNH8J4Tl/I=";
	
	// pay command can be pay or refund
	
	private static final String PAY_COMMAND = "pay";
	private static final String VERSION = "1.0.1";

	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	public TransactionInfo refund(Card card, int amount, String contents) {
		return null;
	}
	
	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	public TransactionInfo payOrder(Card card, int amount, String contents) {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}
		transaction.put("command", PAY_COMMAND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
		//requestMap.put("appCode", Configs.appCode);

		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
	}

	private TransactionInfo makePaymentTransaction(MyMap response) {
		if (response == null)
			return null;
		MyMap transaction = (MyMap) response.get("transaction");
		Card card = new Card(Integer.parseInt((String) transaction.get("id")), (String) transaction.get("securityCode"),
				(String) transaction.get("name"), (String) transaction.get("pin"), (String) transaction.get("bankName"),
				(String) transaction.get("expiration") );
		TransactionInfo trans = new TransactionInfo((String) response.get("errorCode"), card,
				(String) transaction.get("transactionId"), (String) transaction.get("transactionContent"),
				Integer.parseInt((String) transaction.get("amount")), (String) transaction.get("createdAt"));

		switch (trans.getErrorCode()) {
		case "00":
			break;
		case "01":
			throw new InvalidCardException();
		case "02":
			throw new NotEnoughBalanceException();
		case "03":
			throw new InternalServerErrorException();
		case "04":
			throw new SuspiciousTransactionException();
		case "05":
			throw new NotEnoughTransactionInfoException();
		case "06":
			throw new InvalidVersionException();
		case "07":
			throw new InvalidTransactionAmountException();
		default:
			throw new UnrecognizedException();
		}

		return trans;
	}

}
