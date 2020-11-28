package controller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlaceRushOrderControllerTest {
	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"26/11,27/11,true",
		"26/11,25/11,false",
		"24/11,26/11,false",
	})
	void testValidateReceiveHour (String fromTime, String toTime, boolean expected) {
		boolean isValid = placeRushOrderController.validateReceiveDateTime(fromTime, toTime);
		assertEquals(expected, isValid);
	}

	@ParameterizedTest
	@CsvSource({
		"Ha Noi,true",
		"Ha Tay,false",
		"Ha Nam,false",
		"Hà Nội,true"
	})
	void testValidateRushDeliveryInfo (String province, boolean expected) {
		boolean isValid = placeRushOrderController.validateRushDeliveryInfo(province);
		assertEquals(expected, isValid);
	}

}
