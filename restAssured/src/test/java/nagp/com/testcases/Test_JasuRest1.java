package nagp.com.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import nagp.com.common.BaseSetup;
import nagp.com.data.restfulbooker.BookingData;
import nagp.com.data.restfulbooker.BookingDates;
import nagp.com.reports.ExtentReportManager;

public class Test_JasuRest1 extends BaseSetup {
	
	   @Test
	    public void createBookingTest() {

	        ExtentReportManager.createTest("Get Bookings ");
	       
	        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	        
	        given().when().log().all().get("/booking").then().statusCode(200);

	    }
	   
	   @Test
	   public void createBookingTest34() {
		    ExtentReportManager.createTest("Get Bookings using spec builders");
		   
		    // Request specification with base URI and content type
		    RequestSpecification spec = new RequestSpecBuilder()
		            .setBaseUri("https://restful-booker.herokuapp.com")
		            .setContentType(ContentType.JSON)
		            .build();
		    
		    // Response specification to expect a status code of 200
		    ResponseSpecification res = new ResponseSpecBuilder()
		            .expectStatusCode(200)
		            .build();
		    
		    // Request body as a clean, correctly formatted JSON string
		    String requestBody = "{\n" +
		            "    \"firstname\": \"Jim\",\n" +
		            "    \"lastname\": \"Test\",\n" +
		            "    \"totalprice\": 111,\n" +
		            "    \"depositpaid\": true,\n" +
		            "    \"bookingdates\": {\n" +
		            "        \"checkin\": \"2018-01-01\",\n" +
		            "        \"checkout\": \"2019-01-01\"\n" +
		            "    },\n" +
		            "    \"additionalneeds\": \"LnchDineer\"\n" +
		            "}";

		    // Making the POST request with the request body
		    RequestSpecification req = given().spec(spec).body(requestBody);
		    
		    // Extract the response and print it
		    String response = req.when().log().all().post("/booking").then().spec(res).extract().asString();
		    System.out.println(response);
		}

	   
	   
		
	   @Test
	    public void createBookingTest1() {

	        ExtentReportManager.createTest("test specific booking ids ");
	       
	        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	        
	        //given().when().log().all().get("/booking").then().statusCode(200);
	        given().pathParam("id", 730).when().log().all().get("/booking/{id}").then().log().all();
	        

	    }
	   
	   @Test
	    public void createBookingTest2() {

	        ExtentReportManager.createTest("Post and Put and then get ");
	       
	        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	        
	        Map<String, Object> mapJsonBody = new HashMap<>();
	        
	        
	       // given().when().log().all().get("/booking").then().statusCode(200);
	        //given().pathParam("id", 1).when().log().all().get("/booking/{id}").then().statusCode(200);
	        String response = given().contentType("application/json").body("{\r\n"
	        		+ "    \"firstname\" : \"Jim\",\r\n"
	        		+ "    \"lastname\" : \"Test\",\r\n"
	        		+ "    \"totalprice\" : 111,\r\n"
	        		+ "    \"depositpaid\" : true,\r\n"
	        		+ "    \"bookingdates\" : {\r\n"
	        		+ "        \"checkin\" : \"2018-01-01\",\r\n"
	        		+ "        \"checkout\" : \"2019-01-01\"\r\n"
	        		+ "    },\r\n"
	        		+ "    \"additionalneeds\" : \"Lunch\"\r\n"
	        		+ "}").when().log().all().post("/booking").then().log().all().assertThat().statusCode(200).extract().asString();
	        
	        JsonPath js = new JsonPath(response);
	        System.out.println(js.getString("bookingid"));
	        
	        Response response1 =given().contentType("application/json").body("{\r\n"
	        		+ "    \"username\" : \"admin\",\r\n"
	        		+ "    \"password\" : \"password123\"\r\n"
	        		+ "}").log().all().post("/auth");
	       System.out.println(response1.jsonPath().getString("token"));
	       System.out.println(response1.getStatusCode());
	        
	        given().pathParam("id", js.getString("bookingid")).cookie("token",response1.jsonPath().getString("token")).contentType("application/json").body("{\r\n" +
	  			  "    \"firstname\" : \"James\",\r\n" + "    \"lastname\" : \"Test\",\r\n" +
	  			  "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n" +
	  			  "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
	  			  + "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n" +
	  			  "    \"additionalneeds\" : \"Dinner\"\r\n" +
	  			  "}").log().all().put("/booking/{id}").then().statusCode(200);

	        
	        String bookingid = given().pathParam("id", js.getString("bookingid")).when().log().all().get("/booking/{id}").then().log().all().extract().asString();
	        System.out.println(bookingid);
	        JsonPath js1 = new JsonPath(bookingid);
	        System.out.println(js1.getString("additionalneeds"));
	   
	   }
	   
	   @Test
	    public void createBookingTest3() throws IOException {

	        ExtentReportManager.createTest("customised payload ");
	       
	        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	        
	        Map<String, Object> requestBody = new HashMap<>();
	        
	        
	       // given().when().log().all().get("/booking").then().statusCode(200);
	        //given().pathParam("id", 1).when().log().all().get("/booking/{id}").then().statusCode(200);
	        
	        requestBody.put("firstname", "Jim");
	        requestBody.put("lastname", "Brown");
	        requestBody.put("totalprice", 111);
	        requestBody.put("depositpaid", true);

	        // Create a nested Map for "bookingdates"
	        Map<String, String> bookingDates = new HashMap<>();
	        bookingDates.put("checkin", "2018-01-01");
	        bookingDates.put("checkout", "2019-01-01");

	        // Add the "bookingdates" Map to the main request body
	        requestBody.put("bookingdates", bookingDates);

	        // Add "additionalneeds" to the request body
	        requestBody.put("additionalneeds", "Breakfast");
	       
	        //given().contentType("application/json").body(requestBody).when().log().all().get("/booking").then().statusCode(200);
	        
	        System.out.println(System.getProperty("user.dir"));
	        //File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\payload.json");
	        String file=  System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "payload.json";
	        System.out.println(file);
	        given().body(file).contentType("application/json").body(requestBody).when().log().all().post("/booking").then().statusCode(200);
	        given().body(Files.readAllBytes(Paths.get(file))); //another method
	        
	    }

	   @Test
	    public void createBookingTest6() {

	        ExtentReportManager.createTest("Fetch values from response");
	       
	        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	        
	       Response response =given().contentType("application/json").body("{\r\n"
	        		+ "    \"username\" : \"admin\",\r\n"
	        		+ "    \"password\" : \"password123\"\r\n"
	        		+ "}").log().all().post("/auth");
	       System.out.println(response.jsonPath().getString("token"));
	       System.out.println(response.getStatusCode());

	       
	    }
	   
	  
	   

	   
	
}
