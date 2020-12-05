package entity.station;

import entity.db.EcoBikeRental;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Station {
    protected Statement stm;
    protected int id;
    protected String name;
    protected int numAvailableBike;
    protected double area;
    protected String address;
    private int numEmptyDockPoint;

    public Station(int x){
        this.numEmptyDockPoint = x;
    }
    public Station(int id,
                   String name,
                   int numAvailableBike,
                   int numEmptyDockPoint,
                   float area,
                   String address){
        this.id = id;
        this.name = name;
        this.numAvailableBike = numAvailableBike;
        this.numEmptyDockPoint = numEmptyDockPoint;
        this.area = area;
        this.address = address;
    }

    public Station setId(int id) {
        this.id = id;
        return this;
    }
    public Station setName(String name) {
        this.name = name;
        return this;
    }
    public Station setNumAvailableBike(int numAvailableBike) {
        this.numAvailableBike = numAvailableBike;
        return this;
    }
    public Station setAddress(String address) {
        this.address = address;
        return this;
    }
    public Station setArea(double area) {
        this.area = area;
        return this;
    }
    public Station setNumEmptyDockPoint(int numEmptyDockPoint) {
        this.numEmptyDockPoint = numEmptyDockPoint;
        return this;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getNumAvailableBike() {
        return numAvailableBike;
    }
    public double getArea() {
        return area;
    }
    public String getAddress() {
        return address;
    }
    public int getNumEmptyDockPoint(){
        return this.numEmptyDockPoint;
    }

    public Station() throws SQLException {
        stm = EcoBikeRental.getConnection().createStatement();
    }

    public List getAllStation() throws SQLException{
        Statement stm = EcoBikeRental.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from Station");
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            Station station = new Station()
                    .setId(res.getInt("id"))
                    .setName(res.getString("name"))
                    .setNumEmptyDockPoint(res.getInt("numEmptyDockPoint"))
                    .setNumAvailableBike(res.getInt("numAvailableBike"))
                    .setArea(res.getDouble("area"))
                    .setAddress(res.getString("address"));
            medium.add(station);
        }
        return medium;
    }
}
