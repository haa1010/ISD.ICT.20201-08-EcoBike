package entity.invoice;

import entity.order.Order;

public class Invoice {
    private Order order;
    private int amount;
    private String contents;

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
}
