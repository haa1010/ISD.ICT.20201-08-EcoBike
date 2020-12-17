package views.screen.bike;


import common.exception.PlaceOrderException;
import controller.RentBikeController;
import controller.ViewBikeController;
import entity.bike.*;
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
    private static Logger LOGGER = Utils.getLogger(BikeScreenHandler.class.getName());

    public BikeScreenHandler(Stage stage, String screenPath) throws IOException, SQLException {
        super(stage, screenPath);


    }

    public void setBike(Bike bike) throws SQLException {
        if (bike.getType().equals("Standard electric bike")) {
            this.bike = new StandardElectricBike().getBikeById(bike.getId());
        } else if (bike.getType().equals("Standard bike")) {
            this.bike = new StandardBike().getBikeById(bike.getId());
        } else if (bike.getType().equals("Twin bike")) {
            this.bike = new TwinBike().getBikeById(bike.getId());
        } else if (bike.getType().equals("Electric twin bike")) {
            this.bike = new TwinElectricBike().getBikeById(bike.getId());
        }
        bike.setStation(getBController().getStation(bike.getStation().getId()));
    }

    public ViewBikeController getBController() {
        return (ViewBikeController) super.getBController();
    }

    /**
     * set bike info to view
     */
    public void setBikeInfo() throws IOException {

        liscensePlateTitle.setText(bike.getLicensePlate());
        // set image from url
        String imageSource = bike.getUrlImage();
        boolean backgroundLoading = true;
// The image is being loaded in the background
        Image image = new Image(imageSource, backgroundLoading);
        urlImage.setImage(image);

        BikeInfo bikeInfoItems = new BikeInfo(Configs.BIKE_INFO, this.bike);
        bikeInfo.getChildren().add(bikeInfoItems.getContent());
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
