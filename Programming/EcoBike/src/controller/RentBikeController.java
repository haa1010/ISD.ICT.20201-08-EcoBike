package controller;


import entity.bike.Bike;
import entity.invoice.Invoice;
import entity.order.Order;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This controller class control the flow of the renting bike process
 * in our EcoBike Software.
 * @author Pham Nhat Linh
 * @version 1.0
 */
public class RentBikeController extends BaseController {

	/**
	 * get bike with given barcode, if the barcode is invalid in format, does not exist or the bike has already been rented
	 * throw exception
	 * @param barcode
	 * @return Bike or Null
	 * @throws Exception
	 */
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
     * Check if bike has been rented or not (available bike)
     * @param bike
     * @return
     */
    public boolean checkAvailableBike(Bike bike) {
        try {
            return bike.isRenting();
        } catch (Exception e) {
            return false;
        }
    }
    
    
    /**
     * Validate the barcode's format
     * @param barcode
     * @return
     */
    public boolean validateBarcode(String barcode) {
        if (barcode == null) return false;
        barcode = barcode.trim();
        if (barcode.isEmpty()) return false;
        for (int i = 0; i < barcode.length(); i++) {
            if (!Character.isLetterOrDigit(barcode.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * Create content for the order
     * @param barcode
     * @return
     */
    public String getContent(String barcode) {
        return "Pay deposit for renting bike " + barcode;
    }

    /**
     * create new order based on the rented bike
     * create new invoice based on the order
     * @param rented Bike
     * @return
     */
    public Invoice createInvoice(Bike rented) {
        Order order = new Order(rented, LocalDateTime.now());
        Invoice invoice = new Invoice(order, order.getDeposit(), getContent(rented.getBarcode()));
        return invoice;
    }

    /**
     * set isRenting of bike
     *
     * @param bike
     * @param state
     */
    public void setRentBike(Bike bike, boolean state) {
        bike.setRenting(state);
    }

    /**
     * get deposit of bike
     *
     * @param bike
     * @return deposit of the bike
     */
    public int getDeposit(Bike bike) {
        return (int) (bike.getValue() * 0.4);
    }
}