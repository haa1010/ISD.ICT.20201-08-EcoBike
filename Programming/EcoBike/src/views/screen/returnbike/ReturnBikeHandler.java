package views.screen.returnbike;

import controller.BaseController;
import controller.PaymentController;
import controller.ReturnBikeController;
import entity.bike.Bike;
import entity.bike.StandardElectricBike;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.station.Station;
import entity.transaction.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import subsystem.interbank.InterbankSubsystemController;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.payment.TransactionErrorScreenHandler;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ReturnBikeHandler extends BaseScreenHandler {

    @FXML
    private Text numberPlate;
    @FXML
    private Text barcode;
    @FXML
    private Text type;
//    @FXML
//    private Text battery;
//    @FXML
//    private Text remainingTime;
//    @FXML
//    private Text batteryLabel;
//    @FXML
//    private Text remainingTimeLabel;
    @FXML
    private Text deposit;
    @FXML
    private Text rentedTime;
    @FXML
    private Text total;
    @FXML
    private Text station;
    @FXML
    private Text start;
    @FXML
    private Text end;

    @FXML
    private ImageView bikeImage;

    @FXML
    private TextField owner;
    @FXML
    private TextField cardCode;

    @FXML
    private TextField dateExpired;
    @FXML
    private TextField cvvCode;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private Button editBtn;

    private Station s;
    private Card card;
    private Order order;

    private static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    public ReturnBikeHandler(Stage stage, String screenPath, BaseController bController, Station station, Order order) throws IOException {
        super(stage, screenPath);
        setBController(bController);
        this.order = order;
        this.s = station;

        setBikeInfo();
//        card = getBController().getCard();
         card = new Card("121319_group8_2020", "Group 8", "128", "1125");
        setCardInfo();
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
        int deposit1 = (int)(bike.getValue() * 0.4);
        deposit.setText(Utils.getCurrencyFormat(deposit1));
        // set image from url
        String imageSource = bike.getUrlImage();
        boolean backgroundLoading = true;

        Image image = new Image(imageSource, backgroundLoading);
        bikeImage.setImage(image);

//        if (bike instanceof StandardElectricBike) {
//            int batteryPercentage = ((StandardElectricBike) bike).getBatteryPercentage();
//            battery.setText(batteryPercentage + " %");
//            remainingTime.setText(Utils.convertTime(((StandardElectricBike) bike).getRemainingTime()));
//        } else {
//            batteryLabel.setVisible(false);
//            battery.setVisible(false);
//            remainingTimeLabel.setVisible(false);
//            remainingTime.setVisible(false);
//        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd ");
        start.setText((order.getStart().format(formatter)));
        end.setText((LocalDateTime.now()).plusHours(2).format(formatter));
        rentedTime.setText(String.valueOf((Duration.between(order.getStart(), (LocalDateTime.now()).plusHours(2))).toMinutes()) + " minutes");

        int totalAmount =  new ReturnBikeController().calculateAmount(1, LocalDateTime.now().minusHours(2));
        total.setText(Utils.getCurrencyFormat(totalAmount));
    }

    @FXML
    void moveToPaymentScreen(MouseEvent event) throws IOException {
        order.setEnd(LocalDateTime.now());
        Invoice invoice = new Invoice(order);
        BaseScreenHandler payment = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, invoice);
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
        cvvCode.setText("***");
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

        int amount  = 100;
        interbank.payOrder(card, amount, "return bike");

        // else error then display transaction error screen

        displayTransactionError(String.valueOf(3));

    }

    void displayTransactionError (String errorCode) throws IOException {
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
    void backToDockSelection (MouseEvent event) throws IOException {
        SelectDockToReturnBikeScreenHandler d = new SelectDockToReturnBikeScreenHandler(stage, Configs.SELECT_DOCK_TO_RETURN_BIKE_PATH,  order);
        d.show();
    }

    @FXML
    void backToHome(MouseEvent event) throws IOException {
        HomeScreenHandler homeScreenHandler = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
//        HomeScreenHandler.setHomeScreenHandler(homeScreenHandler);
//        HomeScreenHandler.setBController(new ViewCartController());
//        HomeScreenHandler.requestToViewCart(this.getPreviousScreen());
    }
}
