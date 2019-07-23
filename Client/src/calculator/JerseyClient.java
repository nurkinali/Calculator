package calculator;

import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class JerseyClient {

	public static final String DIVISION_VALUES = "/division/values"; // Defining a path to a specific resource using WebTarget
	public static final String ADDITION_VALUES = "/addition/values";
	public static final String SUBSTRACTION_VALUES = "/substraction/values";
	public static final String MULTIPLICATION_VALUES = "/multiplication/values";

	public static WebTarget getWebTarget() {
		Client newClient = ClientBuilder.newClient(); // Creating an instance of a client
		return newClient.target("http://localhost:8080"); // Creating a WebTarget using the URl of the targeted web resource
	}
	
	public static String calculate(final WebTarget webTarget, String calcMode, BigDecimal ...bigDecimals) {
		Invocation.Builder invocationBuilder = webTarget.queryParam("numbers", bigDecimals).path(calcMode).request(MediaType.APPLICATION_JSON); // An invocation builder instance is created
		return invocationBuilder.get(String.class); // Invoking HTTP GET requests
	}
	
	public static void main(String[] args) {
		String division = calculate(getWebTarget(), DIVISION_VALUES, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ONE);
		String addition = calculate(getWebTarget(), ADDITION_VALUES, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ONE);
		String substraction = calculate(getWebTarget(), SUBSTRACTION_VALUES, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ONE);
		String multiplication = calculate(getWebTarget(), MULTIPLICATION_VALUES, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ONE);

	}
}