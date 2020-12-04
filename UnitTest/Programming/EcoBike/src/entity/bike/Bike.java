package entity.bike;

import entity.station.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Bike {
    protected Statement stm;
    protected int id;
    protected String type;
    protected String licensePlate;
    protected boolean isRenting = false;
    protected int numPedal;
    protected int numSaddle;
    protected int numRearSeat;
    protected String barcode;
    protected double value;
    protected double coefficient;
    protected Station station;
    protected String urlImage;
    public Bike() throws SQLException {
        stm = AIMSDB.getConnection().createStatement();
    }
public Bike(int id, String licensePlate, String barcode) {
    this.id = id;
    this.licensePlate = licensePlate;
    this.barcode = barcode;

}
    public Bike(int id, String type, String licensePlate, double value, int numPedal, int numSaddle, int numRearSeat, String barcode) {
        this.id = id;
        this.type = type;
        this.licensePlate = licensePlate;
        this.numPedal = numPedal;
        this.numSaddle = numSaddle;
        this.numRearSeat = numRearSeat;
        this.barcode = barcode;
        this.value = value;
    }
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

//    public void setNumPedal(int numPedal) {
//        this.numPedal = numPedal;
//    }
//
//    public void setNumSaddle(int numSaddle) {
//        this.numSaddle = numSaddle;
//    }
//
//    public void setNumRearSeat(int numRearSeat) {
//        this.numRearSeat = numRearSeat;
//    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

//   public void setValue(double value) {
//        this.value = value;
//    }

    public void setStation(Station station) {
        this.station = station;
    }
//
//    public void setCoefficient(double coefficient) {
//        this.coefficient = coefficient;
//    }

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

    public double getValue() {
        return value;
    }

    public Station getStation() {
        return station;
    }

    public double getCoefficient() {
        return coefficient;
    }


    public Bike getBikeById(int id) throws SQLException{
        try {
        String sql = "SELECT * FROM Bike where id=id;";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()) {


                return new Bike()
                        .setLicensePlate(res.getString("licensePlate"))
                        .setId(res.getInt("id"))
                        .setStation(new Station().getStationById(res.getInt("stationID"))
//                        .setNumPedal(res.getInt("numPedal"))
//                        .setNumRearSeat(res.getInt("numRearSeat"))
                         .setBarcode(res.getString("barcode"))
//                                .setValue(res.getDouble("value"))
//                                .setCoefficient(res.getInt("coefficient"))
                        .setType(res.getString("type"));
        }} catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        return null;

    }
    public Bike getBikeByBarcode(String barcode)throws SQLException{
        try {
            String sql = "SELECT * FROM Bike where barcode=`barcode`;";
            Statement stm = AIMSDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if(res.next()) {


                return new Bike()
                        .setLicensePlate(res.getString("licensePlate"))
                        .setId(res.getInt("id"))
                        .setStation(new Station().getStationById(res.getInt("stationID"))
//                        .setNumPedal(res.getInt("numPedal"))
//                        .setNumRearSeat(res.getInt("numRearSeat"))
                                .setBarcode(res.getString("barcode"))
//                                .setValue(res.getDouble("value"))
//                                .setCoefficient(res.getInt("coefficient"))
                                .setType(res.getString("type"));
            }} catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
    public List getAllBike() throws SQLException{
        ArrayList allBike = new ArrayList<>();
       try{ String sql = "SELECT * FROM Bike ;";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);

        while(res.next()) {

Bike bike= new Bike()
.setLicensePlate(res.getString("licensePlate"))
                    .setId(res.getInt("id"))
                    .setStation(new Station().getStationById(res.getInt("stationID"))
//                        .setNumPedal(res.getInt("numPedal"))
//                        .setNumRearSeat(res.getInt("numRearSeat"))
                            .setBarcode(res.getString("barcode"))
//                                .setValue(res.getDouble("value"))
//                                .setCoefficient(res.getInt("coefficient"))
                            .setType(res.getString("type"));
                    allBike.add(bike);
        }} catch (SQLException throwables) {
        throwables.printStackTrace();
    }

        return allBike;
    }
    public void updateBikeFieldById(String tbname, int id, String field, Object value) throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        if (value instanceof String){
            value = "\"" + value + "\"";
        }
        stm.executeUpdate(" update " + tbname + " set" + " "
                + field + "=" + value + " "
                + "where id=" + id + ";");
    }
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
    public void setBikeInfo(int id, String type, String licensePlate, double value, int numPedal, int numSaddle, int numRearSeat, String barcode) {
        this.id = id;
        this.type = type;
        this.licensePlate = licensePlate;
        this.numPedal = numPedal;
        this.numSaddle = numSaddle;
        this.numRearSeat = numRearSeat;
        this.barcode = barcode;
        this.value = value;
    }

}
