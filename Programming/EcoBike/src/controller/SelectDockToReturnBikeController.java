package controller;

import entity.station.Station;
import java.sql.SQLException;
import java.util.List;

public class SelectDockToReturnBikeController extends BaseController{
    public List getStationHasEmptyDock() throws SQLException {
        return new Station().getStationHasEmptyDock();
    }
}
