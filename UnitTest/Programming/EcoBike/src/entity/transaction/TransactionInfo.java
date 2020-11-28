package entity.transaction;

public class TransactionInfo {
	private String errorCode;
	private Card card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
	
	public TransactionInfo(String errorCode, Card card, String transactionId, String transactionContent,
						   int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
