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
            String sql = "SELECT * FROM Bike natual join BikeDetail  where type=\"Standard bike\" and id=" + qId + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                StandardElectricBike bike = new StandardElectricBike();
                return setValueBike(res, bike);
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
            String sql = "SELECT * FROM Bike natural join BikeDetail  where type=\"Standard bike\" and barcode= " + barcode + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                StandardElectricBike bike = new StandardElectricBike();
                return setValueBike(res, bike);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public List getAllBike() throws SQLException {
        ArrayList allBike = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Bike natural join BikeDetail where type=\"Standard bike\";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {

                StandardElectricBike bike = new StandardElectricBike();

                allBike.add(setValueBike(res, bike));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allBike;
    }

}
