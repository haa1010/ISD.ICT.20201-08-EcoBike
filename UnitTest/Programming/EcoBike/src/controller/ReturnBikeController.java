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
}

