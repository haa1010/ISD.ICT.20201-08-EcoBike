package views.screen.bike;


import controller.ViewBikeController;
import entity.bike.Bike;
import entity.bike.StandardElectricBike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class BikeInfo extends FXMLScreenHandler implements Initializable {
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
    private Label station;
    @FXML
    private Label remainingTime;
    private Bike bike;
    private static Logger LOGGER = Utils.getLogger(BikeScreenHandler.class.getName());


    public BikeInfo(String screenPath, Bike bike) throws IOException {
        super(screenPath);
        this.bike = bike;
        setBikeInfo();
    }

    /**
     * set bike info to view
     */
    public void setBikeInfo() {
        liscensePlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
        station.setText(bike.getStation().getName());

        int deposit1 = (int) (bike.getValue() * 0.4);

        deposit.setText(Utils.getCurrencyFormat(deposit1));
        // set image from url
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

