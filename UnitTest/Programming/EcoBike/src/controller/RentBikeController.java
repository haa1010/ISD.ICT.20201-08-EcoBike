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
            if(bike.getStation()!=null) return true;
            else return false;
        }catch(Exception e){
            return false;
        }
    }
}