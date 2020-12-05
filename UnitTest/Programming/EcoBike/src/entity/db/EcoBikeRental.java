package entity.db;

import utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class EcoBikeRental {

    private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
    private static Connection connect;

    public static Connection getConnection() {


        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // db parameters
            String url = "jdbc:mysql://remotemysql.com/3I4cIEwZdW";
            String user = "3I4cIEwZdW";
            String password = "ah4boEj89z";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("DB connected");
            // more processing here
            // ...
            Statement st = conn.createStatement();
            String query = "insert into Station (name,numEmptyDockPoint,numAvailableBike,area,address) VALUES (\"Hoang Mai\",300,200,1500,\"Hoang Mai\")";
            int rsI = st.executeUpdate(query);
            System.out.println("Hi");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return conn;
        }
    }




    public static void main(String[] args) {
        EcoBikeRental.getConnection();
    }
}
