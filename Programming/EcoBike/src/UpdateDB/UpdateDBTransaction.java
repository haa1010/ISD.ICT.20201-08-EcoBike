package UpdateDB;

import entity.invoice.Invoice;
import entity.transaction.TransactionInfo;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public interface UpdateDBTransaction {
    void updateDB(Invoice invoice) throws SQLException;

    void moveToResultScreenHandler(Stage stage, Invoice invoice, TransactionInfo transactionResult) throws IOException;
}
