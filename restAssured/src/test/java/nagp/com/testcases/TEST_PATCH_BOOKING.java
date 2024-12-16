package nagp.com.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import nagp.com.common.BaseSetup;
import nagp.com.data.reqres.AuthenticationPojo;
import nagp.com.data.restfulbooker.BookingData;
import nagp.com.data.restfulbooker.BookingDates;
import nagp.com.data.restfulbooker.PartialBookingData;

public class TEST_PATCH_BOOKING extends BaseSetup {

    // private static final String URL = "https://restful-booker.herokuapp.com";
    Logger         log = LogManager.getLogger(TEST_PATCH_BOOKING.class);
    private String strToken;
    private int    intBookingId;

    @BeforeClass
    public void setup() {
        // Generate and store the token
        strToken = given().contentType(ContentType.JSON).body(new AuthenticationPojo(configReader.getUser(), configReader.getPassword())).when()
                .post(URL + "/auth").then().extract().path("token");

        // Create a new booking and get the booking ID
        final BookingDates bookingDates = new BookingDates("2023-01-01", "2023-01-10");
        final BookingData newBooking = new BookingData("Jim", "Brown", 100, true, bookingDates, "Breakfast");

        intBookingId = given().contentType(ContentType.JSON).body(newBooking).when().post(URL + "/booking").then().extract().path("bookingid");

        System.out.println("Token is:" + strToken);
        System.out.println("Booking ID is:" + intBookingId);
    }

    @Test
    public void updateBookingPatchTest() {
        // Create a partial update object
        final PartialBookingData partialBookingData = new PartialBookingData.Builder().firstname("James").lastname("Brown").build();

        final JSONObject requestJson = new JSONObject();
        requestJson.put("firstname", partialBookingData.getFirstname());
        requestJson.put("lastname", partialBookingData.getLastname());

        given().header("Content-Type", "application/json").header("Accept", "application/json").header("Cookie", "token=" + strToken)
                .body(requestJson.toString()).log().all().when().patch(URL + "/booking/" + intBookingId).then().log().all().statusCode(200).and().assertThat()
                .body("firstname", equalTo(partialBookingData.getFirstname()));
    }
}