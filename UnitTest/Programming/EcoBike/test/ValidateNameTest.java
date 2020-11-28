import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PaymentController;

class ValidateNameTest {
    private PaymentController PaymentController;

    @BeforeEach
    void setUp() throws Exception {
        PaymentController = new PaymentController();
    }

    @ParameterizedTest
    @CsvSource({
            "Tran Thi Hang,true",
            "Vietcombank,true",
            "nguyen01234,false",
            "$#nguyen,false",
            ",false"
    })
    public void test(String name, boolean expected) {
        boolean isValid = PaymentController.validateName(name);
        assertEquals(expected,isValid);
    }

}
