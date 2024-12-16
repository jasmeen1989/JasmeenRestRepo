package nagp.com.testcases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import nagp.com.common.BaseSetup;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;

public class Test_OAuth extends BaseSetup {
	
	@Test
	
	public void getResources() {
		
		
		
		
		//get access token
		
		String response_accesstoken = given().queryParams("code", "").queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token")
		.then().extract().response().asString();
		
		JsonPath js = new JsonPath(response_accesstoken);
		String access_token = js.getString("access_token");
		
		//get the courses
		String response = given().queryParam("access_token", access_token)
		.when().get("https://rahulshettyacademy.com/getCourse.php").then().extract().response().asString();

		System.out.println(response);
	
	}
}
