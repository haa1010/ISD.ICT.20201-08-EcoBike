package views.screen.home;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import controller.ViewStationController;
import entity.order.Order;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import utils.Configs;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.popup.PopupHomeScreen;
import views.screen.station.StationScreenHandler;

public class StationHandler extends FXMLScreenHandler {

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

    private Station station;
    private HomeScreenHandler home;
    private Order order;

    public StationHandler(String screenPath, Station station, HomeScreenHandler home) throws SQLException, IOException {
        super(screenPath);
        this.station = station;
        this.home = home;
        //Select dock marker
        initHomeStations(station, home, this.order);
        setStationInfo();
    }

    public StationHandler(String screenPath, Station station, HomeScreenHandler home, Order order) throws SQLException, IOException {
        super(screenPath);
        this.station = station;
        this.home = home;
        this.order = order;
        initHomeStations(station, home, order);
        setStationInfo();
    }

    public Station getStation(){
        return station;
    }

    public void initHomeStations(Station station, HomeScreenHandler home, Order order) {
        view.setOnMouseClicked(event -> {
            StationScreenHandler stationScreen;
            try {
                if (order == null)
                    stationScreen = new StationScreenHandler(home.getStage(), Configs.STATION_PATH, station, home);
                else stationScreen = new StationScreenHandler(home.getStage(), Configs.STATION_PATH, station, home, order);
                stationScreen.setHomeScreenHandler(home);
                stationScreen.setBController(new ViewStationController());
                stationScreen.requestToViewStation(home);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        distance.setOnMouseClicked(event -> {
            PopupHomeScreen popupHomeScreen;
            try {
                System.out.println("Distance clicked");
                PopupHomeScreen.showPopup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setStationInfo() throws SQLException {
        // set the cover image of station
        stationName.setText(station.getName());
        stationAddress.setText("Address: " + station.getAddress());
        stationAvailableBike.setText("Available bikes: " + (station.getNumAvailableBike()));
        stationEmptyDock.setText("Empty docks: " + (station.getNumEmptyDockPoint()));

        setImage(stationImage, "assets/images/dock-img.png");
    }

}
