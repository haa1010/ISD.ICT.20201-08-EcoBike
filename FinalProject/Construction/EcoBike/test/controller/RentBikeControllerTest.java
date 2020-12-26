// package controller;


import entity.bike.Bike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.RentBikeController;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentBikeControllerTest {
    private RentBikeController rentBikeController;

    @BeforeEach
    void setUp() throws Exception {
        rentBikeController = new RentBikeController();
    }

    @ParameterizedTest
    @CsvSource({
            "true,true",
            "false,false"

    })
    void testCheckAvailableBike(boolean renting, boolean expected) throws SQLException {
        Bike bike = new Bike();
        bike.setRenting(renting);
        boolean valid = rentBikeController.checkAvailableBike(bike);
        assertEquals(expected, valid);

    }

    @ParameterizedTest
    @CsvSource({
            " ,false",
            " asbasced ,true",
            "12ab@#,false",
            "1a2b3c,true"
    })
    public void testValidateBarcode(String barcode, boolean expected) {
        boolean isValid = rentBikeController.validateBarcode(barcode);
        assertEquals(expected, isValid);
    }

    @ParameterizedTest
    @CsvSource({
            ",false",
            "STB01,true",
            "STEB01,true",
            "ST04,false",
            "ABC123,false"
    })
    public void testValidateBarcodeBike(String barcode, boolean expected) throws SQLException {
        Bike bike = new Bike().getBikeByBarcode(barcode);
        boolean isValid;
        isValid = bike != null;
        assertEquals(expected, isValid);
    }


}
