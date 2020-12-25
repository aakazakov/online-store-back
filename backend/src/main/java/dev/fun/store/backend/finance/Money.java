package dev.fun.store.backend.finance;

public class Money {
		
	/**
	 * Converts the currency {@code double} value  to its {@code long} equivalent.<p>
	 * For example:<p>
	 * <code>doubleToLong(98.95)</code> will return <code>9895L</code><p>
	 * 98.95 USD = 9895 cents
	 * @param value {@code double} number
	 * @return {@code long} number
	 */
	public static long doubleToLong(double value) {
		return Math.round(value * 100);
	}
	
	/**
	 * Converts the currency {@code long} value  to its {@code double} equivalent.
	 * For example:<p>
	 * <code>longToDouble(9895L)</code> will return <code>98.95</code><p>
	 * 9895 cents = 98.95 USD
	 * @param value {@code long} number
	 * @return {@code double} number
	 */
	public static double longToDouble(long value) {
		return Double.valueOf(Long.toString(value)) / 100;
	}
	
	private Money() {
		
	}
	
}
