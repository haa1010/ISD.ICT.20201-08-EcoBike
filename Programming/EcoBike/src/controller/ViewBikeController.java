package controller;

import common.exception.ViewBikeException;
import entity.bike.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ViewBikeController extends BaseController {
    /**
     * count 1 second
     *
     * @param amount amount want to count
     * @return a hashmap include: hour, minute, second
     */
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

    /**
     * convert time in  localDateTime to second
     *
     * @param start
     * @return return seconds
     */
    public int calculateAmountMinutes(LocalDateTime start) {
        int hour = (int) ChronoUnit.HOURS.between(start, LocalDateTime.now());
        int minute = (int) ChronoUnit.MINUTES.between(start, LocalDateTime.now());
        int second = (int) ChronoUnit.SECONDS.between(start, LocalDateTime.now());
        return hour * 3600 + minute * 60 + second;
    }

    /**
     * create instance bike base on id and type
     *
     * @param id
     * @param type
     * @return
     * @throws SQLException
     */
    public Bike setBike(int id, String type) throws SQLException, ViewBikeException {
        try {
            if (type.equals("Standard electric bike")) {
                return new StandardElectricBike().getBikeById(id);
            } else if (type.equals("Standard bike")) {
                return new StandardBike().getBikeById(id);
            } else if (type.equals("Twin bike")) {
                return new TwinBike().getBikeById(id);
            } else if (type.equals("Electric twin bike")) {
                return new TwinElectricBike().getBikeById(id);
            }
        } catch (ViewBikeException | SQLException e) {
            throw new ViewBikeException("Not found Bike");
        }
        return null;
    }

    /**
     * get bike bike id
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Bike viewBikeInfoById(int id) throws SQLException, ViewBikeException {

        try {
            Bike bike = new Bike();
            return bike.getBikeById(id);
        } catch (SQLException e) {
            throw new ViewBikeException("Not found bikeID :" + id + "in DB");
        }
    }

    /**
     * check bike isRenting or not
     *
     * @param id id of bike
     * @return return Bike.isRenting
     * @throws SQLException
     */
    public boolean bikeIsRenting(int id) throws SQLException {
        try {
            return new Bike().getBikeById(id).isRenting();
        } catch (SQLException e) {
            throw new ViewBikeException("Not found bikeID :" + id + "in DB");
        }
    }


}
