package entity.transaction;

public class TransactionInfo {
	private String errorCode;

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getTransactionContent() {
		return transactionContent;
	}

	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	private Card card;
	private String transactionContent;
	private int amount;
	private String createdAt;
	
	public TransactionInfo(String errorCode, Card card, String transactionContent,
						   int amount, String createdAt) {
		this.errorCode = errorCode;
		this.card = card;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	public TransactionInfo(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
}
