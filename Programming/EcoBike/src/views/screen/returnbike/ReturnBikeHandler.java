package views.screen.returnbike;

import controller.BaseController;
import controller.PaymentController;
import controller.ResultScreenController;
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
import views.screen.payment.ResultScreenHandler;
import views.screen.payment.TransactionErrorScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
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
    private String invoiceContents;

    private static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    public ReturnBikeHandler(Stage stage, String screenPath, BaseController bController, Station station, Order order) throws IOException {
        super(stage, screenPath);
        setBController(bController);
        this.order = order;
        this.s = station;
        
        //update rented bike's station
        this.order.getRentedBike().setStation(s);

        setBikeInfo();

        this.card = new Card("121319_group8_2020", "Group 8", "128", "1125");
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
            this.invoiceContents = "Pay additional for returning bike";
            total.setText(Utils.getCurrencyFormat(totalAmount));
        }
        // else refund
        else {
            payType.setText("Refund");
            this.invoiceContents = "Refund for returning bike";
            total.setText(Utils.getCurrencyFormat(-totalAmount));
        }
    }

    @FXML
    void moveToPaymentScreen(MouseEvent event) throws IOException, SQLException {
        order.setEnd(LocalDateTime.now());
        order.updateOrderDB();
        Invoice invoice = new Invoice(order, totalAmount, this.invoiceContents);
        invoice.newInvoiceDB();
        
        BaseScreenHandler payment = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, invoice, this.card);
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
    }

    @FXML
    void submitReturnBike(MouseEvent event) throws IOException, SQLException {
    	
    	// update db invoice, order
    	order.setEnd(LocalDateTime.now());
        order.updateOrderDB();
        Invoice invoice = new Invoice(order, totalAmount, this.invoiceContents);
        invoice.newInvoiceDB();

        // call API if success display invoice screen
        InterbankSubsystemController interbank = new InterbankSubsystemController();

        // pay more if rentingFee > deposit, else refund to account
        TransactionInfo transactionResult;
        card.setCvvCode(cvvCode.getText());
        if(totalAmount > 0) {
            transactionResult = interbank.payOrder(card, totalAmount, "Pay additional for returning bike");
        }
        else {
            transactionResult = interbank.refund(card, - totalAmount, "Refund for returning bike");
        }
        // else error then display transaction error screen

        if(!transactionResult.getErrorCode().equals("00")) {
            displayTransactionError(transactionResult.getErrorCode(), this.order,totalAmount, this.invoiceContents);
            
        }
        else {
        	
        	// update transaction db
        	transactionResult.newTransactionDB(invoice.getId());
        	
            ResultScreenHandler resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult);
            resultScreenHandler.show();
        }

    }


    @FXML
    void backToDockSelection(MouseEvent event) throws IOException {
        SelectDockToReturnBikeScreenHandler d = new SelectDockToReturnBikeScreenHandler(stage, Configs.SELECT_DOCK_TO_RETURN_BIKE_PATH, order);
        d.show();
    }
}
