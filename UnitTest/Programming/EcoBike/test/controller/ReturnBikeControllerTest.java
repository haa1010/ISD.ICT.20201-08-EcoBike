package controller;

import entity.station.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnBikeControllerTest {
    private ReturnBikeController returnBikeController;
    @BeforeEach
    void setUp() throws Exception{
        returnBikeController= new ReturnBikeController();
    }


    @ParameterizedTest
    @CsvSource({
            "15,true",
            "0,false",
            "-1,false"
    })
    void testCheckStationReturnBike( int numEmptyDockPoint,boolean expected){
        Station station= new Station(numEmptyDockPoint);
        boolean valid= returnBikeController.checkStationReturnBike(station);
        assertEquals(expected,valid);

    }
}
