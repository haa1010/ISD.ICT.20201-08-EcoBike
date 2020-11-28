package controller;


import entity.bike.Bike;

/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our EcoBike Software.
 * 
 * @author hieud
 *
 */
public class RentBikeController extends BaseController {

    /**
     * check bike is on any station: if true return true else return false;
     * @param bike
     * @return
     * @author hue
     */
    public boolean checkAvailableBike(Bike bike){
        try{
           return bike.isRenting();
        }catch(Exception e){
            return false;
        }
    }
    /**
     * check input barcode format;
     * @param String barcode
     * @return
     * @author linh
     */
    public boolean validateBarcode(String barcode) {
    	if(barcode == null) return false;
    	if(barcode.length()!=6) return false;
    	// check every character of barcode
			for(int i = 0; i<barcode.length(); i++){
				if(!Character.isLetterOrDigit(barcode.charAt(i)))
					return false;
			}
	    return true;
    }
}