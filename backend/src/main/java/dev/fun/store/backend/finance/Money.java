package dev.fun.store.backend.finance;

public class Money {
		
	/**
	 * Converts the currency value {@code double} to its equivalent {@code long}.
	 * @param value {@code double} number
	 * @return {@code long} number
	 */
	public static long doubleToLong(double value) {
		return Math.round(value * 100);
	}
	
	/**
	 * Converts the currency value {@code long} to its equivalent {@code double}.
	 * @param value {@code long} number
	 * @return {@code double} number
	 */
	public static double longToDouble(long value) {
		return Double.valueOf(Long.toString(value)) / 100;
	}
	
	private Money() {
		
	}
	
}
