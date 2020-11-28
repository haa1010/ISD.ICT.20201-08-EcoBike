package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlaceOrderControllerTest {
	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"0123456789,true",
		"01234,false",
		"abc123,false",
		"1234567890,false"
	})
	void testValidatePhoneNumber(String phone, boolean expected) {
		boolean isValid = placeOrderController.validatePhoneNumber(phone);
		assertEquals(expected, isValid);
	}

	@ParameterizedTest
	@CsvSource({
		"Nguyễn Đức Hưng,true",
		"Nguyen Duc Hung,true",
		"Nguy3n Duc Hung,false",
		",false"
	})
	void testValidateName (String name, boolean expected) {
		boolean isValid = placeOrderController.validateName(name);
		assertEquals(expected, isValid);
	}

	@ParameterizedTest
	@CsvSource({
		"quận Hai Bà Trưng,true",
		"quận Tây Hồ,true",
		"quan Hoan Ki3m,false",
		",false"
	})
	void testValidateAddress (String address, boolean expected) {
		boolean isValid = placeOrderController.validateAddress(address);
		assertEquals(expected, isValid);
	}

}
