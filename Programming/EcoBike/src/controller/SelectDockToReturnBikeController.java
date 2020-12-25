package controller;

import entity.station.Station;
import java.sql.SQLException;
import java.util.List;

/**
 * This class controls the flow of events in select dock to return bike screen
 * @author Do Minh Thong
 * @version 1.0
 *
 */
public class SelectDockToReturnBikeController extends BaseController{
	/**
	 * Get all stations in DB that has empty dock to display
	 * @return List[Station]
	 * @throws SQLException
	 */
    public List getStationHasEmptyDock() throws SQLException {
        return new Station().getStationHasEmptyDock();
    }
}
