import controller.PaymentController;
import controller.RentBikeController;
import entity.transaction.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ValidateBalance {
	
	private PaymentController paymentController;

	@BeforeEach
	void setUp() throws Exception {
		paymentController = new PaymentController();
	}

	@ParameterizedTest
    @CsvSource({
            "51000,52000,false",
            "100000,20000,true",
            "5000,15000,false"
    })
    public void test(Card card, double money, boolean expected) {
        boolean isValid = paymentController.validateBalance(card, money);
        assertEquals(expected,isValid);
    }

}
