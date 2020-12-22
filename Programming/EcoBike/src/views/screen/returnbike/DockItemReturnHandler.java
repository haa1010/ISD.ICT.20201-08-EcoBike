package views.screen.returnbike;

import common.exception.MediaNotAvailableException;
import controller.DockItemReturnController;
import controller.ReturnBikeController;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DockItemReturnHandler extends FXMLScreenHandler {

    @FXML
    protected ImageView stationImage;

    @FXML
    protected Text stationName;

    @FXML
    protected Text emptyDocks;

    @FXML
    protected Text stationAddress;


    @FXML
    protected Button returnBtn;

    private Station station;
    private SelectDockToReturnBikeScreenHandler home;



    private static Logger LOGGER = Utils.getLogger(DockItemReturnHandler.class.getName());

    public DockItemReturnHandler( String screenPath, Station station, SelectDockToReturnBikeScreenHandler home) throws IOException, SQLException {
        super(screenPath);
        this.station = station;
        this.home = home;
        returnBtn.setOnMouseClicked(event -> {
            try {
                LOGGER.info("Choose " + station.getName() + " to return");
                home.dockChosen(station);
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
        stationName.setText(station.getName());
        emptyDocks.setText(Integer.toString(station.getNumEmptyDockPoint()));
        stationAddress.setText(station.getAddress());
        setImage(stationImage, "assets/images/dock-img.png");
    }

}
