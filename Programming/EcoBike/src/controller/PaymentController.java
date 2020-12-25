package controller;

import java.io.IOException;
import java.sql.SQLException;

import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.stage.Stage;
import subsystem.InterbankInterface;
import subsystem.interbank.InterbankSubsystemController;
import utils.Configs;
import views.screen.payment.ResultScreenHandler;


/**
 * This class controls the flow of the payment process in our EcoBikeRental project
 *
 * @author Tran Thi Hang
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

    /**
     * Represent the card used for payment
     */
    private Card card;

    /**
     * Represent the Interbank subsystem
     */
    private InterbankInterface interbank;
    private double amount;
    private String content;


    public TransactionInfo submitToPay(Invoice invoice, Card card) {
        InterbankSubsystemController interbank = new InterbankSubsystemController();
        TransactionInfo transactionResult = null;

        // payment controller
        if (invoice.getContents().contains("Refund")) {
            transactionResult = interbank.refund(card, Math.abs(invoice.getAmount()), invoice.getContents());
        } else
            transactionResult = interbank.payOrder(card, invoice.getAmount(), invoice.getContents());
        return transactionResult;
    }

    public Card createCard(String cardCode, String owner, String cvvCode, String dateExpired){
        return new Card(cardCode, owner, cvvCode, dateExpired);
    }

}