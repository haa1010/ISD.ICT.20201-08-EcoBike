//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package entity.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import utils.Utils;

public class EcoBikeRental {
    private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
    private static Connection conn;

    public EcoBikeRental() {
    }


    public static Connection getConnection() {
        conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/vaftyLWDOZ";
            String user = "root";
            String password = "30041975";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException var10) {
            LOGGER.info(var10.getMessage());
        } finally {
            return conn;

        }

    }

    public static void main(String[] args) {
        getConnection();
    }
}
