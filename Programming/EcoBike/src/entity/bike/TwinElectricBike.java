package entity.bike;

import entity.db.EcoBikeRental;
import entity.station.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TwinElectricBike extends StandardElectricBike {
    public TwinElectricBike() throws SQLException {
        super();

    }


    @Override
    public TwinElectricBike setValueBike(ResultSet res) throws SQLException {
        TwinElectricBike bike = new TwinElectricBike();
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
    public StandardElectricBike getBikeById(int id) throws SQLException {
        try {
            String qId = "\"" + id + "\"";
            String sql = "SELECT * FROM Bike B join natural join Station natural join BikeDetail natural join ElectricBike SEB  where  where type=\"Electric twin bike\" id=" + qId + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                Bike bike = setValueBike(res);
                bike = new StandardElectricBike();
                StandardElectricBike eBike = (StandardElectricBike) bike;
                eBike.setRemainingTime(res.getInt("remainingTime"));
                eBike.setBatteryPercentage(res.getInt("batteryPercentage"));
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
            String sql = "SELECT * FROM Bike natural join BikeDetail natural join Station natural join ElectricBike where type=\"Electric twin bike\" Bike.barcode= " + barcode;
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {

                StandardElectricBike eBike = setValueBike(res);

                eBike.setRemainingTime(res.getInt("remainingTime"));
                eBike.setBatteryPercentage(res.getInt("batteryPercentage"));

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
            String sql = "SELECT * FROM Bike natural join BikeDetail natural join ElectricBike where type=\"Electric twin bike\";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                Bike bike = setValueBike(res);
                bike = new StandardElectricBike();
                StandardElectricBike eBike = (StandardElectricBike) bike;
                eBike.setRemainingTime(res.getInt("remainingTime"));
                eBike.setBatteryPercentage(res.getInt("batteryPercentage"));


                allBike.add(eBike);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allBike;

    }

}
