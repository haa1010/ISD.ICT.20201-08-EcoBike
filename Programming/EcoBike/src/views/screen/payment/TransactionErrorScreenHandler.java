package views.screen.payment;

import java.io.IOException;
import controller.BaseController;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;


public class TransactionErrorScreenHandler extends BaseScreenHandler {

    @FXML
    Label message;

    public TransactionErrorScreenHandler(Stage stage, String screenPath, String errorMessage) throws IOException {
        super(stage, screenPath);
        message.setText(errorMessage);
    }

    public BaseController getBController() {
        return super.getBController();
    }

    @FXML
    void moveToPaymentScreen(MouseEvent event) throws IOException {
        this.getPreviousScreen().show();
    }
}
