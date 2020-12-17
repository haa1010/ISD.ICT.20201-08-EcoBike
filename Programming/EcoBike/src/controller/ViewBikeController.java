package controller;

import entity.bike.*;
import entity.order.Order;
import entity.station.Station;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.TimerTask;

public class ViewBikeController extends BaseController {

    public HashMap counting(int amount) {
        int hour = amount / 3600;
        int minute = (amount - hour * 3600) / 60;
        int second = amount - hour * 3600 - minute * 60;
        if (second < 59) second += 1;
        else {
            second = 0;
            if (minute < 59) minute += 1;
            else {
                hour += 1;
                minute = 0;
            }
        }
        HashMap<String, Integer> time = new HashMap<>();
        time.put("hour", hour);
        time.put("minute", minute);
        time.put("second", second);
        time.put("newAmount", amount + 1);
        return time;
    }

    public int calculateAmountMinutes(LocalDateTime start) {
        int hour = (int) ChronoUnit.HOURS.between(start, LocalDateTime.now());
        int minute = (int) ChronoUnit.MINUTES.between(start, LocalDateTime.now());
        int second = (int) ChronoUnit.SECONDS.between(start, LocalDateTime.now());
        return hour * 3600 + minute * 60 + second;
    }

    public Bike setBike(int id, String type) throws SQLException {
        if (type.equals("Standard electric bike")) {
            return new StandardElectricBike().getBikeById(id);
        } else if (type.equals("Standard bike")) {
            return new StandardBike().getBikeById(id);
        } else if (type.equals("Twin bike")) {
            return new TwinBike().getBikeById(id);
        } else if (type.equals("Electric twin bike")) {
            return new TwinElectricBike().getBikeById(id);
        }
        return null;
    }

    public Bike viewBikeInfoById(int id) throws SQLException {
        Bike bike = new Bike();
        return bike.getBikeById(id);

    }

    public boolean checkCanRent(Bike bike) {
        return !bike.isRenting();
    }

    public Bike viewBikeInfoByBarcode(String barcode) throws SQLException {
        Bike bike = new Bike();
        return bike.getBikeByBarcode(barcode);
    }


    public void rentBike() {

    }

    public Station getStation(Integer id) throws SQLException {

        return new Station().getStationById(id);
    }


    public void returnBike() {

    }


}
