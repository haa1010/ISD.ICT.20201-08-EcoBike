import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.RentBikeController;


class ValidateBarcodeTest {
	
	private RentBikeController rbc;

	@BeforeEach
	void setUp() throws Exception {
		rbc = new RentBikeController();
	}

	@ParameterizedTest
    @CsvSource({
            " ,false",
            " asbasced ,false",
            "12ab@#,false",
            "1a2b3c,true"
    })
    public void test(String barcode, boolean expected) {
        boolean isValid = rbc.validateBarcode(barcode);
        assertEquals(expected,isValid);
    }

}
