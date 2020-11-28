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
}