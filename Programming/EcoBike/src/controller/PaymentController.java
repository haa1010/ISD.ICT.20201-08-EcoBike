package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import updateDB.RentBike;
import updateDB.ReturnBike;
import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.invoice.Invoice;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.stage.Stage;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;


/**
 * This class controls the flow of the payment process in our EcoBikeRental project
 *
 * @author Tran Thi Hang, Duong Thi Hue,Pham Nhat Linh
 * @version 1.0
 * <p>
 * created_at: 01/12/2020
 * <p>
 * project_name: EcoBike Rental (EBR)
 * <p>
 * teacher_name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class_name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: Teaching Assistants and other team members
 */

public class PaymentController extends TransactionController {


    public boolean checkInvoice(Invoice invoice, String content) {
        return invoice.getContents().contains(content);
    }

    /**
     * base on transaction result , process rent or return bike
     *
     * @param transactionResult
     * @param invoice
     * @param card
     * @param stage
     * @param homeScreenHandler
     * @param prev
     * @throws IOException
     * @throws SQLException
     */
    public void proceedTransactionResult(TransactionInfo transactionResult, Invoice invoice, Card card, Stage stage, HomeScreenHandler homeScreenHandler, BaseScreenHandler prev) throws IOException, SQLException {
        if (!transactionResult.getErrorCode().equals("00")) {
            displayTransactionError(transactionResult.getErrorCode(), stage, homeScreenHandler, prev);
        } else {
            if (invoice.getContents().contains("deposit")) {
                moveToTransactionResult(invoice, transactionResult, card, stage, new RentBike());
            } else {
                moveToTransactionResult(invoice, transactionResult, card, stage, new ReturnBike());
            }
        }
    }

    /**
     * process pay order request
     *
     * @param cardNumber
     * @param holderName
     * @param securityCode
     * @param expirationDate
     * @param invoice
     * @param stage
     * @param homeScreenHandler
     * @param prev
     * @throws Exception
     */
    public void processPayRequest(String cardNumber, String holderName, String securityCode, String expirationDate, Invoice invoice, Stage stage, HomeScreenHandler homeScreenHandler, BaseScreenHandler prev) throws Exception {

        validateCard(cardNumber, holderName, securityCode, expirationDate);
        Card card = createCard(cardNumber, holderName, securityCode, expirationDate);
        TransactionInfo transactionResult = submitToPay(invoice, card);
        proceedTransactionResult(transactionResult, invoice, card, stage, homeScreenHandler, prev);

    }

}