package controller;


import entity.bike.Bike;

/**
 * This {@code RentBikeController} class control the flow of the renting bike process
 * in our EcoBike Software.
 * 
 */
public class RentBikeController extends BaseController {

	
	public Bike validateBarcodeBike(String barcode) throws Exception {
		Bike tmp;
		if(!this.validateBarcode(barcode))
			throw new Exception("Invalid Barcode");
		tmp = new Bike().getBikeByBarcode(barcode);
		if(checkAvailableBike(tmp))
			throw new Exception("Bike has already been rented");
		return tmp;
	}
	
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
    	barcode = barcode.trim();
    	if(barcode == null) return false;
    	//if(barcode.length()!=6) return false;
    	// check every character of barcode
			for(int i = 0; i<barcode.length(); i++){
				if(!Character.isLetterOrDigit(barcode.charAt(i)))
					return false;
			}
	    return true;
    }
}