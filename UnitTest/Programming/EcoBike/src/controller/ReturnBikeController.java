package controller;

public class ReturnBikeController extends BaseController{


/**
 * check station have empty dock point to return bike
 * @param station
 * @author hue
 *
 */
public boolean checkStationRentBike(entity.station.Station station) {
    try {
        if (station.getNumEmptyDockPoint != 0) {
            return true;
        }

    } catch (Exception e) {
        return false;
    }
    return false;
}

}
