package views.screen.payment;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import controller.PaymentController;
import controller.ResultScreenController;
import controller.ViewBikeController;
import entity.BaseEntity;
import entity.bike.Bike;
import entity.db.EcoBikeRental;
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
import views.screen.home.HomeScreenHandler;


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
    public PaymentScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);

        this.card = null;

        home.setOnMouseClicked(event -> {
            try {
                backToHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

        setCardInfo(card);

        home.setOnMouseClicked(event -> {
            try {
                backToHomeAfterRent(this.invoice.getOrder());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    void setCardInfo(Card card) {
        this.cardCode.setText(card.getCardCode());
        this.owner.setText(card.getOwner());
        this.dateExpired.setText(card.getDateExpired());
    }

    public PaymentController getBController() {
        return (PaymentController) super.getBController();
    }

    @FXML
    public void submitToPay() throws Exception {
        try {
            this.card = getBController().createCard(this.cardCode.getText(), this.owner.getText(), this.cvvCode.getText(), this.dateExpired.getText());
            TransactionInfo transactionResult = getBController().submitToPay(this.invoice, this.card);
            if (!transactionResult.getErrorCode().equals("00")) {
                displayTransactionError(transactionResult.getErrorCode(), this.invoice.getOrder(), this.invoice.getAmount(), this.invoice.getContents());
            } else {
                if (this.invoice.getContents().contains("deposit")) {
                    moveToSuccessfulDepositScreen(this.invoice, transactionResult, this.card);
                } else {
                    getBController().moveToSuccessfulTransactionScreen(this.invoice, transactionResult, this.card, this.stage);
                }

            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

    @FXML
    void backToPreviousScreen(MouseEvent event) {
        this.getPreviousScreen().show();
    }

    void moveToSuccessfulDepositScreen(Invoice invoice, TransactionInfo transactionResult, Card card) throws SQLException, IOException {
        ResultScreenHandler resultScreenHandler = null;
        new Bike().updateDB(1, invoice.getOrder().getRentedBike());
        //this.invoice.getOrder().getRentedBike().setRenting(false);
        invoice.newInvoiceDB();
        transactionResult.newTransactionDB(invoice.getId(), card);
        resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult, invoice.getOrder());
        resultScreenHandler.show();
    }


    public void requestToPaymentScreen(BaseScreenHandler prev, HomeScreenHandler homeScreenHandler) {
        setPreviousScreen(prev);
        setHomeScreenHandler(homeScreenHandler);
        setScreenTitle("Payment Screen when Rent Bike");
        show();
    }

    public void notifyError() {

    }

}