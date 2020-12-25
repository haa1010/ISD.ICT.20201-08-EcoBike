package entity.bike;

/**
 * This class is the base class for Standard Bike
 * @author Duong Thi Hue
 * @version 1.0
 */
import entity.db.EcoBikeRental;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StandardBike extends Bike {
	/**
	 * Constructor
	 * @throws SQLException
	 */
    public StandardBike() throws SQLException {
    }

    @Override
    /**
     * get a bike by querying it in db
     * @param id
     * @return Bike
     * @throws SQLException
     */
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
    /**
     * find a bike by its barcode
     * @param barcode
     * @return Bike
     * @throws SQLException
     */
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

    /**
     * get a list of all bike of this type
     * @return List[Bike]
     */
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
