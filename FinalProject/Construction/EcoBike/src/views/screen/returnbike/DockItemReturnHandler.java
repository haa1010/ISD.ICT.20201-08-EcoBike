package views.screen.returnbike;

import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utils.Utils;
import views.screen.FXMLScreenHandler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * This class is to display the station user chooses to return bike
 * @author Do Minh Thong
 * @version 1.0
 */
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

    /**
     * constructor
     * @param screenPath
     * @param station
     * @param home
     * @throws IOException
     * @throws SQLException
     */
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
    /**
     * set the station info
     * @throws SQLException
     */
    private void setStationInfo() throws SQLException {
        stationName.setText(station.getName());
        emptyDocks.setText(Integer.toString(station.getNumEmptyDockPoint()));
        stationAddress.setText(station.getAddress());
        setImage(stationImage, "assets/images/dock-img.png");
    }
}
