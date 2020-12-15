import controller.BaseController;
import controller.ReturnBikeController;
import controller.ViewBikeController;
import entity.bike.Bike;
import entity.bike.StandardBike;
import entity.bike.StandardElectricBike;
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
import views.screen.bike.BikeScreenHandler;
import views.screen.bike.ReturnBikeHandler;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {

    @FXML
    ImageView logo;

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
//                    StandardElectricBike stde = new StandardElectricBike();
//                    ViewBikeController viewBikeController = new ViewBikeController(stde.getBikeByBarcode("STEB01"));
//                    BikeScreenHandler bikeScreenHandler = new BikeScreenHandler(primaryStage, Configs.BIKE_INFO, stde.getBikeByBarcode("STEB01"));
//                    bikeScreenHandler.setScreenTitle("View bike");
//                    bikeScreenHandler.setBController(viewBikeController);
//                    bikeScreenHandler.show();

                    try {
                        HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_PATH);
                        homeHandler.setScreenTitle("Home Screen");
                        homeHandler.setImage();
                        homeHandler.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
//                    Bike bike;
//                    try {
//                        bike = new Bike().getBikeById(3);
//                        ReturnBikeHandler returnBikeHandler = new ReturnBikeHandler(primaryStage, Configs.RETURN_BIKE_SCREEN_PATH, new ReturnBikeController(), bike);
//                        returnBikeHandler.show();
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
