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
    void backToHome(MouseEvent event) throws IOException, SQLException {
        
    }

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

        BaseScreenHandler rent = new RentBikeScreenHandler(this.stage, Configs.RENT_BIKE_PATH, rentByBarcode);
        rent.setPreviousScreen(this);
        rent.setHomeScreenHandler(homeScreenHandler);
        rent.setScreenTitle("Rent Bike Screen");
        rent.setBController(new RentBikeController());
        rent.show();
    }

    public void requestToViewBarcode(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Rent bike");
        show();
    }

}
