package entity.invoice;

import entity.order.Order;

public class Invoice {
	private Order order;
	private int amount;
	private String contents;
	
	public Invoice(Order order) {
		this.order = order;
		if(order.getTotalUpToNow() == 15000) {
			this.amount = order.getDeposit();
			this.contents = "Pay deposit when rent bike "+this.order.getRentedBike().getBarcode();
		}
		else {
			// full amount = ?
			this.amount = order.getTotalUpToNow() - order.getDeposit();
			this.contents = "Pay Rent and Return Deposit";
		}
	}
}
