package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class test some validate methods for Card Information in EcoBikeRental project
 *
 * @author Tran Thi Hang
 * @version 1.0
 * <p>
 * created_at: 01/12/2020
 * <p>
 * project_name: EcoBike Rental (EBR)
 * <p>
 * teacher_name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class_name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: Teaching Assistants and other team members
 *
 *
 */
public class PaymentControllerTest {
    private PaymentController PaymentController;

    @BeforeEach
    void setUp() throws Exception {
        PaymentController = new PaymentController();
    }

    @ParameterizedTest
    @CsvSource({
            "2007-12-03 ,false",
            "2020-11-29 ,true",
            "abc,false",
            ",false"
    })
    public void testExpirationDate(String date, boolean expected) {
        boolean isValid = PaymentController.validateExpirationDate(date);
        assertEquals(expected,isValid);
    }

    @ParameterizedTest
    @CsvSource({
            "Tran Thi Hang,true",
            "Vietcombank,true",
            "nguyen01234,false",
            "$#nguyen,false",
            ",false"
    })
    public void testNameField(String name, boolean expected) {
        boolean isValid = PaymentController.validateName(name);
        assertEquals(expected,isValid);
    }


    @ParameterizedTest
    @CsvSource({
            "3456789,true",
            "abc123,false",
            ",false"
    })
    public void testNumberField(String phone, boolean expected) {
        boolean isValid = PaymentController.validateNumberField(phone);
        assertEquals(expected,isValid);
    }

}
