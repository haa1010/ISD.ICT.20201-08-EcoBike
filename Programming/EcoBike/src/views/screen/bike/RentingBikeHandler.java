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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * This class is to display the info of the renting bike
 * @author Duong Thi Hue
 * @version 1.0
 */
public class RentingBikeHandler extends BaseScreenHandler implements Initializable {
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
    @FXML
    private ImageView home;


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private Timeline animation;
    private boolean flag;

    @FXML
    /**
     * pause and continue renting time
     * @param event
     * @throws IOException
     */
    private void pauseCountingTime(MouseEvent event) throws IOException {
        if (animation != null) {
            if (!isFlag()) {
                animation.pause();
                setImage(pause, "assets/images/playIcon.png");
                setFlag(true);

            } else {
                animation.play();
                String imageSource = "assets/images/pauseIcon.png";
                setImage(pause, "assets/images/pauseIcon.png");
                setFlag(false);
            }
        }

    }
    
    /**
     * request to move to this screen
     * @param prevScreen
     * @throws SQLException
     * @throws IOException
     */
    public void requestToViewRentingBike(BaseScreenHandler prevScreen) throws SQLException, IOException {
        setStartAt();
        setBikeInfo();
        setScreenTitle("View renting bike");
        setPreviousScreen(prevScreen);
        setScreenTitle("View bike");
        show();
    }


    @FXML
    /**
     * request to move to select dock to return bike screen
     * @throws IOException
     */
    private void returnBike() throws IOException {
        LOGGER.info("return bike button clicked");

        SelectDockToReturnBikeScreenHandler selectDock = new SelectDockToReturnBikeScreenHandler(this.stage, Configs.SELECT_DOCK_TO_RETURN_BIKE_PATH, order);

        selectDock.show();

    }

    /**
     * constructor
     * @param stage
     * @param screenPath
     * @param order
     * @throws IOException
     */
    public RentingBikeHandler(Stage stage, String screenPath, Order order) throws IOException {
        super(stage, screenPath);
        this.bike = order.getRentedBike();
        this.order = order;
        home.setOnMouseClicked(event -> {
            try {
                backToHomeAfterRent(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setStartAt() {
        this.startAt = getBController().calculateAmountMinutes(order.getStart());
    }

    public Timeline getAnimation() {
        return animation;
    }

    public void setAnimation(Timeline animation) {
    }

    /**
     * back to home
     */
    public void backToHome() throws IOException, SQLException {
        backToHomeAfterRent(order);
    }

    /**
     * get the controller of this screen
     */
    public ViewBikeController getBController() {
        return (ViewBikeController) super.getBController();
    }


    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    /**
     * set time counting
     */
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
        setImage(urlImage, bike.getUrlImage());
        BikeInfo bikeInfoItems = new BikeInfo(Configs.BIKE_INFO, this.bike, false);
        bikeInfo.getChildren().add(bikeInfoItems.getContent());
        amountUpToNow.setText(Utils.getCurrencyFormat(new ReturnBikeController().calculateAmount(bike.getCoefficient(), order.getStart())));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
