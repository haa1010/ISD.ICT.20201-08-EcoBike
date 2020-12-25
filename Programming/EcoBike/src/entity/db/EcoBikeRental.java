package entity.db;
import utils.Configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * This class is the database entity of the program
 * @author minhthong
 * @version 1.0
 */

public class EcoBikeRental {
    private static Connection conn;
    
    /**
     * Constructor
     */
    public EcoBikeRental() {
    }

    /**
     * Get the connection to the remote database
     * @return Connection
     */
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
