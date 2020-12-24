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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeInfo;
import views.screen.payment.PaymentScreenHandler;
import entity.bike.*;

public class RentBikeScreenHandler extends BaseScreenHandler {
    @FXML
    private ImageView home;

    @FXML
    private Pane bikeInfo;

    @FXML
    private Label plate;

    @FXML
    private Label barcode;

    @FXML
    private Label type;

    @FXML
    private Label battery;

    @FXML
    private ImageView bikeImage;

    @FXML
    private Label time;

    @FXML
    private Label deposit;

    @FXML
    void backToHome(MouseEvent event) throws IOException, SQLException {
        rented.setRenting(false);
        this.backToHome();
    }

    @FXML
    void backToPreviousScreen(MouseEvent event) {
        rented.setRenting(false);
        this.getPreviousScreen().show();
    }

    @FXML
    void moveToPaymentScreen(MouseEvent event) throws IOException, SQLException {
        Order order = new Order(rented, LocalDateTime.now());
        order.newOrderDB();
        Invoice invoice = new Invoice(order, order.getDeposit(), "Pay deposit for renting bike " + order.getRentedBike().getBarcode());
        invoice.newInvoiceDB();
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
        int depo = (int) (rented.getValue() * 0.4);
        deposit.setText(Utils.getCurrencyFormat(depo));
        setImage(bikeImage, rented.getUrlImage());

        BikeInfo bikeInfoItems = new BikeInfo(Configs.BIKE_INFO, this.rented, false);
        bikeInfo.getChildren().add(bikeInfoItems.getContent());


    }

}
