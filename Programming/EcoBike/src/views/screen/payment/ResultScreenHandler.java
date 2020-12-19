package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.order.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;

// This is invoice screen handler

public class ResultScreenHandler extends BaseScreenHandler {
	
	private Order order;

	public ResultScreenHandler(Stage stage, String screenPath, String result, String message) throws IOException {
		super(stage, screenPath);
		resultLabel.setText(result);
		messageLabel.setText(message);
	}
	
	public ResultScreenHandler(Stage stage, String screenPath, String result, String message, String username, String contents, int amount) throws IOException {
		super(stage, screenPath);
		resultLabel.setText(result);
		messageLabel.setText(message);
		this.username.setText(username);
		this.transaction_detail.setText(contents);
		this.amount.setText(Utils.getCurrencyFormat(amount));
		this.order = null;
	}
	
	public ResultScreenHandler(Stage stage, String screenPath, String result, String message, String username, String contents, int amount, Order order) throws IOException {
		super(stage, screenPath);
		resultLabel.setText(result);
		messageLabel.setText(message);
		this.username.setText(username);
		this.transaction_detail.setText(contents);
		this.amount.setText(Utils.getCurrencyFormat(amount));
		this.order = order;
	}
	
    @FXML
    private Label username;

    @FXML
    private Label transaction_detail;

    @FXML
    private Label amount;

	@FXML
	private Label resultLabel;

	
	@FXML
	private Label messageLabel;

	@FXML
	void confirmPayment(MouseEvent event) throws IOException, SQLException {
		if(order == null) {
			this.backToHome();
		}
		else {
			this.backToHomeAfterRent(order);
		}
	}

}
