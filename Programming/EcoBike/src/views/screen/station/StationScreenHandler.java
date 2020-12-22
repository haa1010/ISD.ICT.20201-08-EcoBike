package views.screen.station;

import controller.ViewStationController;
import entity.bike.Bike;
import entity.order.Order;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

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
    ImageView homeLogo;

    @FXML
    VBox vbox1;

    @FXML
    VBox vbox2;

    @FXML
    VBox vbox3;

    @FXML
    HBox hboxBike;

    private Station station;

    List stationItems;

    private Order order;

    public StationScreenHandler(Stage stage, String screenPath, Station station, BaseScreenHandler homeScreenHandler) throws IOException {
        super(stage, screenPath);
        this.station = station;
        initStation(stage, this, this.order);
    }

    public StationScreenHandler(Stage stage, String screenPath, Station station, BaseScreenHandler homeScreenHandler, Order order) throws IOException {
        super(stage, screenPath);
        this.station = station;
        this.order = order;
        initStation(stage, this, order);
    }

    public ViewStationController getBController() {
        return (ViewStationController) super.getBController();
    }

    public Stage getStage() {
        return this.stage;
    }

    public void initStation(Stage stage, StationScreenHandler home, Order order) {
        try {
            setStationInfo();
            List medium = getBController().getAllBikeAvailable(station.getId());
            this.stationItems = new ArrayList<>();
            for (Object object : medium) {
                Bike bike = (Bike) object;
                BikeHandler bikeHandler;
                if (order == null) {
                    bikeHandler = new BikeHandler(stage, Configs.BIKE_STATION_PATH, bike, home);
                    homeLogo.setOnMouseClicked(e -> {
                        try {
                            backToHome();
                        } catch (IOException | SQLException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                }
                else {
                    homeLogo.setOnMouseClicked(e -> {
                        try {
                            backToHomeAfterRent(order);
                        } catch (IOException | SQLException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                    bikeHandler = new BikeHandler(stage, Configs.BIKE_STATION_PATH, bike, home, order);
                }
                this.stationItems.add(bikeHandler);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        addBikeStation(stationItems);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBController(new ViewStationController());
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

    public void addBikeStation(List items) {
        ArrayList stationItems = (ArrayList) ((ArrayList) items).clone();
        hboxBike.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while (!stationItems.isEmpty()) {
            hboxBike.getChildren().forEach(node -> {
                VBox vBox = (VBox) node;
                vBox.setSpacing(20);
                if (items.size() % 3 == 0) {
                    while (vBox.getChildren().size() < items.size() / 3 && !stationItems.isEmpty()) {
                        BikeHandler bike = (BikeHandler) stationItems.get(0);
                        vBox.getChildren().add(bike.getContent());
                        stationItems.remove(bike);
                    }
                } else {
                    while (vBox.getChildren().size() < items.size() / 3 + 1 && !stationItems.isEmpty()) {
                        BikeHandler bike = (BikeHandler) stationItems.get(0);
                        vBox.getChildren().add(bike.getContent());
                        stationItems.remove(bike);
                    }
                }
            });
            return;
        }
    }
}
