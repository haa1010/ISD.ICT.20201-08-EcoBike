package views.screen.payment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.IOException;

//import entity.invoice.Invoice;

public class PaymentScreenHandler extends BaseScreenHandler {

	@FXML
	private Button btnConfirmPayment;

	@FXML
	private ImageView loadingImage;
//
//	private Invoice invoice;
//
//	public PaymentScreenHandler(Stage stage, String screenPath, int amount, String contents) throws IOException {
//		super(stage, screenPath);
//	}
//
//	public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
//		super(stage, screenPath);
//		this.invoice = invoice;
//
//		btnConfirmPayment.setOnMouseClicked(e -> {
//			try {
//				confirmToPayOrder();
//				((PaymentController) getBController()).emptyCart();
//			} catch (Exception exp) {
//				System.out.println(exp.getStackTrace());
//			}
//		});
//	}

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

	public PaymentScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
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

}