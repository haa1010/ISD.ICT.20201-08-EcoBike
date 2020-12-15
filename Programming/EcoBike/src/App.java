import controller.ReturnBikeController;
import entity.bike.Bike;
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
import views.screen.returnbike.ReturnBikeHandler;

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
                    //  Bike stde = new Bike().getBikeByBarcode("STEB01");

//                    Bike stde = new Bike().getBikeByBarcode("STEB01");
//                    ViewBikeController viewBikeController = new ViewBikeController(stde);
//                    BikeScreenHandler bikeScreenHandler = new BikeScreenHandler(primaryStage, Configs.BIKE_INFO_PATH, stde);
//                    bikeScreenHandler.setScreenTitle("View bike");
//                    bikeScreenHandler.setBController(viewBikeController);
//                    bikeScreenHandler.show();


                    //  StandardElectricBike twinBike = (StandardElectricBike) stde.getBikeByBarcode("STEB01");
//                    Order order = new Order(twinBike, LocalTime.now());
//                    ViewBikeController viewBikeController = new ViewBikeController(twinBike);
//                    ViewRentingBike viewRentingBike = new ViewRentingBike(primaryStage, Configs.RENT_BIKE_INFO, order);
//                    //  BikeScreenHandler bikeScreenHandler = new BikeScreenHandler(primaryStage, Configs.BIKE_INFO, stde.getBikeByBarcode("TB002"));
//                    viewRentingBike.setScreenTitle("View bike");
//                    viewRentingBike.setBController(viewBikeController);


//					HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_SCREEN_PATH);
//					homeHandler.setScreenTitle("Home Screen");
//					homeHandler.setImage();
//					homeHandler.show();
                    Bike bike;
                    try {
                        bike = new StandardElectricBike().getBikeById(3);
                        ReturnBikeHandler returnBikeHandler = new ReturnBikeHandler(primaryStage, Configs.RETURN_BIKE_SCREEN_PATH, new ReturnBikeController(), bike);
                        returnBikeHandler.show();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;
}


