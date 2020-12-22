package views.screen.payment;

import java.io.IOException;
import java.sql.SQLException;

import controller.ResultScreenController;
import entity.BaseEntity;
import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.station.Station;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import subsystem.interbank.InterbankSubsystemController;
import utils.Configs;
import views.screen.BaseScreenHandler;


public class PaymentScreenHandler extends BaseScreenHandler {

    @FXML
    private Label pageTitle;

    @FXML
    private TextField cardCode;

    @FXML
    private TextField owner;

    @FXML
    private TextField dateExpired;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;

    @FXML
    private PasswordField cvvCode;

    @FXML
    private ImageView home;

    private Invoice invoice;

    private Card card;

    /**
     * This constructor use when pay for renting bike
     *
     * @author hangtt
     */
    public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        this.card = null;

        home.setOnMouseClicked(event -> {
            try {
                backToHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        submitBtn.setOnMouseClicked(event -> {
            try {
                this.submitToPay();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }


    /**
     * This constructor use when pay for returning bike
     *
     * @author hangtt
     */
    public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice, Card card) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        this.card = card;
        this.cardCode.setText(card.getCardCode());
        this.owner.setText(card.getOwner());
        this.dateExpired.setText(card.getDateExpired());

        home.setOnMouseClicked(event -> {
            try {
                backToHomeAfterRent(this.invoice.getOrder());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        submitBtn.setOnMouseClicked(event -> {
            try {
                this.submitToPay();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }


    void submitToPay() throws IOException, SQLException {
        this.card = new Card(this.cardCode.getText(), this.owner.getText(), this.cvvCode.getText(), this.dateExpired.getText());

        InterbankSubsystemController interbank = new InterbankSubsystemController();
        TransactionInfo transactionResult = null;

        if (this.invoice.getContents().contains("Refund")) {
            transactionResult = interbank.refund(this.card, Math.abs(this.invoice.getAmount()), this.invoice.getContents());
        } else
            transactionResult = interbank.payOrder(this.card, this.invoice.getAmount(), this.invoice.getContents());

        if (!transactionResult.getErrorCode().equals("00")) {
            displayTransactionError(transactionResult.getErrorCode(), this.invoice.getOrder(), this.invoice.getAmount(), this.invoice.getContents());
        } else {
            ResultScreenHandler resultScreenHandler = null;

            // if invoice is used for deposit -> start renting, else return successful
            if (this.invoice.getContents().contains("deposit")) {
                BaseEntity.updateDB(1, invoice.getOrder().getRentedBike());
                this.invoice.getOrder().getRentedBike().setRenting(false);
                resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult, this.invoice.getOrder());
            } else {
                BaseEntity.updateDB(0, invoice.getOrder().getRentedBike());
                resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult);
            }
            resultScreenHandler.show();
        }
    }

    @FXML
    void backToPreviousScreen(MouseEvent event) {
        this.getPreviousScreen().show();
    }


}