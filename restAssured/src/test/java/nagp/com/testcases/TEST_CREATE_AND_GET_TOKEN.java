package nagp.com.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import nagp.com.common.BaseSetup;
import nagp.com.data.reqres.AuthenticationPojo;
import nagp.com.reports.ExtentReportManager;

public class TEST_CREATE_AND_GET_TOKEN extends BaseSetup {

    // private static final String URL = "https://restful-booker.herokuapp.com";
    Logger log = LogManager.getLogger(TEST_CREATE_AND_GET_TOKEN.class);

    @DataProvider
    public Iterator<Object[]> getAuthenticationData() {
        final List<Object[]> getTestData = new ArrayList<>();
        getTestData.add(new Object[] { configReader.getUser(), configReader.getPassword() });
        return getTestData.iterator();
    }

    @Test(dataProvider = "getAuthenticationData")
    public void testAuthenticationToken(final String email, final String password) {

        ExtentReportManager.createTest("test Authentication token");

        final AuthenticationPojo requestBody = new AuthenticationPojo(email, password);

        given().contentType(ContentType.JSON).body(requestBody).when().log().all().post(URL + "/auth").then().assertThat().statusCode(200).log().all()
                .body("token", notNullValue());

    }

    public static Map<String, Object> getToken(final String email, final String password) {
        final AuthenticationPojo requestBody = new AuthenticationPojo(email, password);
        final String response = given().contentType(ContentType.JSON).body(requestBody).when().log().all().post(URL + "/auth").then().assertThat()
                .statusCode(200).log().all().body("token", notNullValue()).and().extract().response().asString();

        final JSONObject responseObject = new JSONObject(response);
        final Map<String, Object> responseMap = new HashMap<>();
        // responseMap.put("id", responseObject.getInt("id"));
        responseMap.put("token", responseObject.getString("token"));
        return responseMap;
    }

    @Test(dataProvider = "getAuthenticationData")
    public void testAuthToken(final String email, final String password) {

        ExtentReportManager.createTest("test Auth token extraction");

        final String strToken = getToken(email, password).get("token").toString();
        log.info("Token is" + getToken(email, password).get("token").toString());

        ExtentReportManager.pass("Token created successfully with ID: " + strToken);
    }

}