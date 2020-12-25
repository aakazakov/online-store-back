package dev.fun.store.backend.finance;

public class Money {
	
	private Long value;
	private Byte currencyCode;
	
	public Long getValue() {
		return value;
	}
	
	public Byte getCurrencyCode() {
		return currencyCode;
	}
	
	public Money() {
		this.currencyCode = (byte) 810;
	}
	
	public Money(Long value) {
		this();
		this.value = value;
	}
	
	public Money(Long value, Byte currencyCode) {
		this();
		this.value = value;
		this.currencyCode = currencyCode;
	}
	
	public Money(Double value) {
		this.value = doubleToLong(value);
	}
	
	public Money(Double value, Byte currencyCode) {
		this.value = doubleToLong(value);
		this.currencyCode = currencyCode;
	}
	
	/**
	 * Converts the currency value {@code double} to its equivalent {@code long}.
	 * @param value {@code double} number
	 * @return {@code long} number
	 */
	private long doubleToLong(double value) {
		return Math.round(value * 100);
	}
	
	public double getDoubleValue() {
		return longToDouble(value);
	}
	
	/**
	 * Converts the currency value {@code long} to its equivalent {@code double}.
	 * @param value {@code long} number
	 * @return {@code double} number
	 */
	private double longToDouble(long value) {
		return Double.valueOf(Long.toString(value)) / 100;
	}
	
}
