package views.screen.bike;

import controller.ReturnBikeController;
import controller.ViewBikeController;
import entity.bike.Bike;
import entity.bike.StandardElectricBike;
import entity.order.Order;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.returnbike.SelectDockToReturnBikeScreenHandler;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ViewRentingBike extends BaseScreenHandler implements Initializable {
    @FXML
    private Label liscensePlate;
    @FXML
    private Text amountUpToNow;
    @FXML
    private Label barcode;
    @FXML
    private Label type;
    @FXML
    private Label liscensePlateTitle;
    @FXML
    private Label deposit;
    @FXML
    private Label batteryPercentage;
    @FXML
    private Label batteryLabel;
    @FXML
    private Label remainingLabel;
    @FXML
    private Label remainingTime;
    @FXML
    private Bike bike;
    @FXML
    private ImageView urlImage;
    @FXML
    private Pane bikeInfo;
    @FXML
    private Label hours;
    @FXML
    private Label minutes;
    @FXML
    private Label seconds;
    private Order order;
    private Integer startAt;
    @FXML
    private ImageView pause;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private Timeline animation;
    private boolean flag;

    @FXML
    private void pauseCountingTime(MouseEvent event) throws IOException {
        if (animation != null) {
            if (!isFlag()) {
                animation.pause();


                //  Image icon16 = new Image("../../../assets/images/playIcon.png");

                String imagePath = "file:///D:/itss/ISD.ICT.20201.08/Programming/EcoBike/assets/images/playIcon.png";
                Image image = new Image(imagePath);
                pause.setImage(image);
                setFlag(true);

            } else {
                animation.play();
                String imageSource = "file:///D:/itss/ISD.ICT.20201.08/Programming/EcoBike/assets/images/pauseIcon.png";

                boolean backgroundLoading = true;
// The image is being loaded in the background
                Image image = new Image(imageSource, backgroundLoading);
                pause.setImage(image);
                setFlag(false);
            }
        }

    }

    @FXML
    private void returnBike() throws IOException {
        LOGGER.info("return bike button clicked");

        SelectDockToReturnBikeScreenHandler returnBikeScreenHandler = new SelectDockToReturnBikeScreenHandler(this.stage, Configs.SELECT_DOCK_TO_RETURN_BIKE_PATH, bike);
        returnBikeScreenHandler.setBController(new ReturnBikeController());
        returnBikeScreenHandler.show();

    }

    public ViewRentingBike(Stage stage, String screenPath, Order order) throws IOException {

        super(stage, screenPath);
        this.bike = order.getRentedBike();
        this.order = order;

    }

    public void setStartAt() {
        this.startAt = getBController().calculateAmountMinutes(order.getStart());

    }

    public Timeline getAnimation() {
        return animation;
    }

    public void setAnimation(Timeline animation) {


    }

    public ViewBikeController getBController() {
        return (ViewBikeController) super.getBController();
    }


    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public void setTimeCounting() {
        HashMap<String, Integer> time = getBController().counting(this.getStartAt());
        hours.setText(String.valueOf(time.get("hour")));
        minutes.setText(String.valueOf(time.get("minute")));
        seconds.setText(String.valueOf(time.get("second")));
        this.setStartAt(time.get("newAmount"));
    }


    /**
     * set bike info to view
     */
    public void setBikeInfo() throws IOException {
        Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(1), ev -> {
                    setTimeCounting();
                }));
        animation.setCycleCount(Animation.INDEFINITE);
        this.animation = animation;
        this.getAnimation().play();

        liscensePlateTitle.setText(bike.getLicensePlate());
        // set image from url
        String imageSource = bike.getUrlImage();
        boolean backgroundLoading = true;
// The image is being loaded in the background
        Image image = new Image(imageSource, backgroundLoading);
        urlImage.setImage(image);
        BikeInfo bikeInfoItems = new BikeInfo(Configs.BIKE_INFO, this.bike, false);
        bikeInfo.getChildren().add(bikeInfoItems.getContent());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
