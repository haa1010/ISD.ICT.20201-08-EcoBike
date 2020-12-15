package entity.transaction;


import entity.db.EcoBikeRental;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create Card entity use for transaction
 *
 * @author hangtt
 */

public class Card {
    private String cvvCode;
    private String owner;
    private String cardCode;
    private String dateExpired;

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getName() {
        return owner;
    }

    public void setName(String name) {
        this.owner = name;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }


    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String expiration) {
        this.dateExpired = expiration;
    }


    public static Card getCardFromDB() {
        try {
            String sql = "SELECT * FROM Card";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                String name = res.getString("owner");
                String securityCode = res.getString("cvvCode");
                String pin = res.getString("cardCode");
                String expiration = res.getString("dateExpired");
                Card card = new Card(securityCode, name, pin, expiration);
                return card;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


	public Card(String cardCode, String owner, String cvvCode, String dateExpired) {
		this.cardCode = cardCode;
		this.owner = owner;
		this.cvvCode = cvvCode;
		this.dateExpired = dateExpired;
	}
}
