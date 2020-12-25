package utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


/**
 * @author nguyenlm Contains the configs for AIMS Project
 */
public class Configs {

    // api constants
    public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
    public static final String appCode = "A1SRyiBqj/E=";

    // database Configs
    public static final String DB_NAME = "ecoBikeRental";
    public static final String DB_URL = "jdbc:mysql://remotemysql.com/vaftyLWDOZ";
    public static final String DB_USERNAME = "vaftyLWDOZ";
    public static final String DB_PASSWORD = "9db0uNrKek";

    // static resource
    public static final String RESULT_SCREEN_PATH = "/views/fxml/result.fxml";
    public static final String PAYMENT_SCREEN_PATH = "/views/fxml/payment.fxml";
    public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash.fxml";
    public static final String HOME_PATH = "/views/fxml/home.fxml";
    public static final String STATION_HOME_PATH = "/views/fxml/station_home.fxml";
    public static final String POPUP_HOME_PATH = "/views/fxml/popup_home.fxml";
    public static final String STATION_PATH = "/views/fxml/station.fxml";
    public static final String BIKE_STATION_PATH = "/views/fxml/bike_station.fxml";
    public static final String TRANSACTION_ERROR_SCREEN_PATH = "/views/fxml/transaction_error.fxml";
    public static final String RETURN_BIKE_SCREEN_PATH = "/views/fxml/return_bike.fxml";
    public static final String BIKE_INFO_PATH = "/views/fxml/bike.fxml";
    public static final String RENT_BIKE_INFO_PATH = "/views/fxml/rentingBike.fxml";
    public static final String BARCODE_SCREEN_PATH = "/views/fxml/barcode_screen.fxml";
    public static final String RENT_BIKE_PATH = "/views/fxml/rent_bike.fxml";
    public static final String HOME_SCREEN_PATH = "/views/fxml/home.fxml";
    public static final String BIKE_INFO = "/views/fxml/bike_info.fxml";
    public static final String RETURN_DOCK_ITEM_PATH = "/views/fxml/return_dock_item.fxml";
    public static final String SELECT_DOCK_TO_RETURN_BIKE_PATH = "/views/fxml/select_dock_to_return_bike.fxml";


    public static final Map<String, String> errorCodes = new HashMap<String, String>() {{
        put("00", "Transaction successfully");
        put("01", "Invalid card");
        put("02", "Insufficient account balance");
        put("03", "Internal Server Error");
        put("04", "Fraud transaction");
        put("05", "Missing transaction information");
        put("06", "Missing version information");
        put("07", "Invalid amount");
    }};
}
