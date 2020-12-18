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

    @FXML
    private TextField bankName;

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
    void confirmToPayDeposit(MouseEvent event) throws IOException {
        if (this.card == null) {
            String contents = "pay order";
            PaymentController ctrl = (PaymentController) getBController();
            try {
                ctrl.validateCardInfo(cardNumber.getText(), holderName.getText(),
                        expirationDate.getText(), securityCode.getText(), bankName.getText());
            } catch (Exception e) {
                notifyError(e.getMessage());
            }
            Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, cardNumber.getText(), holderName.getText(),
                    expirationDate.getText(), securityCode.getText(), bankName.getText());

            BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE"), holderName.getText(), invoice.getContents(), invoice.getAmount());
            resultScreen.setPreviousScreen(this);
            resultScreen.setHomeScreenHandler(homeScreenHandler);
            resultScreen.setScreenTitle("Result Screen");
            resultScreen.show();
        }
    }

    /*
     * Back to rent bike screen
     */
    @FXML
    void backToPreviousScreen(MouseEvent event) {

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