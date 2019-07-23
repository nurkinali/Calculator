package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calculator.Calculator;

public class TestCalculator{
		
	@Test
	public void addition() {
		assertEquals(Double.toString(6), Calculator.addition(Double.toString(2), Double.toString(4)));
	}
	
	@Test
	public void substraction() {
		assertEquals(Double.toString(-2), Calculator.substraction(Double.toString(2), Double.toString(4)));
	}
	
	@Test
	public void multiplication() {
		assertEquals(Double.toString(8), Calculator.multiplication(Double.toString(2), Double.toString(4)));
	}
	
	@Test
	public void division() {
		assertEquals(Double.toString(2), Calculator.division(Double.toString(4), Double.toString(2)));
	}
}