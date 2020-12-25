package controller;


import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This {@code RentBikeController} class control the flow of the renting bike process
 * in our EcoBike Software.
 */
public class RentBikeController extends BaseController {


    public Bike validateBarcodeBike(String barcode) throws Exception {
        Bike tmp;
        if (!this.validateBarcode(barcode))
            throw new Exception("Invalid Barcode");
        tmp = new Bike().getBikeByBarcode(barcode);
        if (tmp == null) throw new Exception("Barcode is not exist");
        if (checkAvailableBike(tmp))
            throw new Exception("Bike has already been rented");
        return tmp;
    }

    /**
     * check bike is on any station: if true return true else return false;
     *
     * @param bike
     * @return
     * @author hue
     */
    public boolean checkAvailableBike(Bike bike) {
        try {
            return bike.isRenting();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateBarcode(String barcode) {
        barcode = barcode.trim();
        if (barcode == null || barcode.isEmpty()
        ) return false;
        for (int i = 0; i < barcode.length(); i++) {
            if (!Character.isLetterOrDigit(barcode.charAt(i)))
                return false;
        }
        return true;
    }

    public String getContent(String barcode) {
        return "Pay deposit for renting bike" + barcode;
    }

    /**
     * save order and invoice to db
     *
     * @param rented
     * @return
     * @throws SQLException
     */
    public Invoice saveToDB(Bike rented) throws SQLException {
        Order order = new Order(rented, LocalDateTime.now());
        order.newOrderDB();
        Invoice invoice = new Invoice(order, order.getDeposit(), getContent(rented.getBarcode()));

        return invoice;
    }

}