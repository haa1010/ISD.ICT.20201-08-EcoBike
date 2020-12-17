package controller;

import entity.station.Station;

import java.sql.SQLException;
import java.util.List;

/**
 * This class controls the flow of events in homescreen
 *
 * @author nguyenlm
 */
public class HomeController extends BaseController {
    public List getAllStations() throws SQLException {
        return new Station().getAllStations();
    }
}
