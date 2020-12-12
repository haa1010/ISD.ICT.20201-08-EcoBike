package utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.stream.IntStream;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * @author nguyenlm Contains the configs for AIMS Project
 */
public class Configs {

    // api constants
    public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/121319_group8_2020";
    public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
    public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
    public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";
    public static final String appCode = "A1SRyiBqj/E=";
    public static final String hashCode = "BtNH8J4Tl/I=";

    // demo data
    public static final String POST_DATA = "{"
            + " \"secretKey\": \"BtNH8J4Tl/I=\" ,"
            + " \"transaction\": {"
            + " \"command\": \"pay\" ,"
            + " \"cardCode\": \"121319_group8_2020\" ,"
            + " \"owner\": \"Group 8\" ,"
            + " \"cvvCode\": \"128\" ,"
            + " \"dateExpried\": \"1125\" ,"
            + " \"transactionContent\": \"Pei debt\" ,"
            + " \"amount\": 50000 "
            + "}"
            + "}";
    public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMTg2MDlfZ3JvdXAxXzIwMjAiLCJpYXQiOjE1OTkxMTk5NDl9.y81pBkM0pVn31YDPFwMGXXkQRKW5RaPIJ5WW5r9OW-Y";

    // database Configs
    public static final String DB_NAME = "ecoBikeRental";
    public static final String DB_USERNAME = System.getenv("DB_USERNAME");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public static String CURRENCY = "VND";
    public static float PERCENT_VAT = 10;

    // static resource
    public static final String IMAGE_PATH = "assets/images";
    public static final String INVOICE_SCREEN_PATH = "/views/fxml/invoice.fxml";
    public static final String INVOICE_MEDIA_SCREEN_PATH = "/views/fxml/media_invoice.fxml";
    public static final String PAYMENT_SCREEN_PATH = "/views/fxml/payment.fxml";
    public static final String RESULT_SCREEN_PATH = "/views/fxml/result.fxml";
    public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash.fxml";
    public static final String POPUP_PATH = "/views/fxml/popup.fxml";
    public static final String RETURN_BIKE_SCREEN_PATH = "/views/fxml/return_bike.fxml";
    public static final String BIKE_INFO = "/views/fxml/bike.fxml";
    public static final String RENT_BIKE_INFO = "/views/fxml/rentingBike.fxml";
    public static final String BARCODER_SCREEN_PATH = "/views/fxml/barcode.fxml";
    public static final String RENT_BIKE = "/views/fxml/rent_bike.fxml";
    public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);
    // credit card expires on the last day of the month of that year
    public static final String MONTH[] = { 
    		"January", "Febuary", "March", "April",
    		"May", "June", "July", "August",
    		"September", "October", "November", "December"
    };
    public static final String YEARS[] = Arrays.stream(IntStream.rangeClosed(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.YEAR)+5).toArray()).mapToObj(String::valueOf).toArray(String[]::new);


}
