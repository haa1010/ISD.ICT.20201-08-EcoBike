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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public StandardElectricBike() throws SQLException {
        super();

    }

    @Override
    public Bike getBikeById(int id) throws SQLException {
        try {
            String qId = "\"" + id + "\"";
            String sql = "SELECT * FROM Bike B join natural join Station natural join BikeDetail natural joinElectricBike SEB  where id=" + qId + ";";
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
    public StandardElectricBike setValueBike(ResultSet res) throws SQLException {
        StandardElectricBike bike = new StandardElectricBike();
        bike.setLicensePlate(res.getString("licensePlate"));
        bike.setId(res.getInt("id"));
        bike.setNumRearSeat(res.getInt("numRearSeat"));
        bike.setLicensePlate(res.getString("licensePlate"));
        bike.setNumPedal(res.getInt("numPedal"));
        bike.setValue(res.getDouble("value"));
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
    public Bike getBikeByBarcode(String barcode) throws SQLException {
        try {
            barcode = "\"" + barcode + "\"";
            String sql = "SELECT * FROM Bike natural join BikeDetail natural join Station natural join ElectricBike where Bike.barcode= " + barcode;
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
            String sql = "SELECT * FROM Bike natural join BikeDetail natural join ElectricBike;";
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
