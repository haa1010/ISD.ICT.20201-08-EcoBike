package views.screen.payment;

import java.io.IOException;

import entity.order.Order;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class RentPaymentScreenHandler  extends BaseScreenHandler {

	private Order order;

	public RentPaymentScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.order = order;
		// TODO Auto-generated constructor stub
	}

}
