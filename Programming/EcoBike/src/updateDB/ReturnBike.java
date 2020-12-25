package updateDB;

import controller.ResultScreenController;
import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.transaction.TransactionInfo;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.payment.ResultScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReturnBike implements UpdateDBTransaction {
    @Override
    public void updateDB(Invoice invoice) throws SQLException {
        invoice.getOrder().setEnd(LocalDateTime.now());
        new Bike().updateQtyDB(0, invoice.getOrder().getRentedBike());
        invoice.getOrder().updateOrderDB();
        invoice.creatNewInvoiceDB();
        // set new station for bike
        new Bike().updateBikeDB(invoice.getOrder().getRentedBike().getId(),
                invoice.getOrder().getRentedBike().getStation().getId());
    }

    public void moveToResultScreenHandler(Stage stage, Invoice invoice, TransactionInfo transactionResult) throws IOException {

        ResultScreenHandler resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult);
        resultScreenHandler.show();
    }
}
