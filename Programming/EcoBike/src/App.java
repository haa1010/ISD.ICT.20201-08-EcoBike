import controller.ReturnBikeController;
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
import views.screen.bike.BikeScreenHandler;
import views.screen.bike.ViewRentingBike;
import views.screen.home.HomeScreenHandler;
import views.screen.returnbike.SelectDockToReturnBikeScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

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

                    //Show home screen after splash screen
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

//                    //   Test rent bike
//                    Bike stde = new StandardElectricBike().getBikeByBarcode("STEB01");
//
//                    Order order = new Order(stde, LocalDateTime.now().minusHours(25));
//                    ViewRentingBike viewRentingBike = new ViewRentingBike(primaryStage, Configs.RENT_BIKE_INFO_PATH, order);
//                    viewRentingBike.setBController(new ViewBikeController());
//                    viewRentingBike.requestToViewRentingBike(new HomeScreenHandler(primaryStage, Configs.HOME_SCREEN_PATH));
//                    viewRentingBike.show();

                    // test return bike
//                     Bike bike;
//                     try {
//                         bike = new StandardElectricBike().getBikeById(3);
//                         Order order = new Order(bike, LocalDateTime.now().minusHours(28));
//                         SelectDockToReturnBikeScreenHandler d = new SelectDockToReturnBikeScreenHandler(primaryStage, Configs.SELECT_DOCK_TO_RETURN_BIKE_PATH,  order);
//                     d.show();
//                     } catch (SQLException throwables) {
//                         throwables.printStackTrace();
//                     }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


