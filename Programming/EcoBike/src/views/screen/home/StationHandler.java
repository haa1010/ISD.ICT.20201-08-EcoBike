package views.screen.home;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import controller.ViewStationController;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.station.StationScreenHandler;

public class StationHandler extends FXMLScreenHandler{

    @FXML
    protected ImageView stationImage;

    @FXML
    protected Label stationName;

    @FXML
    protected Label stationAddress;

    @FXML
    protected Label stationAvailableBike;

    @FXML
    protected Label stationEmptyDock;


    @FXML
    protected Button view;

    @FXML
    protected Button distance;

    private static Logger LOGGER = Utils.getLogger(StationHandler.class.getName());
    private Station station;
    private HomeScreenHandler home;

    public StationHandler(String screenPath, Station station, HomeScreenHandler home) throws SQLException, IOException{
        super(screenPath);
        this.station = station;
        this.home = home;
        //Select dock marker
        view.setOnMouseClicked(event -> {
            StationScreenHandler stationScreen;
            try {
                stationScreen = new StationScreenHandler(home.getStage(), Configs.STATION_PATH, station);
                stationScreen.setHomeScreenHandler(home);
                stationScreen.setBController(new ViewStationController());
                stationScreen.requestToViewStation(home);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        setStationInfo();
    }

    public Station getStation(){
        return station;
    }

    private void setStationInfo() throws SQLException {
        // set the cover image of media
        File file = new File("assets/images/map (1) 1.png");
        Image image = new Image(file.toURI().toString());
        stationImage.setFitHeight(160);
        stationImage.setFitWidth(152);
        stationImage.setImage(image);

        stationName.setText(station.getName());
        stationAddress.setText("Address: " + station.getAddress());
        stationAvailableBike.setText("Available bikes: " + (station.getNumAvailableBike()));
        stationEmptyDock.setText("Empty docks: " + (station.getNumEmptyDockPoint()));
//        spinnerChangeNumber.setValueFactory(
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
//        );

        setImage(stationImage, "assets/images/map (1) 1.png");
    }

}
