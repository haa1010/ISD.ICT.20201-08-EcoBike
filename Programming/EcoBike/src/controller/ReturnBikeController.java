package controller;

import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.station.Station;
import entity.transaction.Card;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;


public class ReturnBikeController extends TransactionController {

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


    public int calculateAmount(double coefficient, LocalDateTime start) {
        Duration dur = Duration.between(start, LocalDateTime.now());
        long minutes = dur.toMinutes();
        if (minutes <= 10) return 0;
        if (minutes <= 30) return (int) (10000 * coefficient);
        return (int) (10000 + (Math.ceil((minutes - 30) / 15) * 3000) * coefficient);
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

    /**
     * get bike deposit
     *
     * @param bike
     * @return
     */

    public int getBikeDeposit(Bike bike) {
        return (int) (bike.getValue() * 0.4);
    }

    /**
     * create card
     *
     * @param cardCode
     * @param owner
     * @param cvvCode
     * @param dateExpired
     * @return
     */
    public Card createCard(String cardCode, String owner, String cvvCode, String dateExpired) {
        return new Card(cardCode, owner, cvvCode, dateExpired);
    }

    /**
     *
     */
    public void setCvvCode(String cvv, Card card) {
        card.setCvvCode(cvv);
    }
}

