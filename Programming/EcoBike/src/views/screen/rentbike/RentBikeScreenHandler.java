package views.screen.rentbike;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import controller.PaymentController;
import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import entity.bike.*;

public class RentBikeScreenHandler extends BaseScreenHandler {
    @FXML
    private ImageView home;

    @FXML
    private Label plate;

    @FXML
    private Label barcode;

    @FXML
    private Label type;

    @FXML
    private Label battery;

    @FXML
    private Label time;

    @FXML
    private Label deposit;

    @FXML
    void backToHome(MouseEvent event) throws IOException, SQLException {
        this.backToHome();
    }

    @FXML
    void backToPreviousScreen(MouseEvent event) {
        this.getPreviousScreen().show();
    }

    @FXML
    void moveToPaymentScreen(MouseEvent event) throws IOException {
        Order order = new Order(rented, LocalDateTime.now());
        Invoice invoice = new Invoice(order, order.getDeposit(), "Pay deposit for renting bike " + order.getRentedBike().getBarcode());
        BaseScreenHandler payment = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, invoice);
        payment.setBController(new PaymentController());
        payment.setPreviousScreen(this);
        payment.setHomeScreenHandler(homeScreenHandler);
        payment.setScreenTitle("Payment Screen when Rent Bike");
        payment.show();
    }

    private Bike rented;


    public RentBikeScreenHandler(Stage stage, String screenPath, Bike rented) throws IOException {
        super(stage, screenPath);
        // TODO Auto-generated constructor stub
        rented.setRenting(true);
        this.rented = rented;
        plate.setText(rented.getLicensePlate());
        barcode.setText(rented.getBarcode());
        type.setText(rented.getType());
        int depo = (int) (rented.getValue() * 0.4);
        deposit.setText(Utils.getCurrencyFormat(depo));
        if (rented instanceof StandardElectricBike || rented instanceof TwinElectricBike) {
            battery.setText(String.valueOf(((StandardElectricBike) rented).getBatteryPercentage()));
            time.setText(String.valueOf(((StandardElectricBike) rented).getRemainingTime()));
        } else {
            battery.setText("Not used");
            time.setText("none");
        }
    }

}
