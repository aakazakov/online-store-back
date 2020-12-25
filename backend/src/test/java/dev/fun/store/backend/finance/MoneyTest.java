package dev.fun.store.backend.finance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

	@Test
	@DisplayName("should convert money from double to long")
	void checkConvertingFromDoubleToLong() {
		Money money = new Money(198.95);
		
		assertNotNull(money);
		
		long actual = money.getValue();
		
		assertEquals(19895, actual);
		
	}
	
	@Test
	@DisplayName("should convert money from long to double")
	void checkConvertingFromLongToDouble() {
		Money money = new Money(19895L);
		
		assertNotNull(money);
		
		double actual = money.getDoubleValue();
		
		assertEquals(198.95, actual);
		
	}

}
