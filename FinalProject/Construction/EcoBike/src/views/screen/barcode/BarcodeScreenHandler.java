package views.screen.barcode;

/**
 * This class is the base screen view handler
 * @author Pham Nhat Linh
 * @version 1.0
 */

import java.io.IOException;
import java.sql.SQLException;

import controller.RentBikeController;
import entity.bike.Bike;
import javafx.event.ActionEvent;
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
	
	/**
	 * constructor
	 * @param stage
	 * @param screenPath
	 * @throws IOException
	 */
    public BarcodeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }
    
    /**
     * get the controller of this screen
     */
    public RentBikeController getBController() {
        return (RentBikeController) super.getBController();
    }
    
    /**
     * display error to the screen
     * @param error
     */
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

    /**
     * move to the rent bike screen
     * @param event
     * @throws Exception
     */
    @FXML
    void viewRentBikeScreen(MouseEvent event) throws Exception {
        try {
            String barcode = barcodeInput.getText();
            Bike rentByBarcode = getBController().validateBarcodeBike(barcode);
            RentBikeScreenHandler rent = new RentBikeScreenHandler(this.stage, Configs.RENT_BIKE_PATH, rentByBarcode);
            rent.setBikeInfo();
            rent.requestToViewRentBike(this, homeScreenHandler);
        } catch (Exception e) {
            notifyError(e.getMessage());
        }

    }
    
    /**
     * move to the barcode screen
     * @param prevScreen
     * @throws SQLException
     */
    public void requestToViewBarcode(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Rent bike");
        show();
    }

}
