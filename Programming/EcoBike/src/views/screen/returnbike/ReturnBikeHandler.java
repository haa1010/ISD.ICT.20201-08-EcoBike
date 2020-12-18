package views.screen.returnbike;

import controller.BaseController;
import controller.PaymentController;
import controller.ReturnBikeController;
import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.station.Station;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import subsystem.interbank.InterbankSubsystemController;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.payment.TransactionErrorScreenHandler;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class ReturnBikeHandler extends BaseScreenHandler {

    @FXML
    private Text numberPlate;
    @FXML
    private Text barcode;
    @FXML
    private Text type;
    @FXML
    private Text deposit;
    @FXML
    private Text rentedTime;
    @FXML
    private Text total;
    @FXML
    private Text rentingFee;
    @FXML
    private Text station;
    @FXML
    private Text start;
    @FXML
    private Text end;
    @FXML
    private Text payType;

    @FXML
    private ImageView bikeImage;

    @FXML
    private TextField owner;
    @FXML
    private TextField cardCode;

    @FXML
    private TextField dateExpired;
    @FXML
    private PasswordField cvvCode;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private Button editBtn;
    @FXML
    private ImageView home;

    private Station s;
    private Card card;
    private Order order;
    private int totalAmount;

    private static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    public ReturnBikeHandler(Stage stage, String screenPath, BaseController bController, Station station, Order order) throws IOException {
        super(stage, screenPath);
        setBController(bController);
        this.order = order;
        this.s = station;

        setBikeInfo();

        card = new Card("121319_group8_2020", "Group 8", "128", "1125");
        setCardInfo();

        home.setOnMouseClicked(event -> {
            try {
                backToHomeAfterRent(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ReturnBikeController getBController() {
        return (ReturnBikeController) super.getBController();
    }

    private void setBikeInfo() {

        Bike bike = this.order.getRentedBike();
        numberPlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
        station.setText(s.getName());
        int deposit1 = (int) (bike.getValue() * 0.4);
        deposit.setText(Utils.getCurrencyFormat(deposit1));
        setImage(bikeImage, bike.getUrlImage());

        start.setText((order.getStart().format(Utils.DATE_FORMATTER)));
        end.setText(LocalDateTime.now().format(Utils.DATE_FORMATTER));

        // format rentedTime
        int hours = Duration.between(order.getStart(), LocalDateTime.now()).toHoursPart();
        long days = Duration.between(order.getStart(), LocalDateTime.now()).toDaysPart();
        String rentedTimeText = days > 0 ? days + " days " : "";
        rentedTimeText += hours > 0 ? hours + " hours " : "";
        rentedTimeText += Duration.between(order.getStart(), LocalDateTime.now()).toMinutesPart() + " minutes";
        rentedTime.setText(rentedTimeText);

        int rFee = new ReturnBikeController().calculateAmount(order.getRentedBike().getCoefficient(), order.getStart());
        rentingFee.setText(Utils.getCurrencyFormat(rFee));

        totalAmount = rFee - deposit1;
        // pay more if rentingFee > deposit
        if (totalAmount > 0) {
            payType.setText("Pay amount");
            total.setText(Utils.getCurrencyFormat(totalAmount));
        }
        // else refund
        else {
            payType.setText("Refund");
            total.setText(Utils.getCurrencyFormat(-totalAmount));
        }
    }

    @FXML
    void moveToPaymentScreen(MouseEvent event) throws IOException {
        order.setEnd(LocalDateTime.now());
        Invoice invoice = new Invoice(order);
        BaseScreenHandler payment = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, invoice, card);
        payment.setBController(new PaymentController());
        payment.setPreviousScreen(this);
        payment.setHomeScreenHandler(homeScreenHandler);
        payment.setScreenTitle("Payment Screen when Return Bike");
        payment.show();
    }

    private void setCardInfo() {
        owner.setText(card.getOwner());
        cardCode.setText(card.getCardCode());
        dateExpired.setText(card.getDateExpired());
        cvvCode.setText(card.getCvvCode());
    }

    @FXML
    void submitReturnBike(MouseEvent event) throws IOException {

//        Thông tin thẻ:
//        Mã thẻ: 121319_group8_2020
//        Chủ thẻ: Group 8
//        CVV: 128
//        Ngày hết hạn: 1125
//        Key Pair
//        App Code: A1SRyiBqj/E=
//        Secret Key: BtNH8J4Tl/I=


        // call API if success display invoice screen
        InterbankSubsystemController interbank = new InterbankSubsystemController();

        // pay more if rentingFee > deposit, else refund to account
        if(totalAmount > 0) {
            TransactionInfo transactionResult = interbank.payOrder(card, totalAmount, "Pay more for returning bike");
        }
        else {
            TransactionInfo transactionResult = interbank.refund(card, totalAmount, "Refund when returning bike");
        }
        // else error then display transaction error screen

        displayTransactionError(String.valueOf(3));

    }

    void displayTransactionError(String errorCode) throws IOException {
        String errorMessage;
        errorMessage = Configs.errorCodes.get(errorCode);

        order.setEnd(LocalDateTime.now());
        Invoice invoice = new Invoice(order);

        TransactionErrorScreenHandler tes = new TransactionErrorScreenHandler(this.stage, Configs.TRANSACTION_ERROR_SCREEN_PATH, errorMessage, invoice);
        tes.setPreviousScreen(this);
        tes.setBController(new ReturnBikeController());
        tes.setHomeScreenHandler(homeScreenHandler);
        tes.setScreenTitle("Transaction Error Screen");
        tes.show();
    }

    @FXML
    void backToDockSelection(MouseEvent event) throws IOException {
        SelectDockToReturnBikeScreenHandler d = new SelectDockToReturnBikeScreenHandler(stage, Configs.SELECT_DOCK_TO_RETURN_BIKE_PATH, order);
        d.show();
    }
}
