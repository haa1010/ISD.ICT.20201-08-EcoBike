package entity.bike;

import entity.db.EcoBikeRental;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StandardBike extends Bike {
    public StandardBike() throws SQLException {
    }

    @Override
    public Bike getBikeById(int id) throws SQLException {
        try {
            String qId = "\"" + id + "\"";
            String sql = "SELECT * FROM Bike natural join BikeDetail join  Station on Bike.stationID=Station.id where type=\"Standard bike\" and Bike.id=" + qId + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                StandardBike bike = new StandardBike();
                return setValueBike(res, bike);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Bike getBikeByBarcode(String barcode) {
        try {
            barcode = "\"" + barcode + "\"";
            String sql = "SELECT * FROM Bike natural join BikeDetail join  Station on Bike.stationID=Station.id  where type=\"Standard bike\" and barcode= " + barcode + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                StandardBike bike = new StandardBike();
                return setValueBike(res, bike);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public List getAllBike() {
        ArrayList allBike = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Bike natural join BikeDetail join  Station on Bike.stationID=Station.id where type=\"Standard bike\";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {

                StandardBike bike = new StandardBike();

                allBike.add(setValueBike(res, bike));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allBike;
    }

}
