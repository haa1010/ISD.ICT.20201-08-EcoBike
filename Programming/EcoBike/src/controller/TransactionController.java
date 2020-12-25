package controller;

import UpdateDB.UpdateDBTransaction;
import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.stage.Stage;
import subsystem.interbank.InterbankSubsystemController;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.payment.ResultScreenHandler;
import views.screen.payment.TransactionErrorScreenHandler;

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
     * update db and move transaction result
     *
     * @param invoice
     * @param transactionResult
     * @param card
     * @param stage
     * @throws SQLException
     * @throws IOException
     */
    public void moveToTransactionResult(Invoice invoice, TransactionInfo transactionResult, Card card, Stage stage, UpdateDBTransaction update) throws SQLException, IOException {
        update.updateDB(invoice);
        newTransactionDB(invoice.getId(), card, transactionResult);
        update.moveToResultScreenHandler(stage, invoice, transactionResult);
    }


    /**
     * insert newTransaction to db
     *
     * @param idInvoice
     * @param card
     * @throws SQLException
     */
    public void newTransactionDB(int idInvoice, Card card, TransactionInfo transactionResult) throws SQLException {
        transactionResult.newTransactionDB(idInvoice, card);
    }

    /**
     * move to resultScreenHandler
     *
     * @param stage
     * @param invoice
     * @param transactionResult
     * @throws IOException
     */
    public void moveToResultScreenHandler(Stage stage, Invoice invoice, TransactionInfo transactionResult) throws IOException {

        ResultScreenHandler resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult, invoice.getOrder());
        resultScreenHandler.show();
    }

    /**
     * move to transaction error screen
     *
     * @param stage
     * @param homeScreenHandler
     * @param prev
     * @throws IOException
     */
    public void displayTransactionError(String errorCode, Stage stage, HomeScreenHandler homeScreenHandler, BaseScreenHandler prev) throws IOException {
        String errorMessage;
        errorMessage = Configs.errorCodes.get(errorCode);
        TransactionErrorScreenHandler tes = new TransactionErrorScreenHandler(stage, Configs.TRANSACTION_ERROR_SCREEN_PATH, errorMessage);
        tes.setPreviousScreen(prev);
        tes.setBController(new ReturnBikeController());
        tes.setHomeScreenHandler(homeScreenHandler);
        tes.setScreenTitle("Transaction Error Screen");
        tes.show();
    }

    /**
     * create invoice
     *
     * @param order
     * @param amount
     * @param contents
     * @return
     */
    public Invoice createInvoice(Order order, int amount, String contents) {
        return new Invoice(order, amount, contents);
    }

    /**
     * @param invoice
     * @param card
     * @return
     */

    public TransactionInfo submitToPay(Invoice invoice, Card card) {
        InterbankSubsystemController interbank = new InterbankSubsystemController();
        TransactionInfo transactionResult = null;

        // payment controller invoice.getContents().contains("ReturnBike")
        if (invoice.getAmount() < 0) {
            transactionResult = interbank.refund(card, Math.abs(invoice.getAmount()), invoice.getContents());
        } else
            transactionResult = interbank.payOrder(card, invoice.getAmount(), invoice.getContents());
        return transactionResult;
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

}
