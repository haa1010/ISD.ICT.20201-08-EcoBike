package entity.bike;
/**
 * This class is the base class for bike
 * @author Duong Thi Hue
 * @version 1.0
 */

import entity.BaseEntity;
import entity.db.EcoBikeRental;
import entity.station.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Bike extends BaseEntity {

    protected int id;
    protected String type;
    protected String licensePlate;
    protected boolean isRenting;
    protected int numPedal;
    protected int numSaddle;
    protected int numRearSeat;
    protected String barcode;
    protected int value;
    protected double coefficient;
    protected Station station;
    protected String urlImage;

    /**
     * Bike Constructor
     *
     * @throws SQLException
     */
    public Bike() throws SQLException {

    }

    /**
     * Bike Constructor
     *
     * @param id
     * @param licensePlate
     * @param barcode
     * @param type
     */
    public Bike(int id, String licensePlate, String barcode, String type) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.barcode = barcode;
        this.type = type;

    }

    /**
     * Bike Constructor
     *
     * @param id
     * @param type
     * @param licensePlate
     * @param value
     * @param numPedal
     * @param numSaddle
     * @param numRearSeat
     * @param barcode
     * @param isRenting
     * @param urlImage
     * @param coefficient
     */
    public Bike(int id, String type, String licensePlate, int value, int numPedal, int numSaddle, int numRearSeat, String barcode, boolean isRenting, String urlImage, int coefficient) {
        this.id = id;
        this.type = type;
        this.licensePlate = licensePlate;
        this.numPedal = numPedal;
        this.numSaddle = numSaddle;
        this.numRearSeat = numRearSeat;
        this.coefficient = coefficient;
        this.barcode = barcode;
        this.value = value;
        this.isRenting = isRenting;
        this.urlImage = urlImage;
    }

    /**
     * check if the bike is being rented or not
     *
     * @return isRenting
     */
    public boolean isRenting() {
        return isRenting;
    }

    public void setRenting(boolean renting) {
        isRenting = renting;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setNumPedal(int numPedal) {
        this.numPedal = numPedal;
    }

    public void setNumSaddle(int numSaddle) {
        this.numSaddle = numSaddle;
    }

    public void setNumRearSeat(int numRearSeat) {
        this.numRearSeat = numRearSeat;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getNumPedal() {
        return numPedal;
    }

    public int getNumSaddle() {
        return numSaddle;
    }

    public int getNumRearSeat() {
        return numRearSeat;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getValue() {
        return value;
    }

    public Station getStation() {
        return station;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public boolean getRenting() {
        return isRenting;
    }

    /**
     * Set a bike from a result set by querying the db
     *
     * @param res
     * @param bike
     * @return Bike
     * @throws SQLException
     */
    public Bike setValueBike(ResultSet res, Bike bike) throws SQLException {
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
        station.setNumEmptyDockPoint(res.getInt("numEmptyDockPoint"));
        station.setNumAvailableBike(res.getInt("numAvailableBike"));
        bike.setStation(station);
        return bike;
    }

    /**
     * get a bike by querying it in db
     *
     * @param id
     * @return Bike
     * @throws SQLException
     */
    public Bike getBikeById(int id) throws SQLException {
        try {
            String qId = "\"" + id + "\"";
            String sql = "SELECT * FROM Bike natural join BikeDetail join  Station on Bike.stationID=Station.id  where Bike.id=" + qId + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                Bike bike = new Bike();
                return setValueBike(res, bike);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    /**
     * find a bike by its barcode
     *
     * @param barcode
     * @return Bike
     * @throws SQLException
     */
    public Bike getBikeByBarcode(String barcode) throws SQLException {
        try {
            barcode = "\"" + barcode + "\"";
            String sql = "SELECT * FROM Bike  join  Station on Bike.stationID=Station.id natural join BikeDetail where barcode= " + barcode + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                Bike bike = new Bike();
                return setValueBike(res, bike);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * get all bike in db
     *
     * @return List[Bike]
     * @throws SQLException
     */
    public List getAllBike() throws SQLException {
        ArrayList allBike = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Bike join  Station on Bike.stationID=Station.id natural join BikeDetail;";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {

                Bike bike = new Bike();
                allBike.add(setValueBike(res, bike));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allBike;
    }

    /**
     * check if a bike with an id is rented or not
     *
     * @param id
     * @return -1 if not found, 1 if rented, 0 otherwise
     * @throws SQLException
     */
    public int getRenting(int id) throws SQLException {
        try {
            String qId = "\"" + id + "\"";
            String sql = "SELECT * FROM Bike  where Bike.id=" + qId + ";";
            Statement stm = EcoBikeRental.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {

                return res.getInt("isRenting");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return -1;
    }

    /**
     * Update the bike info in db after returning
     *
     * @param bikeID
     * @param stationID
     */
    public void updateBikeDB(int bikeID, int stationID) {
        try {
            Statement stm = EcoBikeRental.getConnection().createStatement();
            String sql = " update " + "Bike" + " set" + " "
                    + " stationID " + "= " + stationID
                    + " where id = " + bikeID + " ;";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write a bike object to string
     *
     * @return string
     */
    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", barcode='" + barcode + "'" +
                ", value='" + value + "'" +
                ", type='" + type + "'" +
                ", urlImage='" + urlImage + "'" +
                "}";
    }

    /**
     * update station and bike when a bike is returned/rented
     *
     * @param isRent
     * @param currentBike
     * @throws SQLException
     */
    public void updateQtyDB(int isRent, Bike currentBike) throws SQLException {
        Station currentStation = currentBike.getStation();

        int stationID = currentStation.getId();
        int bikeID = currentBike.getId();

        int numAvailableBike = currentStation.getNumAvailableBike();
        int numEmptyDockPoint = currentStation.getNumEmptyDockPoint();

        if (isRent == 1) {
            numEmptyDockPoint++;
            numAvailableBike--;
        } else {
            numEmptyDockPoint--;
            numAvailableBike++;
        }
        new Station().updateFieldById("Station", stationID, "numEmptyDockPoint", Integer.toString(numEmptyDockPoint));
        new Station().updateFieldById("Station", stationID, "numAvailableBike", Integer.toString(numAvailableBike));
        new Bike().updateFieldById("Bike", bikeID, "isRenting", Integer.toString(isRent));
    }

}
