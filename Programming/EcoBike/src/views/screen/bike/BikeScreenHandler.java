package views.screen.bike;


import common.exception.PlaceOrderException;
import controller.RentBikeController;
import controller.ViewBikeController;
import entity.bike.*;
import entity.order.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.rentbike.RentBikeScreenHandler;
import views.screen.station.BikeHandler;
import views.screen.station.StationScreenHandler;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class BikeScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Label liscensePlateTitle;
    @FXML
    private Pane bikeInfo;
    private Bike bike;
    @FXML
    private ImageView urlImage;
    @FXML
    private Button canRent;

    private static Logger LOGGER = Utils.getLogger(BikeScreenHandler.class.getName());
    private HomeScreenHandler homeScreenHandler;

    public BikeScreenHandler(Stage stage, String screenPath) throws IOException, SQLException {
        super(stage, screenPath);
    }

    public BikeScreenHandler(Stage stage, String screenPath, Order order) throws IOException, SQLException {
        super(stage, screenPath);
    }

    public ViewBikeController getBController() {
        return (ViewBikeController) super.getBController();
    }

    public void requestToViewBike(BaseScreenHandler prevScreen, int id, String type) throws SQLException, IOException {
        setBikeInfo(id, type);
        setScreenTitle("View bike");
        show();
    }

    /**
     * set bike info to view
     */
    public void setBikeInfo(int id, String type) throws IOException, SQLException {
        this.bike = getBController().setBike(id, type);
        liscensePlateTitle.setText(bike.getLicensePlate());
        // set image from url
        setImage(urlImage, bike.getUrlImage());

        BikeInfo bikeInfoItems = new BikeInfo(Configs.BIKE_INFO, this.bike, true);
        bikeInfo.getChildren().add(bikeInfoItems.getContent());
        if (bike.isRenting()) {
            canRent.setDisable(true);
        } else {
            canRent.setDisable(false);
        }
    }


    /**
     * back to previous screen
     */
    @FXML
    private void cancelViewBike() throws IOException, SQLException {
        LOGGER.info("Cancel button clicked");
        if (this.getPreviousScreen() instanceof HomeScreenHandler) {
            backToHome();
        } else {
            StationScreenHandler stationScreenHandler = new StationScreenHandler(this.stage, Configs.STATION_PATH, bike.getStation(), homeScreenHandler);
            stationScreenHandler.requestToViewStation(this);
        }
    }

    /**
     * switch to rentBike screen
     */
    @FXML
    private void rentBike() throws IOException {
        LOGGER.info("Rent bike button clicked");

        RentBikeScreenHandler rentBike = new RentBikeScreenHandler(this.stage, Configs.RENT_BIKE_PATH, bike);
        rentBike.setPreviousScreen(this);
        rentBike.setHomeScreenHandler(homeScreenHandler);
        rentBike.setScreenTitle("Rent bike");
        rentBike.setBController(new RentBikeController());
        rentBike.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void requestToViewBike(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Bike");
        show();
    }
}
