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

    @ParameterizedTest
    @CsvSource({
            "1.5,9,0",
            "1,70,19000",
            "1.5,40,15000",
            "1.5,45,19500"
    })
    void testCalculateMoney(float coefficient, int time, double expected){
        double money = returnBikeController.calculateMoney(coefficient, time);
        assertEquals(expected, money);
    }
}
