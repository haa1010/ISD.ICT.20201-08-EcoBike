package views.screen.returnbike;

import controller.*;
import entity.order.Order;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike.ViewRentingBikeHandler;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SelectDockToReturnBikeScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML
    private VBox vboxDock2;

    @FXML
    private VBox vboxDock1;

    @FXML
    private HBox hboxDock;

    @FXML
    private ImageView home;
//
//    @FXML
//    private ImageView backBtn;

    private List docks;

    private Order order;

    private static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    public SelectDockToReturnBikeScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
        super(stage, screenPath);
        this.order = order;
        addDockSelection();

        home.setOnMouseClicked(event -> {
            try {
                backToHomeAfterRent(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SelectDockToReturnBikeController getBController() {
        return (SelectDockToReturnBikeController) super.getBController();
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            setBController(new SelectDockToReturnBikeController());

            List stations = getBController().getStationHasEmptyDock();
            this.docks = new ArrayList<>();
            for (Object object : stations) {
                Station s = (Station) object;
                DockItemReturnHandler d = new DockItemReturnHandler(Configs.RETURN_DOCK_ITEM_PATH, s, this);
                this.docks.add(d);
            }

        } catch (SQLException | IOException e) {
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void addDockSelection() {

        vboxDock1.getChildren().clear();
        vboxDock2.getChildren().clear();

        vboxDock1.setSpacing(15);
        vboxDock2.setSpacing(15);
        this.docks.forEach((dock) -> {
            int index = this.docks.indexOf(dock);
            if (index % 2 == 0)
                vboxDock1.getChildren().add(((DockItemReturnHandler) dock).getContent());
            else vboxDock2.getChildren().add(((DockItemReturnHandler) dock).getContent());
        });
        return;
    }

    public void dockChosen(Station s) throws IOException {
        ReturnBikeHandler returnBikeHandler = new ReturnBikeHandler(stage, Configs.RETURN_BIKE_SCREEN_PATH, new ReturnBikeController(), s, order);
        returnBikeHandler.show();
    }

    @FXML
    public void backToViewRentingBike() throws IOException, SQLException {
        ViewRentingBikeHandler viewRentingBikeHandler = new ViewRentingBikeHandler(stage, Configs.RENT_BIKE_INFO_PATH, order);
        viewRentingBikeHandler.setBController(new ViewBikeController());
        viewRentingBikeHandler.requestToViewRentingBike(new HomeScreenHandler(stage, Configs.HOME_SCREEN_PATH));
        viewRentingBikeHandler.show();
    }
}

