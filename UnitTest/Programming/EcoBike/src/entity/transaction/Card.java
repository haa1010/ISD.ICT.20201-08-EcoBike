package entity.transaction;

public class Card {
	private String cardCode;
	private String owner;
	private int cvvCode;
	private String dateExpired;

	public Card(String cardCode, String owner, int cvvCode, String dateExpired) {
		super();
		this.cardCode = cardCode;
		this.owner = owner;
		this.cvvCode = cvvCode;
		this.dateExpired = dateExpired;
	}
}
