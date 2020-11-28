package controller;

import entity.station.Station;

public class ReturnBikeController extends BaseController{


/**
 * check station have empty dock point to return bike
 * @param station
 * @author hue
 *
 */
public boolean checkStationRenturnBike(Station station) {
    try {
        if (station.getNumEmptyDockPoint() > 0) {
            return true;
//        }

    } catch (Exception e) {
        return false;
    }
//    return false;
}

}
