package views.screen.barcode;

import java.io.IOException;
import java.sql.SQLException;

import controller.RentBikeController;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.rentbike.RentBikeScreenHandler;

public class BarcodeScreenHandler extends BaseScreenHandler {

    public BarcodeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public RentBikeController getBController() {
        return (RentBikeController) super.getBController();
    }

    void notifyError(String error) {
        this.displayError.setText(error);
    }

    @FXML
    private ImageView home;

    @FXML
    private Button btnHomeScreen;

    @FXML
    private Button btnRentBikeScreen;

    @FXML
    private TextField barcodeInput;

    @FXML
    private Label displayError;


    @FXML
    void viewRentBikeScreen(MouseEvent event) throws Exception {
        String barcode = barcodeInput.getText();
        Bike rentByBarcode;
        try {
            rentByBarcode = getBController().validateBarcodeBike(barcode);

        } catch (Exception e) {
            notifyError(e.getMessage());
            throw new Exception();
        }

        RentBikeScreenHandler rent = new RentBikeScreenHandler(this.stage, Configs.RENT_BIKE_PATH, rentByBarcode);
        rent.setBikeInfo();
        rent.setBController(new RentBikeController());
        rent.requestToViewRentBike(this, homeScreenHandler);
    }

    public void requestToViewBarcode(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Rent bike");
        show();
    }

}
