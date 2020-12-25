package entity.bike;

import entity.db.EcoBikeRental;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is the base class for Twin Electric Bike
 * @author Duong Thi Hue
 * @version 1.0
 */
public class TwinElectricBike extends StandardElectricBike {
	/**
	 * Constructor
	 * @throws SQLException
	 */
    public TwinElectricBike() throws SQLException { }


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
    /**
     * find a bike by its barcode
     * @param barcode
     * @return Bike
     * @throws SQLException
     */
    public Bike getBikeByBarcode(String barcode) {
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
    /**
     * get a list of all bike of this type
     * @return List[Bike]
     */
    public List getAllBike() {
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
