package views.screen.rentbike;

import java.io.IOException;
import java.sql.SQLException;

import controller.PaymentController;
import controller.RentBikeController;
import controller.ReturnBikeController;
import entity.bike.Bike;
import entity.invoice.Invoice;
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
import views.screen.home.HomeScreenHandler;
import views.screen.payment.PaymentScreenHandler;
/**
 * This class is to display the rent bike screen
 * @author Pham Nhat Linh
 * @version 1.0
 */
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
    /**
     * move back to home screen
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    void backToHome(MouseEvent event) throws IOException, SQLException {
        getBController().setRentBike(rented, false);
        this.backToHome();
    }

    @FXML
    /**
     * move back to previous screen
     * @param event
     */
    void backToPreviousScreen(MouseEvent event) {
        getBController().setRentBike(rented, false);
        this.getPreviousScreen().show();
    }

    private Bike rented;

    
    /**
     * constructor
     * @param stage
     * @param screenPath
     * @param rented
     * @throws IOException
     */
    public RentBikeScreenHandler(Stage stage, String screenPath, Bike rented) throws IOException {
        super(stage, screenPath);
        // TODO Auto-generated constructor stub
        this.rented = rented;
        this.setBController(new RentBikeController());
    }

    /**
     * set bike info
     * @throws IOException
     */
    public void setBikeInfo() throws IOException {
        getBController().setRentBike(rented, true);
        int depo = getBController().getDeposit(rented);
        deposit.setText(Utils.getCurrencyFormat(depo));
        setImage(bikeImage, rented.getUrlImage());
        BikeInfo bikeInfoItems = new BikeInfo(Configs.BIKE_INFO, this.rented, false);
        bikeInfo.getChildren().add(bikeInfoItems.getContent());
    }
    
    /**
     * request to move to this screen
     * @param prev
     * @param homeScreenHandler
     */
    public void requestToViewRentBike(BaseScreenHandler prev, HomeScreenHandler homeScreenHandler) {
        setPreviousScreen(prev);
        setHomeScreenHandler(homeScreenHandler);
        setScreenTitle("Rent Bike Screen");
        show();
    }

    /**
     * get the controller of this screen
     */
    public RentBikeController getBController() {
        return (RentBikeController) super.getBController();
    }

    /**
     * move to paymen screen
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void moveToPaymentScreen(MouseEvent event) throws IOException, SQLException {
        Invoice invoice = getBController().createInvoice(rented);
        PaymentScreenHandler payment = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, invoice);
        payment.setBController(new PaymentController());
        payment.requestToPaymentScreen(this, homeScreenHandler);
    }
}
