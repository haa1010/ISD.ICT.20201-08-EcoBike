package entity.bike;

import entity.db.EcoBikeRental;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TwinElectricBike extends StandardElectricBike {
    public TwinElectricBike() throws SQLException {


    }


    @Override
    public Bike getBikeById(int id) throws SQLException {
        try {
            String qId = "\"" + id + "\"";
            String sql = "SELECT * FROM Bike B natural join BikeDetail join  Station on B.stationID=Station.id  join ElectricBike SEB on B.id=SEB.id    where type=\"Electric twin bike\" and  B.id=" + qId + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                TwinElectricBike bike = new TwinElectricBike();

                TwinElectricBike eBike = (TwinElectricBike) setValueBike(res, bike);
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
            String sql = "SELECT * FROM Bike natural join BikeDetail join  Station on Bike.stationID=Station.id  join ElectricBike SEB on B.id=SEB.id  where type=\"Electric twin bike\" and Bike.barcode= " + barcode;
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {

                TwinElectricBike bike = new TwinElectricBike();

                TwinElectricBike eBike = (TwinElectricBike) setValueBike(res, bike);

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
    public List getAllBike() throws SQLException {
        ArrayList allBike = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Bike natural join BikeDetail join  Station on Bike.stationID=Station.id  join ElectricBike SEB on B.id=SEB.id  where type=\"Electric twin bike\";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);


            while (res.next()) {
                TwinElectricBike bike = new TwinElectricBike();
                TwinElectricBike eBike = (TwinElectricBike) setValueBike(res, bike);
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
