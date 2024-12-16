package nagp.com.testcases;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nagp.com.common.BaseSetup;
import nagp.com.utils.Log;

public class TEST_GETBOOKING_BOOKINGIDS extends BaseSetup {

    // private static final String URL = "https://restful-booker.herokuapp.com";

    @DataProvider(name = "getUserData")
    public Iterator<Object[]> getUsers() {
        final List<Object[]> getData = new ArrayList<>();
        getData.add(new Object[] { 2 });
        return getData.iterator();
    }

    @Test
    public void getRequestBooking() {

        final int statusCode = given().when().get(URL + "/booking").statusCode();
        Log.info(statusCode);

        final String responseBody = given().when().get(URL + "/booking").getBody().asString();
        Log.info(responseBody);
    }

    @Test(dataProvider = "getUserData")
    public void getRequestTestWithQueryParam(final int userPage) {

        final String responseBody = given().when().get(URL + "/booking" + "/" + userPage).getBody().asString();
        Log.info(responseBody);
    }

}