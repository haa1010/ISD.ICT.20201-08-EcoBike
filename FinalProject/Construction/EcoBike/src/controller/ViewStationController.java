package controller;

/**
 * This class controls the flow of events in station screen
 * @author Do Minh Thong
 * @version 1.0
 *
 */

import entity.station.Station;

import java.sql.SQLException;
import java.util.List;

public class ViewStationController extends BaseController {
	/**
	 * Get all bikes in a station in DB to display in this screen
	 * @return List[Bike]
	 * @throws SQLException
	 */
    public List getAllBikeAvailable(int stationId) throws SQLException {
        return new Station().getAllBikeAvailable(stationId);
    }
}
