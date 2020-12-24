package entity.bike;

import entity.db.EcoBikeRental;
import entity.station.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StandardElectricBike extends Bike {
    /**
     * set default batteryPercentage=100%
     * set default remainingTime=14400= 4 hours
     */
    protected int batteryPercentage;
    protected int remainingTime;


    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

//    @Override
//    public int getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(int id) {
//        this.id = id;
//    }

    public StandardElectricBike() throws SQLException {


    }

    @Override
    public Bike getBikeById(int id) throws SQLException {
        try {
            String qId = "\"" + id + "\"";
            String sql = "SELECT * FROM Bike B  natural join BikeDetail join  Station on B.stationID=Station.id join ElectricBike SEB on B.id=SEB.id where B.id= " + qId + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {

                StandardElectricBike bike = new StandardElectricBike();
                StandardElectricBike eBike = (StandardElectricBike) setValueBike(res, bike);
                eBike.setRemainingTime(res.getInt("remainingTime"));
                eBike.setBatteryPercentage(res.getInt("batteryPercentage"));
                stm.close();
                return eBike;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    @Override
    public Bike getBikeByBarcode(String barcode) throws SQLException {
        try {
            barcode = "\"" + barcode + "\"";
            String sql = "SELECT * FROM Bike B  natural join BikeDetail join  Station on B.stationID=Station.id join ElectricBike SEB on B.id=SEB.id where B.barcode= " + barcode;
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                StandardElectricBike bike = new StandardElectricBike();
                StandardElectricBike eBike = (StandardElectricBike) setValueBike(res, bike);
                eBike.setRemainingTime(res.getInt("remainingTime"));
                eBike.setBatteryPercentage(res.getInt("batteryPercentage"));

                return eBike;

            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List getAllBike() throws SQLException {
        ArrayList allBike = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Bike B  natural join BikeDetail join  Station on B.stationID=Station.id join ElectricBike SEB on B.id=SEB.id";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);


            while (res.next()) {

                StandardElectricBike bike = new StandardElectricBike();
                StandardElectricBike eBike = (StandardElectricBike) setValueBike(res, bike);
                eBike.setRemainingTime(res.getInt("remainingTime"));
                eBike.setBatteryPercentage(res.getInt("batteryPercentage"));
                allBike.add(eBike);
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allBike;

    }


}
