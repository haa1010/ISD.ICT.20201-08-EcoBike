package views.screen.bike;


/**
 * This class is to display the bike info
 * @author Duong Thi Hue
 * @version 1.0
 */

import entity.bike.Bike;
import entity.bike.StandardElectricBike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.Utils;
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
    private Label stationLabel;
    @FXML
    private Label station;
    @FXML
    private Label remainingTime;
    private Bike bike;
    private static Logger LOGGER = Utils.getLogger(BikeInformationHandler.class.getName());
    private boolean isViewBike;

    /**
     * constructor
     * @param screenPath
     * @param bike
     * @param isViewBike
     * @throws IOException
     */
    public BikeInfo(String screenPath, Bike bike, boolean isViewBike) throws IOException {
        super(screenPath);
        this.bike = bike;
        this.isViewBike = isViewBike;
        setBikeInfo();
    }
	
	   /**
	    * set bike info in the screen
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
        if (isViewBike) {
            stationLabel.setText("Station");
        } else {
            stationLabel.setText("Rent station");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

