package controller;

import entity.station.Station;

import java.sql.SQLException;
import java.util.List;

/**
 * This class controls the flow of events in homescreen
 * @author Do Minh Thong
 * @version 1.0
 *
 */
public class HomeController extends BaseController {
	/**
	 * Get all stations in DB to display in home screen
	 * @return List[Station]
	 * @throws SQLException
	 */
    public List getAllStations() throws SQLException {
        return new Station().getAllStations();
    }
}
