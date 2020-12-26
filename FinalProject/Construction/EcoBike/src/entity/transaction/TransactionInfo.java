package entity.transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.db.EcoBikeRental;

/**
 * This class is the transaction info entity
 * @author Pham Nhat Linh
 * @version 1.0
 */

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
    
    /**
     * Constructor
     */
    public TransactionInfo() {
    }
    
    /**
     * Constructor
     * @param errorCode
     * @param card
     * @param transactionContent
     * @param amount
     * @param createdAt
     */
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
    
    /**
     * add new transaction info to database
     * also add the card used for this transaction to database
     * @param ivID invoiceID
     * @param card
     * @throws SQLException
     */
    public void newTransactionDB(int ivID, Card card) throws SQLException {
        // new card also
        Statement stm = EcoBikeRental.getConnection().createStatement();
        String invoiceID = Integer.toString(ivID);
        String cardID = Integer.toString(this.card.newCardDB(card));
        String createdDate = "\'" + this.createdAt + "\'";
        String content = "\'" + this.transactionContent + "\'";
        String amount = Integer.toString(this.amount);
        stm.execute("INSERT INTO TransactionInfo(cardID, invoiceID, createdDate, content, amount) "
                + "VALUES (" + cardID + "," + invoiceID + "," + createdDate + "," + content + "," + amount + ");");
        int id = -1;
        ResultSet res = stm.executeQuery("SELECT id from TransactionInfo");
        while (res.next()) {
            id = res.getInt("id");
        }
        this.setId(id);
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
