/*
 * @author linh
 */

package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import controller.PaymentController;
import common.exception.PlaceOrderException;
import entity.invoice.Invoice;
import entity.transaction.Card;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.rentbike.RentBikeScreenHandler;

import java.io.IOException;

//import entity.invoice.Invoice;

public class PaymentScreenHandler extends BaseScreenHandler {

    private Invoice invoice;

    private Card card;

    public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        this.card = null;
    }

    public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice, Card card) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        this.card = card;
        this.cardNumber.setText(card.getCardCode());
        this.holderName.setText(card.getOwner());
        this.expirationDate.setText(card.getDateExpired());
        this.securityCode.setText(card.getCvvCode());
    }

    @FXML
    private Label pageTitle;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField holderName;

    @FXML
    private TextField expirationDate;

    @FXML
    private PasswordField securityCode;


    /*
     * this is for confirm button when return bike
     */

//	void confirmToPayOrder() throws IOException{
//		String contents = "pay order";
//		PaymentController ctrl = (PaymentController) getBController();
//		Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, cardNumber.getText(), holderName.getText(),
//				expirationDate.getText(), securityCode.getText(), bankName.getText());
//
//		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE") );
//		resultScreen.setPreviousScreen(this);
//		resultScreen.setHomeScreenHandler(homeScreenHandler);
//		resultScreen.setScreenTitle("Result Screen");
//		resultScreen.show();
//	}
//
//	public PaymentScreenHandler(Stage stage, String paymentScreenPath, Invoice invoice) {
//		super();
//	}
//
//	void confirmToPayOrder() throws IOException{
//		String contents = "pay order";
//		PaymentController ctrl = (PaymentController) getBController();
//		Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, cardNumber.getText(), holderName.getText(),
//				expirationDate.getText(), securityCode.getText());
//
//		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE") );
//		resultScreen.setPreviousScreen(this);
//		resultScreen.setHomeScreenHandler(homeScreenHandler);
//		resultScreen.setScreenTitle("Result Screen");
//		resultScreen.show();
//	}

    /*
     * this is for pay deposit
     */

    @FXML
    void confirmTransaction(MouseEvent event) throws IOException {
    	String contents;
    	PaymentController ctrl = (PaymentController) getBController();
        if (this.card == null) {
            contents = "rent";
        }
        else {
        	contents = "return";
        }
            try {
                ctrl.validateCardInfo(cardNumber.getText(), holderName.getText(),
                        expirationDate.getText(), securityCode.getText());
            } catch (Exception e) {
                notifyError(e.getMessage());
            }
        
            Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, cardNumber.getText(), holderName.getText(),
                    expirationDate.getText(), securityCode.getText());
            
            if(response.get("RESULT") == "PAYMENT SUCCESSFUL!"){
	            BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE"), holderName.getText(), invoice.getContents(), invoice.getAmount());
	            resultScreen.setPreviousScreen(this);
	            resultScreen.setHomeScreenHandler(homeScreenHandler);
	            resultScreen.setScreenTitle("Result Screen");
	            resultScreen.show();
            }
            else {
            	BaseScreenHandler error = new TransactionErrorScreenHandler(this.stage, Configs.TRANSACTION_ERROR_SCREEN_PATH, response.get("MESSAGE"), this.invoice);
            	error.setPreviousScreen(this);
            	error.setHomeScreenHandler(homeScreenHandler);
            	error.show();
            }
        }

    /*
     * Back to rent/return bike screen
     */
    @FXML
    void backToPreviousScreen(MouseEvent event) {
    	this.getPreviousScreen().show();
    }

    @FXML
    private Label errorDisplay;

    /*
     * display error
     */
    void notifyError(String error) {
        errorDisplay.setText(error);
    }

}