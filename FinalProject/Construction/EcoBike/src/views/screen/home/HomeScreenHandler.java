package views.screen.home;

import controller.HomeController;
import controller.RentBikeController;
import controller.ViewBikeController;
import controller.SelectDockToReturnBikeController;
import entity.order.Order;
import entity.station.Station;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.barcode.BarcodeScreenHandler;
import views.screen.returnbike.SelectDockToReturnBikeScreenHandler;
import views.screen.bike.RentingBikeHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
/**
 * This class is to display the home screen
 * @author Do Minh Thong
 * @version 1.0
 */

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox hboxHome;

    @FXML
    private Label homeLabel;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView home;

    @FXML
    private ImageView search;

    @FXML
    private VBox vboxDock1;

    @FXML
    private VBox vboxDock2;

    @FXML
    private TextField searchInput;

    @FXML
    private Button rentBikeButton;

    @FXML
    private Button viewRent;

    private List homeItems;
    @FXML
    private Button viewRentBike;
    private String searchString = searchInput.getText();
    private Order order;

    @FXML
    /**
     * search dock
     * @param ae
     * @throws IOException
     * @throws SQLException
     */
    public void onEnter(ActionEvent ae) throws IOException, SQLException {
        searchString = searchInput.getText();
        initHome(searchString, this.order);
    }

    /**
     * constructor, when the user is not using any bike
     * @param stage
     * @param screenPath
     * @throws IOException
     * @throws SQLException
     */
    public HomeScreenHandler(Stage stage, String screenPath) throws IOException, SQLException {
        super(stage, screenPath);
        this.order = null;
        this.searchString = null;
        initHome(this.searchString, this.order);
    }
    
    /**
     * constructor, when the user is using a rented bike
     * @param stage
     * @param screenPath
     * @param order
     * @throws IOException
     * @throws SQLException
     */
    public HomeScreenHandler(Stage stage, String screenPath, Order order) throws IOException, SQLException {
        super(stage, screenPath);
        this.order = order;
        this.searchString = null;
        initHome(this.searchString, order);

        if (order != null) {
            rentBikeButton.setText("Return Bike");
            rentBikeButton.setStyle("-fx-background-color: #4d5deb");
            rentBikeButton.setOnMouseClicked(e -> {
                SelectDockToReturnBikeScreenHandler selectDock;
                try {
                    selectDock = new SelectDockToReturnBikeScreenHandler(this.stage, Configs.SELECT_DOCK_TO_RETURN_BIKE_PATH, order);
                    selectDock.setHomeScreenHandler(this);
                    selectDock.setBController(new SelectDockToReturnBikeController());
                    selectDock.requestToSelectDock(this);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            viewRent.setVisible(true);
            viewRent.setOnMouseClicked(e -> {
                RentingBikeHandler viewRentingBike;
                try {
                    viewRentingBike = new RentingBikeHandler(this.stage, Configs.RENT_BIKE_INFO_PATH, order);
                    viewRentingBike.setHomeScreenHandler(this);
                    viewRentingBike.setBController(new ViewBikeController());
                    viewRentingBike.requestToViewRentingBike(this);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    /**
     * get the controller of this screen
     */
    public HomeController getBController() {
        return (HomeController) super.getBController();
    }


    @Override
    /**
     * initialize the screen
     */
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new HomeController());
        try {
            rentBikeButton.setOnMouseClicked(e -> {
                BarcodeScreenHandler barcodeScreen;
                try {
                    barcodeScreen = new BarcodeScreenHandler(this.stage, Configs.BARCODE_SCREEN_PATH);
                    barcodeScreen.setHomeScreenHandler(this);
                    barcodeScreen.setBController(new RentBikeController());
                    barcodeScreen.requestToViewBarcode(this);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            viewRent.setVisible(false);
        } catch (Exception e) {
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }

        if (order != null) {
            rentBikeButton.setText("Rent Bike");
            rentBikeButton.setStyle("-fx-background-color:  #eb4d55");
            rentBikeButton.setOnMouseClicked(e -> {
                RentingBikeHandler viewRentingBikeHandler;
                try {
                    viewRentingBikeHandler = new RentingBikeHandler(this.stage, Configs.RENT_BIKE_INFO_PATH, order);
                    viewRentingBikeHandler.setHomeScreenHandler(this);
                    viewRentingBikeHandler.setBController(new ViewBikeController());
                    viewRentingBikeHandler.requestToViewRentingBike(this);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }
    }
    
    /**
     * display the home screen
     * @param searchString
     * @param order
     * @throws SQLException
     * @throws IOException
     */
    public void initHome(String searchString, Order order) throws SQLException, IOException {
        setBController(new HomeController());
        List medium = getBController().getAllStations();
        this.homeItems = new ArrayList<>();
        if (order != null) {
            for (Object object : medium) {
                Station station = (Station) object;
                StationHandler dock = new StationHandler(Configs.STATION_HOME_PATH, station, this, order);
                if (searchString == null) {
                    this.homeItems.add(dock);
                } else if (station.getName().toLowerCase().contains(searchString.toLowerCase()))
                    this.homeItems.add(dock);
            }
        } else {
            for (Object object : medium) {
                Station station = (Station) object;
                StationHandler dock = new StationHandler(Configs.STATION_HOME_PATH, station, this);
                if (searchString == null) {
                    this.homeItems.add(dock);
                } else if (station.getName().toLowerCase().contains(searchString.toLowerCase()))
                    this.homeItems.add(dock);
            }
        }
        home.setOnMouseClicked(e -> {
            addStationHome(this.homeItems);
        });
        addStationHome(this.homeItems);
    }

    /**
     * request to move to this screen
     * @param prevScreen
     * @throws SQLException
     */
    public void requestToReturnHome(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Home");
        show();
    }

    public void setImage() {
        return;
    }

    public Stage getStage() {
        return this.stage;
    }

    /**
     * add station to home
     * @param items
     */
    public void addStationHome(List items) {
        ArrayList homeItems = (ArrayList) ((ArrayList) items).clone();
        hboxHome.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while (!homeItems.isEmpty()) {
            int size = homeItems.size();
            hboxHome.getChildren().forEach(node -> {
                int vid = hboxHome.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                vBox.setSpacing(20);
                while (vBox.getChildren().size() <= size / 2 && !homeItems.isEmpty()) {
                    StationHandler station = (StationHandler) homeItems.get(0);
                    vBox.getChildren().add(station.getContent());
                    homeItems.remove(station);
                }
            });
            return;
        }
    }
}
