package foo;

import java.security.KeyStore;

import org.junit.Test;
import org.junit.Before;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class SampleTest {
	
	String URL = "http://www.herongyang.com/Service/Hello_REST.php";
	String sURL = "https://10.5.50.196:8443/EmployeeData/employees";
	String sURL1="https://bitbucket.org/api/2.0/repositories/tutorials/tutorials.bitbucket.org";
	String url1="https://www.flickr.com/services/rest/?method=flickr.test.echo&format=json&api_key=1c9508d5bd704fe419cac5c5c010e89d";
	String Keystore="C://surendra.jks";
	String key = "123456";
	
	//@Test
	public void simpleTest(){
		System.out.println("Test started...");
		
		 RestAssured.baseURI="https://10.5.50.196";
	     RestAssured.keystore(Keystore,key);
	     RestAssured.port=8443;
	     //RestAssured.useRelaxedHTTPSValidation();
	     //given().keystore("test", "test1234").body("......")
	     
	     
	    System.out.println(expect().given().keystore(Keystore,key).get(sURL).asString());

		/*//expect().body("Message", equalTo("Hello from server - herongyang.com.")).when().get("http://www.herongyang.com/Service/Hello_REST.php");
		//Response response = expect().response().get("http://www.herongyang.com/Service/Hello_REST.php");
		//response.print();
		String message = "Hello from server - herongyang.com.";
		//expect().statusCode(200).body("Result.Message", equalTo(message)).when().get("http://www.herongyang.com/Service/Hello_REST.php");
		
		XmlPath response = expect().get(sURL).xmlPath();
		
		System.out.println(response.get(message));*/
		
		System.out.println("----------------");
		
	}
	
	//@Test
	public void hMacTest(){
		Response res = expect().get(sURL1);
		System.out.println(res.getStatusCode());
		res.print();
		
		/*Response res = expect().get("http://10.5.50.221:7070/EmployeeData/employees");
		  assertEquals(200, res.getStatusCode());
		  String json = res.asString();
		  JsonPath jp = new JsonPath(json);*/
		  
		  /*assertEquals("test@hascode.com", jp.get("email"));
		  assertEquals("Tim", jp.get("firstName"));
		  assertEquals("Testerman", jp.get("lastName"));
		  assertEquals("1", jp.get("id"));*/
	}
}
