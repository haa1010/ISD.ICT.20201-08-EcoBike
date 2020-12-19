package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.order.Order;
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


public class ResultScreenHandler extends BaseScreenHandler {

	@FXML
	private Label owner;

	@FXML
	private Label content;

	@FXML
	private Label amount;

	@FXML
	private Button backToHomeBtn;
	
	private Order order;

	/**
	 * This constructor use when returning bike sucessful
	 * @author hangtt
	 *
	 * @param stage
	 * @param screenPath
	 * @param bController
	 * @param trans
	 * @throws IOException
	 */
	public ResultScreenHandler(Stage stage, String screenPath, BaseController bController, TransactionInfo trans) throws IOException {
		super(stage, screenPath);
		this.owner.setText(trans.getCard().getOwner());
		this.content.setText(trans.getTransactionContent());
		this.amount.setText(Utils.getCurrencyFormat(trans.getAmount()));
		setBController(bController);

		backToHomeBtn.setOnMouseClicked(event -> {
			try {
				backToHome();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}


	/**
	 * This constructor use when rent bike sucessful
	 * @author hangtt
	 *
	 * @param stage
	 * @param screenPath
	 * @param bController
	 * @param trans
	 * @param order
	 * @throws IOException
	 */
	public ResultScreenHandler(Stage stage, String screenPath, BaseController bController, TransactionInfo trans, Order order) throws IOException {
		this(stage, screenPath, bController, trans);
		this.order = order;
		backToHomeBtn.setOnMouseClicked(event -> {
			try {
				backToHomeAfterRent(order);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ReturnBikeController getBController() {
		return (ReturnBikeController) super.getBController();
	}




}
