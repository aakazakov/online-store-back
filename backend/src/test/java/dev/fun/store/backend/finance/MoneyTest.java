package dev.fun.store.backend.finance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

	@Test
	@DisplayName("should convert money from double to long")
	void checkConvertingFromDoubleToLong() {
		
		long actual = Money.doubleToLong(198.95);
		
		assertEquals(19895, actual);
		
	}
	
	@Test
	@DisplayName("should convert money from long to double")
	void checkConvertingFromLongToDouble() {
		
		double actual = Money.longToDouble(19895);
		
		assertEquals(198.95, actual);
		
	}

}
