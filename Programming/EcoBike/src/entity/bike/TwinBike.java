package entity.bike;

import entity.db.EcoBikeRental;
import entity.station.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TwinBike extends Bike {
    /**
     * set default attribute for twin bike
     *
     * @author hue
     */
    public TwinBike() throws SQLException {
        super();

    }


    @Override
    public Bike getBikeById(int id) throws SQLException {
        try {
            String qId = "\"" + id + "\"";
            String sql = "SELECT * FROM Bike natural join BikeDetail natural join Station  where type=\"Twin bike\" and id=" + qId + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                TwinBike bike = new TwinBike();
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
            String sql = "SELECT * FROM Bike natural join BikeDetail natural join Station  where type=\"Twin bike\" and barcode= " + barcode + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            stm.close();
            if (res.next()) {

                TwinBike bike = new TwinBike();
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
            String sql = "SELECT * FROM Bike natural join BikeDetail natural join Station where type=\"Twin bike\";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {

                TwinBike bike = new TwinBike();

                allBike.add(setValueBike(res, bike));
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allBike;
    }
}
