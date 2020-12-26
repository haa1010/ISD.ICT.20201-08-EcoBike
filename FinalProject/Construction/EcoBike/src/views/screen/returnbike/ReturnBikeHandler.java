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

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * This class is to display the return bike screen
 * @author Tran Thi Hang
 * @version 1.0
 */
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
    private Text errorMessage;

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

    private Station newStation;
    private Card card;
    private Order order;
    private int totalAmount;
    private String invoiceContents;

    private static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    /**
     * constructor
     * @param stage
     * @param screenPath
     * @param bController
     * @param station
     * @param order
     * @throws IOException
     */
    public ReturnBikeHandler(Stage stage, String screenPath, BaseController bController, Station station, Order order) throws IOException {
        super(stage, screenPath);
        setBController(bController);
        this.order = order;
        this.newStation = station;

        //update rented bike's station
        this.order.getRentedBike().setStation(newStation);
        setBikeInfo();
        setCardInfo();
        home.setOnMouseClicked(event -> {
            try {
                backToHomeAfterRent(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * get the controller of this screen
     */
    public ReturnBikeController getBController() {
        return (ReturnBikeController) super.getBController();
    }

    /**
     * set bike info
     */
    private void setBikeInfo() {
        Bike bike = this.order.getRentedBike();
        numberPlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
        station.setText(newStation.getName());
        int deposit1 = getBController().getBikeDeposit(bike);
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
        getBController().setAmountOrder(order, rFee);
        rentingFee.setText(Utils.getCurrencyFormat(rFee));

        totalAmount = rFee - deposit1;
        // pay more if rentingFee > deposit
        if (totalAmount > 0) {
            payType.setText("Pay amount");
            this.invoiceContents = "Pay additional for returning bike";
            total.setText(Utils.getCurrencyFormat(totalAmount));
        }
        // else refund
        else {
            payType.setText("ReturnBike");
            this.invoiceContents = "Refund for returning bike";
            total.setText(Utils.getCurrencyFormat(-totalAmount));
        }
    }

    @FXML
    /**
     * move to payment screen
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    void moveToPaymentScreen(MouseEvent event) throws IOException, SQLException {
        Invoice invoice = getBController().createInvoice(order, totalAmount, this.invoiceContents);
        PaymentScreenHandler payment = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, invoice, this.card);
        payment.setBController(new PaymentController());
        payment.requestToPaymentScreen(this, homeScreenHandler);
    }
    
    /**
     * set the card info display in the current screen
     */
    private void setCardInfo() {
        this.card = getBController().createCard("121319_group8_2020", "Group 8", "128", "1125");
        owner.setText(card.getOwner());
        cardCode.setText(card.getCardCode());
        dateExpired.setText(card.getDateExpired());
    }

    @FXML
    /**
     * handle the process when user click submit to return bike
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    void submitReturnBike(MouseEvent event) throws IOException, SQLException {

        getBController().setCvvCode(cvvCode.getText(), card);
        try {
            getBController().validateCard(card.getCardCode(), card.getOwner(), card.getCvvCode(), card.getDateExpired());
            getBController().processReturnBike(card, totalAmount, order, invoiceContents, this.stage, homeScreenHandler, this);
        } catch (Exception e) {
            errorMessage.setText("* You have to fill in security code");
        }
    }

    /**
     * back to select dock screen
     * @param event
     * @throws IOException
     */
    @FXML
    void backToDockSelection(MouseEvent event) throws IOException {
        SelectDockToReturnBikeScreenHandler d = new SelectDockToReturnBikeScreenHandler(stage, Configs.SELECT_DOCK_TO_RETURN_BIKE_PATH, order);
        d.show();
    }
}
