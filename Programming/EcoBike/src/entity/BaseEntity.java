package entity;

import entity.bike.Bike;
import entity.db.EcoBikeRental;
import entity.invoice.Invoice;
import entity.station.Station;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is the base entity
 * we can get other entity by query in the db
 * @author Duong Thi Hue
 * @version 1.0
 */
public class BaseEntity {

    /**
     * update row in table
     * @param tbname
     * @param id
     * @param column
     * @param value
     * @throws SQLException
     */
    public void updateFieldById(String tbname, int id, String column, Object value) throws SQLException {
        Statement stm = EcoBikeRental.getConnection().createStatement();
        if (value instanceof String) {
            value = "\"" + value + "\"";
        }
        stm.executeUpdate(" update " + tbname + " set" + " "
                + column + "=" + value + " "
                + "where id=" + id + ";");

    }
    

}
