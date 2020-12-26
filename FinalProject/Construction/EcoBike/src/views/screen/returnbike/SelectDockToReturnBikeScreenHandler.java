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
import views.screen.bike.RentingBikeHandler;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * This class is to display the list of station for user to return bike
 * @author Do Minh Thong
 * @version 1.0
 */
public class SelectDockToReturnBikeScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML
    private VBox vboxDock2;

    @FXML
    private VBox vboxDock1;

    @FXML
    private HBox hboxDock;

    @FXML
    private ImageView home;

    private List docks;

    private Order order;

    private static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    /**
     * constructor
     * @param stage
     * @param screenPath
     * @param order
     * @throws IOException
     */
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

    /**
     * get the controller of this screen
     */
    public SelectDockToReturnBikeController getBController() {
        return (SelectDockToReturnBikeController) super.getBController();
    }


    @Override
    /**
     * initialize the content of the screen
     */
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
    
    /**
     * add the dock selection
     */
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
    
    /**
     * move to next screen when a dock is chosen
     * @param s
     * @throws IOException
     */
    public void dockChosen(Station s) throws IOException {
        ReturnBikeHandler returnBikeHandler = new ReturnBikeHandler(stage, Configs.RETURN_BIKE_SCREEN_PATH, new ReturnBikeController(), s, order);
        returnBikeHandler.show();
    }

    /**
     * back to previous screen
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void backToViewRentingBike() throws IOException, SQLException {
        RentingBikeHandler viewRentingBikeHandler = new RentingBikeHandler(stage, Configs.RENT_BIKE_INFO_PATH, order);
        viewRentingBikeHandler.setBController(new ViewBikeController());
        viewRentingBikeHandler.requestToViewRentingBike(new HomeScreenHandler(stage, Configs.HOME_SCREEN_PATH));
    }
    
    /**
     * request to move to this screen
     * @param prevScreen
     * @throws SQLException
     * @throws IOException
     */
    public void requestToSelectDock(BaseScreenHandler prevScreen) throws SQLException, IOException {
        setScreenTitle("List stations");
        setPreviousScreen(prevScreen);
        show();
    }
}

