package controller;

import entity.bike.Bike;
import entity.station.Station;

public class ReturnBikeController extends BaseController{


/**
 * check station have empty dock point to return bike
 * @param station
 * @author hue
 *
 */
public boolean checkStationReturnBike(Station station) {
    try {
        if (station.getNumEmptyDockPoint() > 0)
            return true;
     }catch (Exception e) {
        return false;
    }
    return false;
}
/**
 * check if the bike has been renting (belong to no station)
 * @param bike
 * @author linh
 *
 */
public boolean checkRentingBike(Bike bike) {
    try {
        if (bike.getStation() != null) {
            return true;
        }
    } catch (Exception e) {
        return false;
    }
    return false;
}

    /**
     * calculate the total cost
     * @param coefficient, time
     * @return price
     * @author thong
     *
     */
public double calculateMoney(float coefficient, int time) {
    double price;
    if (time <= 10) price = 0;
    else if (time > 10 && time <= 40) price = 10000;
    else price = 10000 + Math.ceil((time - 40)/15) * 3000;
    return price * coefficient;
}
}

