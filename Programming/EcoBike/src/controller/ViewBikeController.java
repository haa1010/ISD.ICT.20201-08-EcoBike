package controller;

import entity.bike.Bike;
import entity.order.Order;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimerTask;

public class ViewBikeController extends BaseController {
    private Bike bike;

    /**
     * get all information of the bike send to boundary
     *
     * @param bike
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


    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
    }
}
