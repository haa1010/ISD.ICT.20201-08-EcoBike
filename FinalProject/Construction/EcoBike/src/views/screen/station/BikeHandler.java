package views.screen.station;

/**
 * This class is to display the bike info
 * @author Duong Thi Hue
 * @version 1.0
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import controller.ViewBikeController;
import entity.bike.Bike;
import entity.order.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import views.screen.bike.BikeInformationHandler;

public class BikeHandler extends FXMLScreenHandler {

    @FXML
    protected ImageView bikeImage;

    @FXML
    protected Label licensePlate;

    @FXML
    protected Label barcode;

    @FXML
    protected Label type;

    @FXML
    protected Button view;

    private static Logger LOGGER = Utils.getLogger(BikeHandler.class.getName());
    private Bike bike;
    private StationScreenHandler home;
    private Order order;

    /**
     * constructor, when the user is not using any bike
     * @param stage
     * @param screenPath
     * @param bike
     * @param home
     * @throws SQLException
     * @throws IOException
     */
    public BikeHandler(Stage stage, String screenPath, Bike bike, BaseScreenHandler home) throws SQLException, IOException {
        super(screenPath);
        this.bike = bike;
        this.home = (StationScreenHandler) home;
        initStationBikes(stage, home, bike, this.order);
    }
    
    /**
     * constructor, when the user is using a rented bike
     * @param stage
     * @param screenPath
     * @param bike
     * @param home
     * @param order
     * @throws SQLException
     * @throws IOException
     */
    public BikeHandler(Stage stage, String screenPath, Bike bike, BaseScreenHandler home, Order order) throws SQLException, IOException {
        super(screenPath);
        this.bike = bike;
        this.home = (StationScreenHandler) home;
        this.order = order;
        initStationBikes(stage, home, bike, this.order);
    }
    
    /**
     * initialize station with bikes
     * @param stage
     * @param home
     * @param bike
     * @param order
     * @throws SQLException
     */
    public void initStationBikes(Stage stage, BaseScreenHandler home, Bike bike, Order order) throws SQLException {
        setBikeInfo();
        view.setOnMouseClicked(e -> {
            BikeInformationHandler bikeScreen;
            try {
                if (order == null) {
                    bikeScreen = new BikeInformationHandler(stage, Configs.BIKE_INFO_PATH);
                    bikeScreen.setBController(new ViewBikeController());
                    bikeScreen.requestToViewBike(home, bike.getId(), bike.getType());
                } else {
                    bikeScreen = new BikeInformationHandler(stage, Configs.BIKE_INFO_PATH, order);
                    bikeScreen.setBController(new ViewBikeController());
                    bikeScreen.requestToViewBike(home, bike.getId(), bike.getType(), order);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * set bike info
     */
    private void setBikeInfo() {
        setImage(bikeImage, bike.getUrlImage());
        licensePlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
    }

}
