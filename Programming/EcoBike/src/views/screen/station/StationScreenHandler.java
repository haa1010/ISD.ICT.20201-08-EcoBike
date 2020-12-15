package views.screen.station;

import controller.HomeController;
import controller.ViewStationController;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.home.StationHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StationScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    Label name1;

    @FXML
    Label name2;

    @FXML
    Label address;

    @FXML
    Label area;

    @FXML
    Label availableBikes;

    @FXML
    Label emptyDocks;

    @FXML
    ImageView home;

    private Station station;

    public StationScreenHandler(Stage stage, String screenPath, Station station) throws IOException {
        super(stage, screenPath);
        this.station = station;
        try {
            setStationInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBController(new HomeController());
        home.setOnMouseClicked(event -> {
            HomeScreenHandler homeScreen;
            System.out.println("Home clicked!");
            try {
                homeScreen = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
                homeScreen.setHomeScreenHandler(homeScreen);
                homeScreen.setBController(new ViewStationController());
                homeScreen.requestToReturnHome(this);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void requestToViewStation(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Station");
        show();
    }

    private void setStationInfo() throws SQLException {
        try {
            name1.setText(station.getName());
            name2.setText(station.getName());
            address.setText(station.getAddress());
            area.setText(station.getArea() + " m2");
            availableBikes.setText(Integer.toString(station.getNumAvailableBike()));
            emptyDocks.setText(Integer.toString(station.getNumEmptyDockPoint()));
        } catch (NullPointerException e) {
            System.out.println("Station is null.");
        }
    }
}
