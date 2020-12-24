package entity.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import entity.BaseEntity;
import entity.bike.Bike;
import entity.db.EcoBikeRental;

public class Order extends BaseEntity {
    protected int id;

    public int getId() {
        return this.id;
    }

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

    public void newOrderDB() throws SQLException {
        // setID also
        Statement stm = EcoBikeRental.getConnection().createStatement();
        String deposit = Integer.toString(this.deposit);
        String bikeID = Integer.toString(this.getRentedBike().getId());
        String start = this.start.toString();
        stm.execute("INSERT INTO EcoOrder(deposit, bikeID, startAt) VALUES (" + deposit + "," + bikeID + "," + "\'" + start + "\'" + ");");
        ResultSet res = stm.executeQuery("SELECT id from EcoOrder where endAt is NULL");

        int id = -1;
        //res.next();
        while (res.next())
            id = res.getInt("id");
        this.setId(id);
    }

    public void updateOrderDB() throws SQLException {

        Statement stm = EcoBikeRental.getConnection().createStatement();
        String end = this.end.toString();
        stm.executeUpdate(" update " + "EcoOrder" + " set" + " "
                + "endAt" + "=" + "\'" + end + "\' "
                + "where id =" + this.getId() + ";");
    }

    public void setId(int id) {
        this.id = id;
    }

}

