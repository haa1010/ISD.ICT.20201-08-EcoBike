package views.screen.returnbike;

import controller.*;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SelectDockToReturnBikeScreenHandler extends BaseScreenHandler  implements Initializable {
    @FXML
    private VBox vboxDock2;

    @FXML
    private VBox vboxDock1;

    @FXML
    private HBox hboxDock;

    private List docks;

    private static Logger LOGGER = Utils.getLogger(ReturnBikeHandler.class.getName());

    public SelectDockToReturnBikeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public SelectDockToReturnBikeController getBController() {
        return (SelectDockToReturnBikeController) super.getBController();
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            setBController(new SelectDockToReturnBikeController());

            List stations = getBController().getStationHasEmptyDock();
            this.docks = new ArrayList<>();
            for (Object object : stations) {
                Station s = (Station)object;
                DockItemReturnHandler d = new DockItemReturnHandler(stage, Configs.RETURN_DOCK_ITEM_PATH, s, this);
                this.docks.add(d);
            }

        }catch (SQLException | IOException e){
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }

        addDockSelection();
    }

    public void addDockSelection(){
        ArrayList dockItems = (ArrayList)((ArrayList) docks).clone();

        hboxDock.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });

        while(!dockItems.isEmpty()){
            hboxDock.getChildren().forEach(node -> {
                int vid = hboxDock.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                while(vBox.getChildren().size()<3 && !dockItems.isEmpty()){
                    DockItemReturnHandler dock = (DockItemReturnHandler) dockItems.get(0);
                    vBox.getChildren().add(dock.getContent());
                    dockItems.remove(dock);
                }
            });
            return;
        }
    }
}
