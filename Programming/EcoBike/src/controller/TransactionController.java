package controller;

import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.payment.ResultScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TransactionController extends BaseController {

    public boolean validateCard(Card card) {
        if (card.getCardCode().isEmpty() || card.getDateExpired().isEmpty() || card.getOwner().isEmpty() || card.getCvvCode().isEmpty()) {
            return false;
        }
        return true;
    }

    public void setAmountOrder(Order order, int amount) {
        order.setTotalUpToNow(amount);
    }

    public void setEndOrder(Order order) {
        order.setEnd(LocalDateTime.now());

    }

    /**
     * update database bike, invoice, order
     *
     * @param invoice
     * @throws SQLException
     */
    public void updateDB(Invoice invoice) throws SQLException {
        new Bike().updateQtyDB(0, invoice.getOrder().getRentedBike());
        invoice.getOrder().updateOrderDB();
        invoice.creatNewInvoiceDB();
        // set new station for bike
        new Bike().updateBikeDB(invoice.getOrder().getRentedBike().getId(),
                invoice.getOrder().getRentedBike().getStation().getId());

    }

    public void moveToSuccessfulTransactionScreen(Invoice invoice, TransactionInfo transactionResult, Card card, Stage stage) throws SQLException, IOException {
        ResultScreenHandler resultScreenHandler = null;
        setEndOrder(invoice.getOrder());
        updateDB(invoice);
        transactionResult.newTransactionDB(invoice.getId(), card);
        resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult);
        resultScreenHandler.show();
    }

    public void moveToSuccessfulDepositScreen(Invoice invoice, TransactionInfo transactionResult, Card card, Stage stage) throws SQLException, IOException {
        ResultScreenHandler resultScreenHandler = null;
        new Bike().updateQtyDB(1, invoice.getOrder().getRentedBike());
        invoice.getOrder().newOrderDB();
        invoice.creatNewInvoiceDB();

        transactionResult.newTransactionDB(invoice.getId(), card);
        resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult, invoice.getOrder());
        resultScreenHandler.show();
    }
}
