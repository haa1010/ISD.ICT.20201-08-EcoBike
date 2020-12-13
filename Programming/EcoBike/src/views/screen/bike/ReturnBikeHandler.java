package views.screen.bike;

import controller.BaseController;
import controller.ReturnBikeController;
import entity.bike.Bike;
import entity.bike.StandardElectricBike;
import entity.transaction.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.util.logging.Logger;

public class ReturnBikeHandler extends BaseScreenHandler {

    @FXML
    private Text numberPlate;
    @FXML
    private Text barcode;
    @FXML
    private Text type;
    @FXML
    private Text battery;
    @FXML
    private Text remainingTime;
    @FXML
    private Text deposit;
    @FXML
    private Text rentedTime;
    @FXML
    private Text total;

    @FXML
    private ImageView bikeImage;

    @FXML
    private TextField cardHolder;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField bank;
    @FXML
    private TextField expiration;
    @FXML
    private TextField securityCode;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private Button editBtn;
    @FXML
    private GridPane batteryPercentageRow;

    private Bike bike;
    private Card card;

    private static Logger LOGGER = Utils.getLogger(BikeScreenHandler.class.getName());

    public ReturnBikeHandler(Stage stage, String screenPath, BaseController bController, Bike bike) throws IOException {
        super(stage, screenPath);
        setBController(bController);
        this.bike = bike;
        setBikeInfo();
        card = getBController().getCard();
        setCardInfo();
    }

    public ReturnBikeController getBController() {
        return (ReturnBikeController) super.getBController();
    }

    private void setBikeInfo() {
        numberPlate.setText(bike.getLicensePlate());
        barcode.setText(bike.getBarcode());
        type.setText(bike.getType());
        int deposit1 = (int)(bike.getValue() * 0.4);
        deposit.setText(Utils.getCurrencyFormat(deposit1));
        // set image from url
        String imageSource = bike.getUrlImage();
        boolean backgroundLoading = true;

        Image image = new Image(imageSource, backgroundLoading);
        bikeImage.setImage(image);

        if (bike instanceof StandardElectricBike) {
            int batteryPercentage = ((StandardElectricBike) bike).getBatteryPercentage();
            battery.setText(batteryPercentage + " %");
            remainingTime.setText(Utils.convertTime(((StandardElectricBike) bike).getRemainingTime()));
        } else {
//            batteryPercentageRow.setVisible(false);
        }
    }

    private void setCardInfo() {
        cardHolder.setText(card.getName());
        cardNumber.setText(card.getPin());
        bank.setText(card.getBank());
        expiration.setText(card.getExpiration());
        securityCode.setText("******");
    }
}
