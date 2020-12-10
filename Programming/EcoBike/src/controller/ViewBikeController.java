package controller;

import entity.bike.Bike;
import entity.order.Order;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewBikeController extends BaseController {
    private Bike bike;

    /**
     * get all information of the bike send to boundary
     *
     * @param Bike
     */
    public ViewBikeController(Bike bike) {
        this.bike = bike;
    }

    public Bike viewBikeInfoById(int id) throws SQLException {
        Bike bike = new Bike();
        return bike.getBikeById(id);

    }

    public Bike viewBikeInfoByBarcode(String barcode) throws SQLException {
        Bike bike = new Bike();
        return bike.getBikeByBarcode(barcode);
    }

    public Bike viewRentingBikeInfo() throws SQLException {
        return null;
    }

    public void rentBike() {

    }

    public void returnBike() {

    }
}
