package views.screen;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ReturnBikeController;
import entity.bike.Bike;
import entity.bike.StandardBike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * This class is the splash screen view handler
 * @author Do Minh Thong
 * @version 1.0
 */

public class SplashScreenHandler implements Initializable {

    @FXML
    ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}