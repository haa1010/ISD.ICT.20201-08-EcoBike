package views.screen.payment;

import java.io.IOException;
import controller.BaseController;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

/**
 * This class is to display the transaction error screen
 * @author Tran Thi Hang
 * @version 1.0
 */

public class TransactionErrorScreenHandler extends BaseScreenHandler {

    @FXML
    Label message;
    
    /**
     * constructor
     * @param stage
     * @param screenPath
     * @param errorMessage
     * @throws IOException
     */
    public TransactionErrorScreenHandler(Stage stage, String screenPath, String errorMessage) throws IOException {
        super(stage, screenPath);
        message.setText(errorMessage);
    }
    
    /**
     * get the controller of this screen
     */
    public BaseController getBController() {
        return super.getBController();
    }

    @FXML
    /**
     * move back to payment screen
     * @param event
     * @throws IOException
     */
    void moveToPaymentScreen(MouseEvent event) throws IOException {
        this.getPreviousScreen().show();
    }
}
