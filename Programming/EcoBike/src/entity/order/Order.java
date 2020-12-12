package entity.order;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;

import entity.bike.Bike;

public class Order {
    protected Bike rentedBike;
    protected LocalTime start;
    protected LocalTime end;
    protected double deposit;
    protected double totalUpToNow;

    void calculateTotalUptoNow() {
        return;
    }

    public Bike getRentedBike() {
        return rentedBike;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
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

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public void setTotalUpToNow(double totalUpToNow) {
        this.totalUpToNow = totalUpToNow;
    }

    public Order(Bike rentedBike, LocalTime start) {
        this.rentedBike = rentedBike;
        this.deposit = rentedBike.getValue() * 0.4;
        this.start = start;
        this.totalUpToNow = 15000;
    }

    public Order(Bike rentedBike, LocalTime start, LocalTime end, double deposit, double totalUpToNow) {
        this.rentedBike = rentedBike;
        this.start = start;
        this.end = end;
        this.deposit = deposit;
        this.totalUpToNow = totalUpToNow;
    }

    ;

}

