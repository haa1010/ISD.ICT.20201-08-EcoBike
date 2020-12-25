package entity.invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.db.EcoBikeRental;
import entity.order.Order;

/**
 * This class is the invoice entity
 * @author Pham Nhat Linh
 * @version 1.0
 */
public class Invoice {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Order order;
    private int amount;
    private String contents;
    
    /**
     * Constructor
     * @param order
     * @param amount
     * @param contents
     */
    public Invoice(Order order, int amount, String contents) {
        this.order = order;
        this.amount = amount;
        this.contents = contents;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    
    /**
     * Create new Invoice in Database
     * @throws SQLException
     */
    public void creatNewInvoiceDB() throws SQLException {
        // setID also
        Statement stm = EcoBikeRental.getConnection().createStatement();
        String orderID = Integer.toString(this.getOrder().getId());
        String totalAmount = Integer.toString(this.getAmount());
        String content = "\'" + this.getContents() + "\'";

        stm.execute("INSERT INTO Invoice(content, totalAmount, orderID) VALUES (" + content + "," + totalAmount + "," + orderID + ");");

        int id = -1;
        ResultSet res = stm.executeQuery("SELECT id from Invoice");
        while (res.next()) {
            id = res.getInt("id");
        }
        this.setId(id);
    }
}
