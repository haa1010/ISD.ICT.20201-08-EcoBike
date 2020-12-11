package utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * @author nguyenlm Contains the configs for AIMS Project
 */
public class Configs {

    // api constants
    public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
    public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
    public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
    public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";

    // demo data
    public static final String POST_DATA = "{"
            + " \"secretKey\": \"BUXj/7/gHHI=\" ,"
            + " \"transaction\": {"
            + " \"command\": \"pay\" ,"
            + " \"cardCode\": \"118609_group1_2020\" ,"
            + " \"owner\": \"Group 1\" ,"
            + " \"cvvCode\": \"185\" ,"
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
    public static final String BIKE_INFO = "/views/fxml/bike.fxml";
    public static final String RENT_BIKE_INFO = "/views/fxml/rentingBike.fxml";
    public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);


}
