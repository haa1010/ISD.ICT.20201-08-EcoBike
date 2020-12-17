package controller;


import entity.station.Station;

import java.sql.SQLException;
import java.util.List;

public class ViewStationController extends BaseController {
    public List getAllBikeAvailable(int stationId) throws SQLException {
        return new Station().getAllBikeAvailable(stationId);
    }
}
