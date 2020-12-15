package views.screen.payment;

import java.io.IOException;
import java.time.LocalTime;

import controller.BaseController;
import controller.PaymentController;
import controller.ReturnBikeController;
import controller.ViewBikeController;
import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;


public class TransactionErrorScreenHandler extends BaseScreenHandler{

    @FXML
    Label message;

    private Invoice invoice;


    public TransactionErrorScreenHandler(Stage stage, String screenPath, String errorMessage, Invoice invoice) throws IOException {
        super(stage, screenPath);
        message.setText(errorMessage);
        this.invoice = invoice;
    }

    public BaseController getBController() {
        return super.getBController();
    }

    @FXML
    void moveToPaymentScreen(MouseEvent event) throws IOException {
        BaseScreenHandler payment = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, invoice);
        payment.setBController(new PaymentController());
        payment.setPreviousScreen(this);
        payment.setScreenTitle("Payment Screen ");
        payment.show();
    }
}
