import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PaymentController;

class ValidateExpirationDateTest {
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
    public void test(String date, boolean expected) {
        boolean isValid = PaymentController.validateExpirationDate(date);
        assertEquals(expected,isValid);
    }

}
