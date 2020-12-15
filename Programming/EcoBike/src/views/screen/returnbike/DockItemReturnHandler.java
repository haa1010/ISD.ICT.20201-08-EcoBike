package views.screen.returnbike;

import common.exception.MediaNotAvailableException;
import controller.DockItemReturnController;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DockItemReturnHandler extends BaseScreenHandler {

    @FXML
    protected ImageView stationImage;

    @FXML
    protected Label stationName;

    @FXML
    protected Label emptyDocks;

    @FXML
    protected Label stationAddress;


    @FXML
    protected Button returnBtn;

    private Station station;
    private SelectDockToReturnBikeScreenHandler home;

    private static Logger LOGGER = Utils.getLogger(DockItemReturnHandler.class.getName());


    public DockItemReturnController getBController() {
        return (DockItemReturnController) super.getBController();
    }

    public DockItemReturnHandler(Stage stage, String screenPath, Station station, SelectDockToReturnBikeScreenHandler home) throws SQLException, IOException {
        super(stage, screenPath);
        this.station = station;
        this.home = home;
        returnBtn.setOnMouseClicked(event -> {
            try {
                LOGGER.info("Choose " + station.getName() + " to return");
                // update db
            } catch (Exception e) {
                LOGGER.severe("Cannot return bike to this station right now! ");
                e.printStackTrace();
            }
        });
        setStationInfo();
    }

    public Station getStation() {
        return station;
    }

    private void setStationInfo() throws SQLException {
        // set the cover image of media
        File file = new File("../../../assets/images/dock-img.png");
        Image image = new Image(file.toURI().toString());
        stationImage.setImage(image);

        stationName.setText(station.getName());
        emptyDocks.setText(Integer.toString(station.getNumEmptyDockPoint()));
        stationAddress.setText(station.getAddress());

        setImage(stationImage, "../../../assets/images/dock-img.png");
    }

}
