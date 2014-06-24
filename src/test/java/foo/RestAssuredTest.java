package foo;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;


public class RestAssuredTest {
	/*
	 * Add file "jssecacerts" in C:\Program Files\Java\jdk1.7.0_17\jre\lib\security
	 * */
	

	String sURL1="https://bitbucket.org/api/2.0/repositories/tutorials/tutorials.bitbucket.org";
	String sURL = "https://10.5.50.151:8443/EmployeeData/employees";
	String Keystore="suren.jks";
	String key = "123456";
	Response response=null;
	JsonPath jp;
	String json;
	String message="Hello from server - herongyang.com.";
	
	@Before
	public void setup(){
		if(response==null){
			response = expect().given().keystore(Keystore,key).get(sURL).andReturn();
			json = response.asString();
			jp = new JsonPath(json);
		}		
	}	
	
	@Test
	public void testWithoutSSL(){
		expect().statusCode(200).body("Result.Message", equalToIgnoringWhiteSpace(message)).when().get("http://www.herongyang.com/Service/Hello_REST.php");
	}
	
	
	@Test
	public void testWithBasicSSL(){
		Response res = expect().get(sURL1);
		System.out.println(res.getStatusCode());
		res.print();
	}
	

	@Test
	public void verifyingResponseStatusLine(){
		Assert.assertEquals("HTTP/1.1 200 OK", response.statusLine());
	}

	
	@Test
	public void verifyingResponseContentType(){
		Assert.assertEquals("application/json",response.contentType());
	}

	
	@Test
	public void verifyingResponseStatusCode(){	
		Assert.assertEquals(200,response.statusCode());	
	}


	@Test
	public void verifyingFirstElementValue(){
		int firtNodeElement = jp.get("age[0]");
		System.out.println("Getting the First element "+jp.get("age[0]"));
		Assert.assertEquals(33, firtNodeElement);
	}


	@Test
	public void verifyingEveryElementsValue(){				
		List<Integer> categories = jp.getJsonObject("age");

		int[] values ={33,23,50,32,43};

		for(int i=0;i<categories.size();i++){
			System.out.println("age ["+i+"] : "+ categories.get(i));
			// Asserting every element
			Assert.assertEquals(values[i], (int)categories.get(i));
		}
	}

	
	@Test
	public void verifyingAllElementsValue(){
		
		System.out.println("Validating all the elements");
		List<Integer> phoneNumbers = jp.get("age");		
		
		// Asserting all elements value
		assertThat(phoneNumbers, hasItems(33,23,50,32,43));
	}

}
