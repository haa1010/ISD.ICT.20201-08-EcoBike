package views.screen.bike;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Utils;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
    private Label hours;
    @FXML
    private Label minutes;
    @FXML
    private Label seconds;
    private Order order;
    private LocalTime startAt;
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
    private void returnBike() {
        LOGGER.info("return bike button clicked");

    }

    public ViewRentingBike(Stage stage, String screenPath, Order order) throws IOException {

        super(stage, screenPath);
        this.bike = order.getRentedBike();
        this.order = order;
        setBikeInfo();
        int hour = (int) ChronoUnit.HOURS.between(order.getStart(), LocalTime.now());
        int minute = (int) ChronoUnit.MINUTES.between(order.getStart(), LocalTime.now());
        int second = (int) ChronoUnit.SECONDS.between(order.getStart(), LocalTime.now());
        LocalTime start = LocalTime.of(hour, minute, second);
        this.startAt = start;
        Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(1), ev -> {
                    setTimeCounting();
                }));
        animation.setCycleCount(Animation.INDEFINITE);
        this.animation = animation;
        this.getAnimation().play();


    }

    public Timeline getAnimation() {
        return animation;
    }

    public void setAnimation(Timeline animation) {


    }

    public ViewBikeController getBController() {
        return (ViewBikeController) super.getBController();
    }


    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public void setTimeCounting() {
        LocalTime newTime = this.getStartAt().plusSeconds(1);
        hours.setText(String.valueOf(newTime.getHour()));
        minutes.setText(String.valueOf(newTime.getMinute()));
        seconds.setText(String.valueOf(newTime.getSecond()));
        this.setStartAt(newTime);
    }


    /**
     * set bike info to view
     */
    public void setBikeInfo() {
        liscensePlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
        int deposit1 = order.getDeposit();
        liscensePlateTitle.setText(bike.getLicensePlate());
        amountUpToNow.setText(Utils.getCurrencyFormat(order.getTotalUpToNow()));
        deposit.setText(Utils.getCurrencyFormat(deposit1));
        // set image from url
        String imageSource = bike.getUrlImage();
        boolean backgroundLoading = true;
// The image is being loaded in the background
        Image image = new Image(imageSource, backgroundLoading);
        urlImage.setImage(image);

        if (bike instanceof StandardElectricBike) {
            batteryLabel.setText("Battery percentage");
            int battery = ((StandardElectricBike) bike).getBatteryPercentage();
            batteryPercentage.setText(battery + " %");
            remainingLabel.setText("Remaining time");
            remainingTime.setText(Utils.convertTime(((StandardElectricBike) bike).getRemainingTime()));
        } else {
            batteryLabel.setText("");
            remainingLabel.setText(" ");

        }


    }


    public void backToHome() {
        LOGGER.info("home button clicked");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String imageSource = "D:/itss/ISD.ICT.20201.08/Programming/EcoBike/assets/images/pauseIcon.png";
//
//        boolean backgroundLoading = true;
//// The image is being loaded in the background
//        Image image = new Image(imageSource, backgroundLoading);
//        pause.setImage(image);
    }
}
