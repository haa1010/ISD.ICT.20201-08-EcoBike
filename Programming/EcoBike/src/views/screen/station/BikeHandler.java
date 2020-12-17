package views.screen.station;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import controller.ViewBikeController;
import controller.ViewStationController;
import entity.bike.Bike;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import views.screen.bike.BikeScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupHomeScreen;
import views.screen.station.StationScreenHandler;

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

    public BikeHandler(Stage stage, String screenPath, Bike bike, BaseScreenHandler home) throws SQLException, IOException {
        super(screenPath);
        this.bike = bike;
        this.home = (StationScreenHandler) home;
        setBikeInfo();
        view.setOnMouseClicked(e -> {
            BikeScreenHandler bikeScreen;
            try {
                bikeScreen = new BikeScreenHandler(stage, Configs.BIKE_INFO_PATH);
                bikeScreen.setHomeScreenHandler(new HomeScreenHandler(new Stage(), Configs.HOME_PATH));
                bikeScreen.setBController(new ViewBikeController());
                bikeScreen.requestToViewBike(home, bike.getId(), bike.getType());
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
                e1.printStackTrace();
            }
        });
    }

    private void setBikeInfo() throws SQLException {
        // set the cover image of media
//        File file = new File("assets/images/map (1) 1.png");
        Image image = new Image(bike.getUrlImage());
//        bikeImage.setFitHeight(152);
//        bikeImage.setFitWidth(315);
        bikeImage.setImage(image);
        licensePlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
//        spinnerChangeNumber.setValueFactory(
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
//        );
    }

}
