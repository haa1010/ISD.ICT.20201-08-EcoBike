package entity.order;

import java.sql.SQLException;
import java.sql.Timestamp;

import entity.bike.Bike;

public class Order{
	protected Bike rentedBike;
	protected Timestamp start;
	protected Timestamp end;
	protected double deposit;
	protected double totalUpToNow;
	void calculateTotalUptoNow() {
		return;
	}
	public Bike getRentedBike() {
		return rentedBike;
	}
	public Timestamp getStart() {
		return start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public double getDeposit() {
		return deposit;
	}
	public double getTotalUpToNow() {
		return totalUpToNow;
	}
	public void setRentedBike(Bike rentedBike) {
		this.rentedBike = rentedBike;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public void setTotalUpToNow(double totalUpToNow) {
		this.totalUpToNow = totalUpToNow;
	}
	public Order(Bike rentedBike, Timestamp start, Timestamp end, double deposit, double totalUpToNow) {
		this.rentedBike = rentedBike;
		this.start = start;
		this.end = end;
		this.deposit = deposit;
		this.totalUpToNow = totalUpToNow;
	};
	
}

