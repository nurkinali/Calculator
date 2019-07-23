package hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestClient {
	
	@Test
	public void shouldReturnStatusOkay() throws UnirestException {
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://www.mocky.io/v2/5a9ce37b3100004f00ab5154")
	      .header("accept", "application/json").queryString("apiKey", "123") // passing headers and parameters with the header() and fields() 
	      .asJson(); // request gets invoked on the asJson() asBinary() asString() asObject() methods call
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void shouldReturnStatusAccepted() throws UnirestException {
	    Map<String, String> headers = new HashMap<>(); // to pass multiple headers or fields creating a map and pass them to .headers(Map<String, Object> headers) and .fields(Map<String, String> fields)
	    headers.put("accept", "application/json");
	    headers.put("Authorization", "Bearer 5a9ce37b3100004f00ab5154");
	 
	    Map<String, Object> fields = new HashMap<>();
	    fields.put("name", "Sam Baeldung");
	    fields.put("id", "PSP123");
	 
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://www.mocky.io/v2/5a9ce7853100002a00ab515e")
	      .headers(headers).fields(fields)
	      .asJson();
	  
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(202, jsonResponse.getStatus());
	}
	
	@Test
	public void givenRequestBodyWhenCreatedThenCorrect() throws UnirestException {
	 
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://www.mocky.io/v2/5a9ce7663100006800ab515d")
	      .body("{\"name\":\"Sam Baeldung\", \"city\":\"viena\"}") // if request requires a string / JSON body: body()
	      .asJson();
	  
	    assertEquals(201, jsonResponse.getStatus());
	}
	
	 /* to pass data as a query String: queryString()
	HttpResponse<JsonNode> jsonResponse 
	  = Unirest.get("http://www.mocky.io/v2/5a9ce37b3100004f00ab5154")
	  .queryString("apiKey", "123") */
	
	/* for passing any URL parameters: routeParam() - parameter placeholder == first argument of the method
	HttpResponse<JsonNode> jsonResponse 
  	  = Unirest.get("http://www.mocky.io/v2/5a9ce37b3100004f00ab5154/{userId}")
      .routeParam("userId", "123") */
	
	/* 
	 * 
	 */
	
	

}
