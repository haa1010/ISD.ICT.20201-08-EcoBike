package controller;

import entity.bike.StandardBike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
//    void testCheckAvailableBike(boolean renting, boolean expected) {
//        StandardBike bike = new StandardBike();
//        bike.setRenting(renting);
//        boolean valid = rentBikeController.checkAvailableBike(bike);
//        assertEquals(expected, valid);
//
//    }


    @ParameterizedTest
    @CsvSource({
            " ,false",
            " asbasced ,false",
            "12ab@#,false",
            "1a2b3c,true"
    })
    public void test(String barcode, boolean expected) {
        boolean isValid = rentBikeController.validateBarcode(barcode);
        assertEquals(expected,isValid);
    }
}
