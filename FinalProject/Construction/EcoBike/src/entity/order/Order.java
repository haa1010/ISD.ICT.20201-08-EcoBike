package entity.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import entity.BaseEntity;
import entity.bike.Bike;
import entity.db.EcoBikeRental;

/**
 * This class is the order entity
 * @author Pham Nhat Linh
 * @version 1.0
 */

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
    
    /**
     * Constructor
     * use when start renting bike
     * @param rentedBike
     * @param start
     */
    public Order(Bike rentedBike, LocalDateTime start) {
        this.rentedBike = rentedBike;
        this.deposit = (int) (rentedBike.getValue() * 0.4);
        this.start = start;
        this.totalUpToNow = 15000;
    }
    
    /**
     * Constructor
     * use when returning bike
     * @param rentedBike
     * @param start
     * @param end
     * @param deposit
     * @param totalUpToNow
     */
    public Order(Bike rentedBike, LocalDateTime start, LocalDateTime end, int deposit, int totalUpToNow) {
        this.rentedBike = rentedBike;
        this.start = start;
        this.end = end;
        this.deposit = deposit;
        this.totalUpToNow = totalUpToNow;
    }
    
    /**
     * write new order to Database
     * create order when start using rented bike
     * @throws SQLException
     */
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
    
    /**
     * update order in database
     * use when returning bike successfully
     * @throws SQLException
     */
    public void updateOrderDB() throws SQLException {
        Statement stm = EcoBikeRental.getConnection().createStatement();
        String end = this.end.toString();
        String sql = " update " + "EcoOrder" + " set" + " "
                + "endAt" + "=" + "\'" + end + "\' "
                + ", amount =" + "\'" + totalUpToNow + "\' "
                + "where id =" + this.getId() + ";";
        stm.executeUpdate(sql);
    }

    public void setId(int id) {
        this.id = id;
    }

}

