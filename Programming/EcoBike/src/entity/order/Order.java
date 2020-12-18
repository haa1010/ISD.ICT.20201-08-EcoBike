package entity.order;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import entity.bike.Bike;

public class Order {
    protected Bike rentedBike;
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected int deposit;
    protected int totalUpToNow;

    public int calculateTotalUptoNow() {
        int total = 0;

        return total;
    }

    public Bike getRentedBike() {
        return rentedBike;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public int getDeposit() {
        return deposit;
    }

    public int getTotalUpToNow() {
        return totalUpToNow;
    }

    public void setRentedBike(Bike rentedBike) {
        this.rentedBike = rentedBike;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public void setTotalUpToNow(int totalUpToNow) {
        this.totalUpToNow = totalUpToNow;
    }

    public Order(Bike rentedBike, LocalDateTime start) {
        this.rentedBike = rentedBike;
        this.deposit = (int) (rentedBike.getValue() * 0.4);
        this.start = start;
        this.totalUpToNow = 15000;
    }

    public Order(Bike rentedBike, LocalDateTime start, LocalDateTime end, int deposit, int totalUpToNow) {
        this.rentedBike = rentedBike;
        this.start = start;
        this.end = end;
        this.deposit = deposit;
        this.totalUpToNow = totalUpToNow;
    }

}

