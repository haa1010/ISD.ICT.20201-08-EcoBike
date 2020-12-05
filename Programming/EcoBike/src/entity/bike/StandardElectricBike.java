//package entity.bike;
//
//import entity.station.Station;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class StandardElectricBike extends Bike {
//    /**
//     * set default batteryPercentage=100%
//     * set default remainingTime=14400= 4 hours
//     */
//    private int batteryPercentage = 100;
//    private int remainingTime = 14400;
//private int id;
//
//    public int getBatteryPercentage() {
//        return batteryPercentage;
//    }
//
//    public void setBatteryPercentage(int batteryPercentage) {
//        this.batteryPercentage = batteryPercentage;
//    }
//
//    public int getRemainingTime() {
//        return remainingTime;
//    }
//
//    public void setRemainingTime(int remainingTime) {
//        this.remainingTime = remainingTime;
//    }
//
//    @Override
//    public int getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public StandardElectricBike() {
//        this.numSaddle = 1;
//        this.numSaddle = 1;
//        this.coefficient = 1.5;
//        this.numRearSeat = 1;
//        this.type = "Standard Electric Bike";
//        this.value = 700000;
//    }
//
//    @Override
//    public Bike getBikeById(int id) throws SQLException {
//        try {
//            String qId = "\"" + id + "\"";
//            String sql = "SELECT * FROM Bike B join StandardElectricBike SEB on B.id=SEB.id where id="+qId+";";
//            Statement stm = AIMSDB.getConnection().createStatement();
//            ResultSet res = stm.executeQuery(sql);
//            if(res.next()) {
//
//
//                return
//                        new Bike()
//                        .setLicensePlate(res.getString("licensePlate"))
//                        .setId(res.getInt("id"))
//                        .setStation(new Station().getStationById(res.getInt("stationID"))
//                                .setIsRenting(res.getBoolean("isRenting"))
//                                .setBarcode(res.getString("barcode"))
//                                .setType(res.getString("type"))
//                                .setBatteryPercentage(res.getInt("bateryPercentage"))
//                        .setRemainingTime(res.getInt("remainingTime"));
//            }} catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    public Bike getBikeByBarcode(String barcode) throws SQLException {
////        try {
////            barcode = "\"" + barcode + "\"";
////            String sql = "SELECT * FROM Bike B  join StandardElectricBike SEB  on B.id=SEB.id  where barcode= "+ barcode +";";
////            Statement stm = AIMSDB.getConnection().createStatement();
////            ResultSet res = stm.executeQuery(sql);
////            if(res.next()) {
////
////
////                return new Bike()
////                        .setLicensePlate(res.getString("licensePlate"))
////                        .setId(res.getInt("id"))
////                        .setStation(new Station().getStationById(res.getInt("stationID"))
////                                .setBarcode(res.getString("barcode"))
////                                .setIsRenting(res.getBoolean("isRenting"))
////                                .setBatteryPercentage(res.getInt("batteryPercentage"))
////                                .setRemainingTime(res.getInt("remainingTime"))
////                                .setType(res.getString("type"));
////            }} catch (SQLException throwables) {
////            throwables.printStackTrace();
////        }
//
//        return null;
//    }
//
//    @Override
//    public void updateBikeFieldById(String tbname, int id, String column, Object value) throws SQLException {
//
//    }
//}
