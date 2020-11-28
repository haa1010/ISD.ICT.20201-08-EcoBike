package entity.bike;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.exception.MediaNotAvailableException;
import entity.station.Station;


public class Bike {
protected int id ;
    protected String type;
    protected String licensePlate;

    protected int  numPedal ;
    protected int numSaddle;
    protected int numRearSeat;
    protected String barcode;
    protected double value;
    protected double coefficient;
    protected entity.station.Station station;
public Bike( int id, String type, String licensePlate, double value, int numPedal, int numSaddle, int numRearSeat, String barcode ){
this.id=id;
this.type=type;
this.licensePlate=licensePlate;
this.numPedal=numPedal;
this.numSaddle=numSaddle;
this.numRearSeat=numRearSeat;
this.barcode=barcode;
this.value=value;
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

    public void setValue(double value) {
        this.value = value;
    }

    public void setStation(Station station) {
        this.station = station;
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

    public String getBarcode() {
        return barcode;
    }

    public double getValue() {
        return value;
    }

    public Station getStation() {
        return station;
    }

    public Bike(){}
    public  void setBikeInfo( int id, String type, String licensePlate, double value, int numPedal, int numSaddle, int numRearSeat, String barcode ){
       this.id=id;
        this.type=type;
        this.licensePlate=licensePlate;
        this.numPedal=numPedal;
        this.numSaddle=numSaddle;
        this.numRearSeat=numRearSeat;
        this.barcode=barcode;
        this.value=value;
    }

}
