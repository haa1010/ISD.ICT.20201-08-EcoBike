package UpdateDB;


/**
 * This class is used when return bike successfully
 * @author Duong Thi Hue
 * @version 1.0
 */
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
    /**
	 * update invoice, order of the invoice, bike of the order, station of the bike to db
	 * @param invoice
	 * @throws SQLException
	 */
    public void updateDB(Invoice invoice) throws SQLException {
        invoice.getOrder().setEnd(LocalDateTime.now());
        new Bike().updateQtyDB(0, invoice.getOrder().getRentedBike());
        invoice.getOrder().updateOrderDB();
        invoice.creatNewInvoiceDB();
        // set new station for bike
        new Bike().updateBikeDB(invoice.getOrder().getRentedBike().getId(),
                invoice.getOrder().getRentedBike().getStation().getId());
    }

    
    /**
     * Move to result screen
     * @param stage
     * @param invoice
     * @param transactionResult
     * @throws IOException
     */
    public void moveToResultScreenHandler(Stage stage, Invoice invoice, TransactionInfo transactionResult) throws IOException {

        ResultScreenHandler resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult);
        resultScreenHandler.show();
    }
}
