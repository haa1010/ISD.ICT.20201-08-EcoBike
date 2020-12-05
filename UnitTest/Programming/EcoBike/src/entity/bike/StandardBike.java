//package entity.bike;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class StandardBike extends Bike {
//    public StandardBike() throws SQLException{
//
//    }
//    public StandardBike(int id, String licensePlate, String barcode) {
//
//super(id, licensePlate, barcode);
//        this.numPedal = 1;
//        this.numSaddle = 1;
//        this.numRearSeat = 1;
//        this.value = 400000;
//        this.coefficient = 1;
//        this.type = "Standard Bike";
//    }
//
//    @Override
//    public Bike getBikeById(int id) throws SQLException {
//        String sql = "SELECT * FROM " +
//                "aims.Book " +
//                "INNER JOIN aims.Media " +
//                "ON Media.id = Book.id " +
//                "where Media.id = " + id + ";";
//        Statement stm = AIMSDB.getConnection().createStatement();
//        ResultSet res = stm.executeQuery(sql);
//        if (res.next()) {
//        }
//    }
//}
