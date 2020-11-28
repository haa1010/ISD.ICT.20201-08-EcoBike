package entity.transaction;


import java.time.LocalDate;

/**
 * Create Card entity use for transaction
 *
 * @author hangtt
 *
 */

public class Card {
	private int id;
	private String securityCode;
	private String name;
	private String pin;
	private String bankName;
	private LocalDate expiration;
	private double balance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance() {
//		 query with interbank to set balance
//		this.balance = balance;
	}


	public Card(int id, String securityCode, String name, String pin, String bankName, LocalDate expiration) {
		this.id = id;
		this.securityCode = securityCode;
		this.name = name;
		this.pin = pin;
		this.bankName = bankName;
		this.expiration = expiration;
	}
}
