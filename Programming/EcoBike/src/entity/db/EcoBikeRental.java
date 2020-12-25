package entity.db;
import utils.Configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EcoBikeRental {
    private static Connection conn;
    public EcoBikeRental() {
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(Configs.DB_URL, Configs.DB_USERNAME, Configs.DB_PASSWORD);
                System.out.println("DB connected");

            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }
}
