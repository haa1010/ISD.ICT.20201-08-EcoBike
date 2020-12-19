package views.screen.home;

import controller.HomeController;
import controller.RentBikeController;
import controller.ViewStationController;
import entity.station.Station;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.barcode.BarcodeScreenHandler;
import views.screen.station.StationScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Logger;


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

    private List homeItems;

    private String searchString = searchInput.getText();

    @FXML
    public void onEnter(ActionEvent ae){
        searchString = searchInput.getText();
        addStationHome(this.homeItems);
    }

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new HomeController());
        try {
            List medium = getBController().getAllStations();
            this.homeItems = new ArrayList<>();
            for (Object object : medium) {
                Station station = (Station) object;
                StationHandler dock = new StationHandler(Configs.STATION_HOME_PATH, station, this);
                if (searchString == null) {
                    this.homeItems.add(dock);
                } else if (station.getName().contains(searchString))
                    this.homeItems.add(dock);
            }
        } catch (SQLException | IOException e) {
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }

        home.setOnMouseClicked(e -> {
            addStationHome(this.homeItems);
        });

        rentBikeButton.setOnMouseClicked(e -> {
            BarcodeScreenHandler barcodeScreen;
            try {
                barcodeScreen = new BarcodeScreenHandler(this.stage, Configs.BARCODER_SCREEN_PATH);
                barcodeScreen.setHomeScreenHandler(this);
                barcodeScreen.setBController(new RentBikeController());
                barcodeScreen.requestToViewBarcode(this);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        addStationHome(this.homeItems);
    }

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
                while(vBox.getChildren().size() < size / 2 && !homeItems.isEmpty()){
                    StationHandler station = (StationHandler) homeItems.get(0);
                    vBox.getChildren().add(station.getContent());
                    homeItems.remove(station);
                }
            });
            return;
        }
    }
}
