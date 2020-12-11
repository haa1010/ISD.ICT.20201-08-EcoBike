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
    private int id;
    private String securityCode;
    private String name;
    private String pin;
    private String bank;
    private String expiration;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance() {
//		 query with interbank to set balance
//		this.balance = balance;
    }


    public Card(int id, String securityCode, String name, String pin, String bank, String expiration) {
        this.id = id;
        this.securityCode = securityCode;
        this.name = name;
        this.pin = pin;
        this.bank = bank;
        this.expiration = expiration;
    }


    public static Card getCardFromDB() {
        try {
            String sql = "SELECT * FROM Card";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String bank = res.getString("bankName");
                String securityCode = res.getString("securityCode");
                String pin = res.getString("pin");
                String expiration = res.getString("expiration");
                Card card = new Card(id, securityCode, name, pin, bank, expiration);
                return card;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
