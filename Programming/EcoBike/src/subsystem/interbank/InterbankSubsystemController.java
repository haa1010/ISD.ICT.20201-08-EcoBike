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
	
	private String command = "pay";
	private static final String VERSION = "1.0.1";

	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	public TransactionInfo refund(Card card, int amount, String contents) {
		this.command = "refund";
		return this.deductMoney(card, amount, contents);
	}
	
	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	public TransactionInfo payOrder(Card card, int amount, String contents) {
		this.command = "pay";
		return this.deductMoney(card, amount, contents);
	}

	public TransactionInfo deductMoney(Card card, int amount, String contents) {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}
		transaction.put("command", command);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		Map<String, Object> dataForHash = new MyMap();
		dataForHash.put("secretKey", SECRET_KEY);
		dataForHash.put("transaction", transaction);
		String hashCode = Utils.md5(generateData(dataForHash));


		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
		requestMap.put("appCode", Configs.appCode);
		requestMap.put("hashCode", hashCode);

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
		TransactionInfo trans;
		if(response.get("transaction") != null) {
			MyMap transaction = (MyMap) response.get("transaction");
			Card card = new Card((String) transaction.get("cvvCode"),
					(String) transaction.get("owner"), (String) transaction.get("cardCode"), (String) transaction.get("dateExpired"));
			trans = new TransactionInfo((String) response.get("errorCode"), card, (String) transaction.get("transactionContent"),
					Integer.parseInt((String) transaction.get("amount")), (String) transaction.get("createdAt"));

			switch (trans.getErrorCode()) {
//		case "00":
//			break;
//		case "01":
//			throw new InvalidCardException();
//		case "02":
//			throw new NotEnoughBalanceException();
//		case "03":
//			throw new InternalServerErrorException();
//		case "04":
//			throw new SuspiciousTransactionException();
//		case "05":
//			throw new NotEnoughTransactionInfoException();
//		case "06":
//			throw new InvalidVersionException();
//		case "07":
//			throw new InvalidTransactionAmountException();
				case "00":
				case "01":
				case "02":
				case "03":
				case "04":
				case "05":
				case "06":
				case "07":
					break;
				default:
					throw new UnrecognizedException();
			}
		}
		else {
			trans = new TransactionInfo(response.get("errorCode").toString());
		}
		return trans;

	}
}
