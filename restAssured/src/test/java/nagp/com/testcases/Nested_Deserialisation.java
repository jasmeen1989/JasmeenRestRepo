package nagp.com.testcases;

import nagp.com.common.BaseSetup;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import nagp.com.common.BaseSetup;
import nagp.com.data.restfulbooker.Address;
import nagp.com.data.restfulbooker.Details;
import nagp.com.data.restfulbooker.Employment;
import nagp.com.data.restfulbooker.PhoneNumbers;
import nagp.com.data.restfulbooker.Salary;
import nagp.com.data.restfulbooker.UserResponse;
import nagp.com.data.restfulbooker.Users;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nagp.com.common.BaseSetup;

public class Nested_Deserialisation extends BaseSetup{
	
	@Test
	public void check() {
		
		
		RestAssured.baseURI= "https://reqres.in/api";
		
		Address objaddress = new Address("89","mohali","punjab");
		PhoneNumbers phone1 = new PhoneNumbers("home", "123-456-7890");
		PhoneNumbers phone2 = new PhoneNumbers("work", "987-654-3210");
		Salary sal = new Salary(1000,"USD");
		Employment objEmploy= new Employment("aa","bb",3,sal);
		
		
		Details objUser = new Details("jasu",54,"jasu.com",objaddress,Arrays.asList(phone1,phone2),objEmploy); //for a single parameter
		
			
		Response response = given().log().all().contentType("application/json")
				
		.body(objUser).when().log().all().post("/users")
		.then().extract().response();
		
		System.out.println(response.asString());
		
		/*
		 * UserResponse res = response.as(UserResponse.class);
		 * System.out.println(res.getAge()); System.out.println(res.getEmail());
		 */

}

}