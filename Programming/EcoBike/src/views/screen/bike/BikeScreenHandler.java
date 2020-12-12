package views.screen.bike;


import common.exception.PlaceOrderException;
import controller.RentBikeController;
import controller.ViewBikeController;
import entity.bike.Bike;
import entity.bike.StandardElectricBike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class BikeScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML
    private Label liscensePlate;
    @FXML
    private Label barcode;
    @FXML
    private Label type;
    @FXML
    private Label liscensePlateTitle;
    @FXML
    private Label deposit;
    @FXML
    private Label batteryPercentage;
    @FXML
    private Label batteryLabel;
    @FXML
    private Label remainingLabel;
    @FXML
    private Label remainingTime;

    private Bike bike;
    @FXML
    private ImageView urlImage;
    @FXML
    private Button cancel;
    private static Logger LOGGER = Utils.getLogger(BikeScreenHandler.class.getName());

    public BikeScreenHandler(Stage stage, String screenPath, Bike bike) throws IOException {

        super(stage, screenPath);
        this.bike = bike;
        setBikeInfo();

    }


    public ViewBikeController getBController() {
        return (ViewBikeController) super.getBController();
    }

    /**
     * set bike info to view
     */
    public void setBikeInfo() {
        liscensePlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
        int deposit1 = (int) (bike.getValue() * 0.4);
        liscensePlateTitle.setText(bike.getLicensePlate());
        deposit.setText(Utils.getCurrencyFormat(deposit1));
        // set image from url
        String imageSource = bike.getUrlImage();
        boolean backgroundLoading = true;
// The image is being loaded in the background
        Image image = new Image(imageSource, backgroundLoading);
        urlImage.setImage(image);

        if (bike instanceof StandardElectricBike) {
            batteryLabel.setText("Battery percentage");
            int battery = ((StandardElectricBike) bike).getBatteryPercentage();
            batteryPercentage.setText(battery + " %");
            remainingLabel.setText("Remaining time");
            remainingTime.setText(Utils.convertTime(((StandardElectricBike) bike).getRemainingTime()));
        } else {
            batteryLabel.setText("");
            remainingLabel.setText(" ");

        }

    }


    public void backToHome() {
        LOGGER.info("home button clicked");
    }

    /**
     * back to previous screen
     */
    @FXML
    private void cancelViewBike() {
        LOGGER.info("Cancel button clicked");
        // this.getPreviousScreen().show();
    }

    /**
     * switch to rentBike screen
     */
    @FXML
    private void rentBike() {
        LOGGER.info("Rent bike button clicked");
        getBController().rentBike();
//        RentBikeHandler rentBike = new RentBikeHandler(this.stage, Configs.SHIPPING_SCREEN_PATH, bike);
//        rentBike.setPreviousScreen(this);
//        rentBike.setHomeScreenHandler(homeScreenHandler);
//        rentBike.setScreenTitle("Rent bike");
//        rentBike.setBController(new RentBikeController());
//        rentBike.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
