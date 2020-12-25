package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.stage.Stage;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
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

public class PaymentController extends BaseController {

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

    /**
     * Validate the input date which should be in the format "mm/yy", and then
     * return a {@link java.lang.String String} representing the date in the
     * required format "mmyy" .
     *
     * @param date - the {@link java.lang.String String} represents the input date
     * @return {@link java.lang.String String} - date representation of the required
     * format
     * @throws InvalidCardException - if the string does not represent a valid date
     *                              in the expected format
     */


    /**
     * Pay order, and then return the result with a message.
     *
     * @param amount         - the amount to pay
     * @param contents       - the transaction contents
     * @param cardNumber     - the card number
     * @param cardHolderName - the card holder name
     * @param expirationDate - the expiration date in the format "mm/yy"
     * @param securityCode   - the cvv/cvc code of the credit card
     * @return {@link java.util.Map Map} represent the payment result with a
     * message.
     */
    public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
                                        String expirationDate, String securityCode) {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            this.card = new Card(cardNumber, cardHolderName, securityCode,
                    expirationDate);

            this.interbank = new InterbankSubsystem();
            TransactionInfo transaction = interbank.payOrder(card, amount, contents);

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have succesffully paid the order!");
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
        }
        return result;
    }

    /**
     * This method validates Cardholder's name
     *
     * @param name
     * @return boolean
     */

    public boolean validateName(String name) {
        try {
            name = name.trim();
            return ((!name.equals("")) && (name.matches("^[ A-Za-z0-9]+$")));
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * This method validate Security Code/cvvCode
     *
     * @param number
     * @return boolean
     */
    public boolean validateNumberField(String number) {

        try {
            number = number.trim();
            Integer.parseInt(number);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    //
//    /**
//     * This method check if expiration date of Card Information is valid for making transaction
//     *
//     * @param time
//     * @return boolean
//     */
//    public boolean validateExpirationDate(String time) {
//        try {
//            time = time.trim();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate dateTime = LocalDate.parse(time, formatter);
//            LocalDate now = LocalDate.now();
//            boolean isAfter = dateTime.isAfter(now);
//            return isAfter;
//        } catch (DateTimeParseException | NullPointerException e) {
//            return false;
//        }
//    }
    public boolean validateExpirationDate(String date) throws InvalidCardException {
        try {
            date = date.trim();
            String regex = "^[0-9]{4}$";
            return date.matches(regex);
        } catch (Exception e) {
            throw new InvalidCardException("Invalid expiration date");
        }
    }
    /*
     * @linh
     * continue to write
     */

    /**
     * This method validate cardCode
     *
     * @param number
     * @return boolean
     */
    public boolean validateCardCode(String number) {
        try {
            number = number.trim();
            return ((!number.equals("")) && (number.matches("^[_0-9A-Za-z]*$")));
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public void validateCardInfo(String cardNumber, String holderName, String expirationDate, String securityCode) throws Exception {
        if (!validateExpirationDate(expirationDate)) throw new InvalidCardException("Invalid expirationDate");
        if (!this.validateName(holderName))
            throw new InvalidCardException("Invalid Owner Name");
        else if (!this.validateNumberField(securityCode))
            throw new InvalidCardException("Wrong format cvvCode");
        else if (!this.validateCardCode(cardNumber))
            throw new InvalidCardException("Wrong format code number");
    }

    public boolean checkInvoice(Invoice invoice, String content) {
        return invoice.getContents().contains(content);
    }

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

    public Card createCard(String cardCode, String owner, String cvvCode, String dateExpired) throws Exception {
        validateCardInfo(cardCode, owner, dateExpired, cvvCode);
        return new Card(cardCode, owner, cvvCode, dateExpired);
    }

    public void moveToSuccessfulTransactionScreen(Invoice invoice, TransactionInfo transactionResult, Card card, Stage stage) throws SQLException, IOException {
        ResultScreenHandler resultScreenHandler = null;
        new Bike().updateDB(0, invoice.getOrder().getRentedBike());
        invoice.newInvoiceDB();
        // update db bike table, station col
        new Bike().updateBikeDB(invoice.getOrder().getRentedBike().getId(),
                invoice.getOrder().getRentedBike().getStation().getId());

        transactionResult.newTransactionDB(invoice.getId(), card);
        resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult);
        resultScreenHandler.show();
    }

}