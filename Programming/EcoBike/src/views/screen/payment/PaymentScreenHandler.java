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
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.IOException;

//import entity.invoice.Invoice;

public class PaymentScreenHandler extends BaseScreenHandler implements Initializable {

	private Invoice invoice;

	public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath);
		this.invoice = invoice;
	}

	@FXML
    private ComboBox<String> month;

    @FXML
    private ComboBox<String> year;
    
	@FXML
	private Label pageTitle;

	@FXML
	private TextField cardNumber;

	@FXML
	private TextField holderName;

	@FXML
	private TextField expirationDate;

	@FXML
	private TextField securityCode;

	@FXML
	private TextField bankName;

	/*
	 * this is for pay order button when return bike
	 */
	void confirmToPayOrder() throws IOException{
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
	}
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
    void confirmToPayDeposit(MouseEvent event) {
    	
    }
    
    /*
     * Back to rent bike screen 
     */
    @FXML
    void backToPreviousScreen(MouseEvent event) {
    		
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
		holderName.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                content.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
		this.month.getItems().addAll(Configs.MONTH);
		this.year.getItems().addAll(Configs.YEARS);
    }
}