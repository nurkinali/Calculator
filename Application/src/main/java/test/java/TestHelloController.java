package test.java;

import static org.junit.Assert.assertEquals;
import hello.HelloController;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class TestHelloController {

	public class test{
			
		@Test
		public void addition() {
			List<Double> list = Arrays.asList(2.0, 4.0);			
			assertEquals(Double.toString(6), HelloController.addition(list));
		}
		
		@Test
		public void substraction() {
			List<Double> list = Arrays.asList(2.0, 4.0);			
			assertEquals(Double.toString(-2), HelloController.substraction(list));
		}
		
		@Test
		public void multiplication() {
			List<Double> list = Arrays.asList(2.0, 4.0);			
			assertEquals(Double.toString(8), HelloController.multiplication(list));
		}
		
		@Test
		public void division() {
			List<Double> list = Arrays.asList(4.0, 2.0);			
			assertEquals(Double.toString(2), HelloController.division(list));
		}
	}
}
