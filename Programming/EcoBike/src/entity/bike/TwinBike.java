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

    public TwinBike setValueBike(ResultSet res) throws SQLException {
        TwinBike bike = new TwinBike();
        bike.setLicensePlate(res.getString("licensePlate"));
        bike.setId(res.getInt("id"));
        bike.setNumRearSeat(res.getInt("numRearSeat"));
        bike.setLicensePlate(res.getString("licensePlate"));
        bike.setNumPedal(res.getInt("numPedal"));
        bike.setValue(res.getInt("value"));
        bike.setCoefficient(res.getInt("coefficientPrice"));
        bike.setUrlImage(res.getString("urlImage"));
        bike.setNumSaddle(res.getInt("numSaddle"));
        bike.setBarcode(res.getString("barcode"));
        bike.setRenting(res.getBoolean("isRenting"));
        bike.setType(res.getString("type"));
        Station station = new Station();
        station.setId(res.getInt("stationID"));
        station.setName(res.getString("name"));
        station.setNumAvailableBike(res.getInt("numAvailableBike"));
        station.setNumEmptyDockPoint(res.getInt("numEmptyDockPoint"));
        bike.setStation(station);
        return bike;
    }

    @Override
    public TwinBike getBikeById(int id) throws SQLException {
        try {
            String qId = "\"" + id + "\"";
            String sql = "SELECT * FROM Bike natual join BikeDetail natural  join Station  where type=\"Twin bike\" and id=" + qId + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {

                return setValueBike(res);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public TwinBike getBikeByBarcode(String barcode) throws SQLException {
        try {
            barcode = "\"" + barcode + "\"";
            String sql = "SELECT * FROM Bike natural join BikeDetail natural join Station  where type=\"Twin bike\" and barcode= " + barcode + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {

                return setValueBike(res);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public List getAllBike() throws SQLException {
        ArrayList allBike = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Bike natural join BikeDetail natural join Station  where type=\"Twin bike\";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {


                allBike.add(setValueBike(res));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allBike;
    }
}
