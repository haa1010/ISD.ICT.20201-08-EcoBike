package views.screen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Logger;

import controller.BaseController;
import entity.order.Order;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.bike.BikeScreenHandler;
import views.screen.home.HomeScreenHandler;

public class BaseScreenHandler extends FXMLScreenHandler {

    private Scene scene;
    private BaseScreenHandler prev;
    protected final Stage stage;
    protected HomeScreenHandler homeScreenHandler;
    protected Hashtable<String, String> messages;
    private BaseController bController;

    public Logger LOGGER = Utils.getLogger(BikeScreenHandler.class.getName());


    public void backToHomeBeforeRent() throws IOException, SQLException {
        LOGGER.info("Home button clicked");
        HomeScreenHandler homeHandler = new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH);
        homeHandler.requestToReturnHome(this);
    }

    public void backToHomeAfterRent(Order order) throws IOException, SQLException {
        LOGGER.info("Home button clicked");
        HomeScreenHandler homeHandler = new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH, order);
        homeHandler.requestToReturnHome(this);
    }

    private BaseScreenHandler(String screenPath) throws IOException {
        super(screenPath);
        this.stage = new Stage();
    }

    public void setPreviousScreen(BaseScreenHandler prev) {
        this.prev = prev;
    }

    public BaseScreenHandler getPreviousScreen() {
        return this.prev;
    }

    public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
        super(screenPath);
        this.stage = stage;
    }

    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    public void setBController(BaseController bController) {
        this.bController = bController;
    }

    public BaseController getBController() {
        return this.bController;
    }

    public void forward(Hashtable messages) {
        this.messages = messages;
    }

    public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
        this.homeScreenHandler = HomeScreenHandler;
    }
}
