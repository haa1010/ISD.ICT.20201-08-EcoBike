package entity;

import entity.bike.Bike;
import entity.db.EcoBikeRental;
import entity.invoice.Invoice;
import entity.station.Station;

import java.sql.SQLException;
import java.sql.Statement;

public class BaseEntity {

    /**
     * update row in table
     *
     * @param tbname
     * @param id
     * @param column
     * @param value
     * @throws SQLException
     */
    public static void updateFieldById(String tbname, int id, String column, Object value) throws SQLException {
        Statement stm = EcoBikeRental.getConnection().createStatement();
        if (value instanceof String) {
            value = "\"" + value + "\"";
        }
        stm.executeUpdate(" update " + tbname + " set" + " "
                + column + "=" + value + " "
                + "where id=" + id + ";");

    }

    public static void updateDB(int isRent, Bike currentBike) throws SQLException {
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
        Station.updateFieldById("Station", stationID, "numEmptyDockPoint", Integer.toString(numEmptyDockPoint));
        Station.updateFieldById("Station", stationID, "numAvailableBike", Integer.toString(numAvailableBike));
        Bike.updateFieldById("Bike", bikeID, "isRenting", Integer.toString(isRent));
    }

}
