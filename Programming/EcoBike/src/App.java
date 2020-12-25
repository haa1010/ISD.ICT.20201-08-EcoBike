import controller.ViewBikeController;
import entity.bike.Bike;
import entity.bike.StandardElectricBike;
import entity.order.Order;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Configs;
import views.screen.bike.BikeInformationHandler;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This class is the main class for our EcoBikeRental project
 * @author minhthong
 * @version 1.0
 */
public class App extends Application {

    @FXML
    ImageView logo;
    /**
     * This method is to start our program
     * @param Stage primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {

            // initialize the scene
            StackPane root = (StackPane) FXMLLoader.load(getClass().getResource(Configs.SPLASH_SCREEN_PATH));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Load splash screen with fade in effect
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            // Finish splash with fade out effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            // After fade in, start fade out
            fadeIn.play();
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            // After fade out, load actual content
            fadeOut.setOnFinished((e) -> {
                try {

                    //Show home screen after splash screen
                    try {
                        HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_PATH);
                        homeHandler.setScreenTitle("Home Screen");
                        homeHandler.setImage();
                        homeHandler.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


