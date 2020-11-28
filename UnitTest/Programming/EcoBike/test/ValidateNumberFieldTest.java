import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PaymentController;

class ValidateNumberFieldTest {
    private PaymentController PaymentController;

    @BeforeEach
    void setUp() throws Exception {
        PaymentController = new PaymentController();
    }

    @ParameterizedTest
    @CsvSource({
            "3456789,true",
            "abc123,false",
            ",false"
    })
    public void test(String phone, boolean expected) {
        boolean isValid = PaymentController.validateNumberField(phone);
        assertEquals(expected,isValid);
    }

}
