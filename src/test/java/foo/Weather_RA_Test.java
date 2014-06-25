package foo;

import static com.jayway.restassured.RestAssured.expect;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;


public class Weather_RA_Test {
	String URL ="http://api.openweathermap.org/data/2.5/weather?q=London,uk";
	
	String URL1 = "";
	//@Test
	public void weatherTest(){
		Response response = expect().get(URL).andReturn();
		response.prettyPrint();
		
		System.out.println(" Status Code "+ RestAssured.get(URL).getStatusCode());
		
		System.out.println(response.getBody().jsonPath().get("weather[0].id"));
	}
	
	

}
