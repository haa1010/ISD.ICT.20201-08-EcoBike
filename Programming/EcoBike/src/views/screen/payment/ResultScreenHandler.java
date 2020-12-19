package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.BaseController;
import controller.ReturnBikeController;
import entity.transaction.TransactionInfo;
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
	@FXML
	private Label owner;

	@FXML
	private Label content;

	@FXML
	private Label amount;

//	@FXML
//	void confirmPayment(MouseEvent event) throws IOException {
//		homeScreenHandler.show();
//	}

	
	public ResultScreenHandler(Stage stage, String screenPath, BaseController bController, TransactionInfo trans) throws IOException {
		super(stage, screenPath);
		this.owner.setText(trans.getCard().getOwner());
		this.content.setText(trans.getTransactionContent());
		this.amount.setText(Utils.getCurrencyFormat(trans.getAmount()));
		setBController(bController);

	}

	public ReturnBikeController getBController() {
		return (ReturnBikeController) super.getBController();
	}


}
