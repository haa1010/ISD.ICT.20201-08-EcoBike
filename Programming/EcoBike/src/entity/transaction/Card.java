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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String name) {
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


    public int newCardDB(Card card) throws SQLException {
        int currentCardID = -1;
        // ID auto++ also
        // if card exist then return;
        // else add
        Statement stm = EcoBikeRental.getConnection().createStatement();
        String owner = "\'" + card.getOwner() + "\'";
        String cvvCode = "\'" + card.getCvvCode() + "\'";
        String cardCode = "\'" + card.getCardCode() + "\'";
        String dateExpired = "\'" + card.getDateExpired() + "\'";
        ResultSet res = stm.executeQuery("SELECT id from Card where owner = " + owner +
                " AND cvvCode =" + cvvCode + " AND cardCode =" + cardCode + " AND dateExpired = " + dateExpired);
        if (!res.isBeforeFirst()) {
            stm.execute("INSERT INTO Card(owner, cvvCode, cardCode, dateExpired) VALUES (" + owner + "," + cvvCode + "," + cardCode + "," + dateExpired + ");");

            res = stm.executeQuery("SELECT id from Card");
            while (res.next()) {
                currentCardID = res.getInt("id");
            }
        } else {// not a new card
            res.next();
            currentCardID = res.getInt("id");
        }
        return currentCardID;
    }


}
