package views.screen.popup;

import java.io.IOException;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.station.StationScreenHandler;

/**
 * This class is to display the pop-up in home screen
 * @author Do Minh Thong
 * @version 1.0
 */

public class PopupHomeScreen extends BaseScreenHandler{

    @FXML
    Label message;

    @FXML
    Label address;

    @FXML
    Label distance;

    @FXML
    Label estimatedTime;

    @FXML
    /**
     * close the pop-up and back to home screen
     * @throws IOException
     * @throws SQLException
     */
    private void back() throws IOException, SQLException {
        LOGGER.info("Back button clicked");
        close(0);
    }

    /**
     * constructor
     * @param stage
     * @throws IOException
     */
    public PopupHomeScreen(Stage stage) throws IOException{
        super(stage, Configs.POPUP_HOME_PATH);
    }
    
    /**
     * style the pop-up
     * @param undecorated
     * @return
     * @throws IOException
     */
    private static PopupHomeScreen popup(Boolean undecorated) throws IOException{
        PopupHomeScreen popup = new PopupHomeScreen(new Stage());
        if (undecorated) popup.stage.initStyle(StageStyle.UNDECORATED);
        return popup;
    }

    /**
     * show pop-up
     * @throws IOException
     */
    public static void showPopup() throws IOException{
        popup(true).show(false);
    }
    
    /**
     * show and auto close after sometime
     * @param autoclose
     */
    public void show(Boolean autoclose) {
        super.show();
        if (autoclose) close(0.8);
    }
    
    /**
     * show and close after sometime
     * @param time
     */
    public void show(double time) {
        super.show();
        close(time);
    }
    
    /**
     * close the pop-up after sometime
     * @param time
     */
    public void close(double time){
        PauseTransition delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
    }
}
