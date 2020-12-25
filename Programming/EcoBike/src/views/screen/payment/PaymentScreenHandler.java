package views.screen.payment;

import java.io.IOException;
import controller.PaymentController;
import entity.invoice.Invoice;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private Text errorMessage;

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
        this.card = null;
        this.invoice = invoice;

        home.setOnMouseClicked(event -> {
            try {
                backToHome();
            } catch (Exception e) {
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
        this.card = getBController().createCard(this.cardCode.getText(), this.owner.getText(), this.cvvCode.getText(), this.dateExpired.getText());
        if(!getBController().validateCard(card)) {
            errorMessage.setText("* You have to fill in all the fields");
        }
        else {
            TransactionInfo transactionResult = getBController().submitToPay(this.invoice, this.card);

            if (!transactionResult.getErrorCode().equals("00")) {
                displayTransactionError(transactionResult.getErrorCode(), this.invoice.getOrder(), this.invoice.getAmount(), this.invoice.getContents());
            } else {
                if (this.invoice.getContents().contains("deposit")) {
                    getBController().moveToSuccessfulDepositScreen(this.invoice, transactionResult, this.card, this.stage);
                } else {
                    getBController().moveToSuccessfulTransactionScreen(this.invoice, transactionResult, this.card, this.stage);
                }
            }
        }
    }


    @FXML
    void backToPreviousScreen(MouseEvent event) {
        this.getPreviousScreen().show();
    }


    public void requestToPaymentScreen(BaseScreenHandler prev, HomeScreenHandler homeScreenHandler) {
        setPreviousScreen(prev);
        setHomeScreenHandler(homeScreenHandler);
        setScreenTitle("Payment Screen");
        show();
    }
}