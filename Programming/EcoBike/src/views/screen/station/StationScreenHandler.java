package views.screen.station;

/**
 * This class is to display the station info
 * @author Nguyen Minh Thong
 * @version 1.0
 */
import controller.ReturnBikeController;
import controller.ViewStationController;
import entity.bike.Bike;
import entity.order.Order;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.returnbike.ReturnBikeHandler;
import views.screen.returnbike.SelectDockToReturnBikeScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StationScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Label name1;

    @FXML
    private Label name2;

    @FXML
    private Label address;

    @FXML
    private Label area;

    @FXML
    private Label availableBikes;

    @FXML
    private Label emptyDocks;

    @FXML
    private ImageView home;

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox2;

    @FXML
    private VBox vbox3;

    @FXML
    private HBox hboxBike;

    @FXML
    private Button returnBtn;

    private Station station;

    List stationItems;

    private Order order;

    /**
     * constructor, when the user is not using any bike
     * @param stage
     * @param screenPath
     * @param station
     * @param homeScreenHandler
     * @throws IOException
     */
    public StationScreenHandler(Stage stage, String screenPath, Station station, BaseScreenHandler homeScreenHandler) throws IOException {
        super(stage, screenPath);
        this.station = station;
        returnBtn.setVisible(false);

        this.home.setOnMouseClicked(e -> {
            try {
                backToHome();
            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
        });

        initStation(stage, this, this.order);
    }
    /**
     * constructor, when the user is using a rented bike
     * @param stage
     * @param screenPath
     * @param station
     * @param homeScreenHandler
     * @param order
     * @throws IOException
     */
    public StationScreenHandler(Stage stage, String screenPath, Station station, BaseScreenHandler homeScreenHandler, Order order) throws IOException {
        super(stage, screenPath);
        this.station = station;
        this.order = order;

        this.returnBtn.setOnMouseClicked(e -> {
            try {
                ReturnBikeHandler returnBikeHandler = new ReturnBikeHandler(stage, Configs.RETURN_BIKE_SCREEN_PATH, new ReturnBikeController(), this.station, order);
                returnBikeHandler.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        this.home.setOnMouseClicked(e -> {
            try {
                backToHomeAfterRent(order);
            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
        });

        initStation(stage, this, order);
    }

    /**
     * get the controller of this screen
     */
    public ViewStationController getBController() {
        return (ViewStationController) super.getBController();
    }

    public Stage getStage() {
        return this.stage;
    }
    
    /**
     * initialize station info
     * @param stage
     * @param home
     * @param order
     */
    public void initStation(Stage stage, StationScreenHandler home, Order order) {
        try {
            setStationInfo();
            List medium = getBController().getAllBikeAvailable(station.getId());
            this.stationItems = new ArrayList<>();
            for (Object object : medium) {
                Bike bike = (Bike) object;
                BikeHandler bikeHandler;
                if (order == null) {
                    bikeHandler = new BikeHandler(stage, Configs.BIKE_STATION_PATH, bike, this);
                    this.home.setOnMouseClicked(e -> {
                        try {
                            backToHome();
                        } catch (IOException | SQLException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                } else {
                    this.home.setOnMouseClicked(e -> {
                        try {
                            backToHomeAfterRent(order);
                        } catch (IOException | SQLException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                    bikeHandler = new BikeHandler(stage, Configs.BIKE_STATION_PATH, bike, this, order);
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
    
    /**
     * request to move to this screen
     * @param prevScreen
     * @throws SQLException
     */
    public void requestToViewStation(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Station");
        show();
    }

    /**
     * set station info
     * @throws SQLException
     */
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
    
    /**
     * add bike to station
     * @param items
     */
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
                int vid = hboxBike.getChildren().indexOf(node);
                int i = 0;
                while (i < items.size()) {
                    BikeHandler bike = (BikeHandler) stationItems.get(i);
                    if (i % 3 == vid) {
                        vBox.getChildren().add(bike.getContent());
                    }
                    i++;
                }
            });
            return;
        }
    }
}
