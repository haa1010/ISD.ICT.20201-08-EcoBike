package controller;

import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.station.Station;
import entity.transaction.Card;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class ReturnBikeController extends BaseController {

    /**
     * get Card information for transaction
     *
     * @author hangtt
     */
    public Card getCard() {
        return Card.getCardFromDB();
    }


    /**
     * check station have empty dock point to return bike
     *
     * @param station
     * @author hue
     */
    public boolean checkStationReturnBike(Station station) {
        try {
            if (station.getNumEmptyDockPoint() > 0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * check if the bike has been renting (belong to no station)
     *
     * @param bike
     * @author linh
     */
    public boolean checkRentingBike(Bike bike) {
        try {
            if (bike.getStation() != null) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public int calculateAmount(double coefficient, LocalDateTime start) {
        Duration dur = Duration.between(start, LocalDateTime.now());
        long minutes = dur.toMinutes();
        if (minutes <= 10) return 0;
        if (minutes <= 30) return (int) (10000 * coefficient);

        return (int) (10000 + (Math.ceil((minutes - 30) / 15) * 3000) * coefficient);

    }

    /**
     * update order in db
     *
     * @param order
     * @throws SQLException
     */
    public void updateOrderDB(Order order) throws SQLException {
        order.setEnd(LocalDateTime.now());
        order.updateOrderDB();
    }

    /**
     * insert invoice to db
     *
     * @param invoice
     * @throws SQLException
     */
    public void insertInvoiceToDB(Invoice invoice) throws SQLException {
        invoice.newInvoiceDB();
    }

    /**
     * create invoice
     *
     * @param order
     * @param amount
     * @param content
     * @return
     */
    public Invoice createInvoice(Order order, int amount, String content) {
        return new Invoice(order, amount, content);
    }
}

