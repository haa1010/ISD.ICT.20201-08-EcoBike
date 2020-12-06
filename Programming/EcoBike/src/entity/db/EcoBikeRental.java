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
    private static Connection connect;

    public EcoBikeRental() {
    }


    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://remotemysql.com/vaftyLWDOZ";
            String user = "vaftyLWDOZ";
            String password = "9db0uNrKek";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("DB connected");

            return conn;
        } catch (SQLException | ClassNotFoundException var10) {
            System.out.println(var10.getMessage());
            return conn;
        } finally {
            ;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}
