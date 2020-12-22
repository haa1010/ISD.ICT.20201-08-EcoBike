package views.screen.station;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import controller.ViewBikeController;
import entity.bike.Bike;
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

    public BikeHandler(Stage stage, String screenPath, Bike bike, BaseScreenHandler home) throws SQLException, IOException {
        super(screenPath);
        this.bike = bike;
        this.home = (StationScreenHandler) home;
        setBikeInfo();
        view.setOnMouseClicked(e -> {
            BikeInformationHandler bikeScreen;
            try {
                bikeScreen = new BikeInformationHandler(stage, Configs.BIKE_INFO_PATH);
                //    bikeScreen.setHomeScreenHandler(new HomeScreenHandler(new Stage(), Configs.HOME_PATH));
                bikeScreen.setBController(new ViewBikeController());
                bikeScreen.requestToViewBike(home, bike.getId(), bike.getType());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    private void setBikeInfo() throws SQLException {

        setImage(bikeImage, bike.getUrlImage());
        licensePlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
//        spinnerChangeNumber.setValueFactory(
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
//        );
    }

}
