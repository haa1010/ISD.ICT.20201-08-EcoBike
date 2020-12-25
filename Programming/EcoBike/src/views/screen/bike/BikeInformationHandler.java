package views.screen.bike;

/**
 * This class is to display the bike info screen
 * @author Duong Thi Hue
 * @version 1.0
 */

import controller.RentBikeController;
import controller.ViewBikeController;
import entity.bike.*;
import entity.order.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.rentbike.RentBikeScreenHandler;
import views.screen.station.StationScreenHandler;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class BikeInformationHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Label liscensePlateTitle;
    @FXML
    private Pane bikeInfo;
    private Bike bike;
    @FXML

    private ImageView urlImage;
    @FXML
    private Button canRent;
    private Order order;
    private static Logger LOGGER = Utils.getLogger(BikeInformationHandler.class.getName());
    private HomeScreenHandler homeScreenHandler;

    /**
     * constructor, when the user not renting any bike
     * @param stage
     * @param screenPath
     * @throws IOException
     * @throws SQLException
     */
    public BikeInformationHandler(Stage stage, String screenPath) throws IOException, SQLException {
        super(stage, screenPath);
    }

    /**
     * constructor, when the user is using a rented bike
     * @param stage
     * @param screenPath
     * @param order
     * @throws IOException
     * @throws SQLException
     */
    public BikeInformationHandler(Stage stage, String screenPath, Order order) throws IOException, SQLException {
        super(stage, screenPath);
        this.order = order;
    }

    /**
     * get the controller of this screen
     */
    public ViewBikeController getBController() {
        return (ViewBikeController) super.getBController();
    }
    
    /**
     * request to view this screen, when the user is not using any bike
     * @param prevScreen
     * @param id
     * @param type
     * @param order
     * @throws SQLException
     * @throws IOException
     */
    public void requestToViewBike(BaseScreenHandler prevScreen, int id, String type, Order order) throws SQLException, IOException {
        this.setPreviousScreen(prevScreen);
        setBikeInfo(id, type);
        setCantRent();
        this.order = order;
        setScreenTitle("View bike");
        show();
    }

    /**
     * request to view this screen, when the user is using a rented bike
     * @param prevScreen
     * @param id
     * @param type
     * @throws SQLException
     * @throws IOException
     */
    public void requestToViewBike(BaseScreenHandler prevScreen, int id, String type) throws SQLException, IOException {
        this.setPreviousScreen(prevScreen);
        setBikeInfo(id, type);
        setCanRent();
        this.order = null;
        setScreenTitle("View bike");
        show();
    }

    /**
     * set the bike info in the screen
     * @param id
     * @param type
     * @throws IOException
     * @throws SQLException
     */
    public void setBikeInfo(int id, String type) throws IOException, SQLException {
        this.bike = getBController().setBike(id, type);
        liscensePlateTitle.setText(bike.getLicensePlate());
        // set image from url
        setImage(urlImage, bike.getUrlImage());
        BikeInfo bikeInfoItems = new BikeInfo(Configs.BIKE_INFO, this.bike, true);
        bikeInfo.getChildren().add(bikeInfoItems.getContent());
    }

    /**
     * back to home
     * @throws IOException
     * @throws SQLException
     */
    public void backToHomie() throws IOException, SQLException {
        if (this.order != null) {
            backToHomeAfterRent(order);
        } else {
            backToHome();
        }
    }

    /**
     * if we cannot rent bike because we are renting another bike, then the button is set to disable
     * @throws SQLException
     */
    public void setCantRent() throws SQLException {
        canRent.setDisable(true);

    }
    
    /**
     * if we can rent bike, then the button is set
     * @throws SQLException
     */
    public void setCanRent() throws SQLException {

        if (getBController().bikeIsRenting(bike.getId())) canRent.setDisable(true);
        else canRent.setDisable(false);

    }

    /**
     * back to previous screen
     */
    @FXML
    private void cancelViewBike() throws IOException, SQLException {
        LOGGER.info("Cancel button clicked");
        if (this.getPreviousScreen() instanceof HomeScreenHandler) {
            if (this.order == null) backToHome();
            else backToHomeAfterRent(this.order);
        } else {
            this.getPreviousScreen().show();

        }
    }

    /**
     * switch to rentBike screen
     */
    @FXML
    private void rentBike() throws IOException {
        LOGGER.info("Rent bike button clicked");
        RentBikeScreenHandler rentBike = new RentBikeScreenHandler(this.stage, Configs.RENT_BIKE_PATH, bike);
        rentBike.setBController(new RentBikeController());
        rentBike.setBikeInfo();
        rentBike.requestToViewRentBike(this, homeScreenHandler);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * move to this screen
     * @param prevScreen
     * @throws SQLException
     */
    public void requestToViewBike(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Bike");
        show();
    }
}
