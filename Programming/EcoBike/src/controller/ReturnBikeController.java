package controller;

import UpdateDB.ReturnBike;
import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.station.Station;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
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
     * get bike deposit
     *
     * @param bike
     * @return
     */

    public int getBikeDeposit(Bike bike) {
        return (int) (bike.getValue() * 0.4);
    }
    
    /**
     *
     */
    public void setCvvCode(String cvv, Card card) {
        card.setCvvCode(cvv);
    }

    public void processReturnBike(Card card, int totalAmount, Order order, String invoiceContents, Stage stage, HomeScreenHandler homeScreenHandler, BaseScreenHandler prev) throws IOException, SQLException {

        // pay more if rentingFee > deposit, else refund to account
        Invoice invoice = createInvoice(order, totalAmount, invoiceContents);
        TransactionInfo transactionResult = submitToPay(invoice, card);
        if (!transactionResult.getErrorCode().equals("00")) {
            displayTransactionError(transactionResult.getErrorCode(), stage, homeScreenHandler, prev);
        } else {
            moveToTransactionResult(invoice, transactionResult, card, stage, new ReturnBike());
        }
    }


}

