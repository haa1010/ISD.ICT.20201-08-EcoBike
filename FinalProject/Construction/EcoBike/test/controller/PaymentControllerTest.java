import controller.PaymentController;
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
    private controller.PaymentController PaymentController;

    @BeforeEach
    void setUp() throws Exception {
        PaymentController = new PaymentController();
    }

    @ParameterizedTest
    @CsvSource({
            "2007-12-03 ,false",
            "1125 ,true",
            "1120,false",
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
            "Vietcombank01234,true",
            "nguyen01234,true",
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
    public void testNumberField(String number, boolean expected) {
        boolean isValid = PaymentController.validateNumberField(number);
        assertEquals(expected,isValid);
    }

    @ParameterizedTest
    @CsvSource({
            "1234abc8,true",
            "adv1 23,false",
            "124_this_is_a_card_code_789,true",
            ",false"
    })
    public void testValidateCardCode(String cardCode, boolean expected) {
        boolean isValid = PaymentController.validateCardCode(cardCode);
        assertEquals(expected, isValid);
    }

}
