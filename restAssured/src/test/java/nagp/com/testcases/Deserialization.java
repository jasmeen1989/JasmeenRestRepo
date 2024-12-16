package nagp.com.testcases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import nagp.com.common.BaseSetup;
import nagp.com.data.restfulbooker.UserResponse;
import nagp.com.data.restfulbooker.Users;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nagp.com.common.BaseSetup;

public class Deserialization extends BaseSetup {

	
	@DataProvider(name="Parameters")
	public Object[][] getData(){
		return new Object[][] {{ new Users("Fudu", 12, "abc.com") }, { new Users("TestUser", 25, "test.com") }, { new Users("AnotherUser", 30, "example.com") } };
		
	}
	
	
	@Test(dataProvider="Parameters")
	public void check(Users user) {
		
		
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Users objUser = new Users("jasu",54,"jasu.com"); for a single parameter
		
			
		Response response = given().contentType("application/json")
		.body(user).when().log().all().post("/users")
		.then().extract().response();
		
		System.out.println(response.asString());
		
		UserResponse res = response.as(UserResponse.class);
		System.out.println(res.getAge());
		System.out.println(res.getEmail());
		
		
		
		
		
	}
}
