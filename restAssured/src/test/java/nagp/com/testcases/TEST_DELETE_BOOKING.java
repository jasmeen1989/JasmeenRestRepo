package nagp.com.testcases;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import nagp.com.common.BaseSetup;
import nagp.com.data.reqres.AuthenticationPojo;
import nagp.com.data.restfulbooker.BookingData;
import nagp.com.data.restfulbooker.BookingDates;
import nagp.com.reports.ExtentReportManager;

public class TEST_DELETE_BOOKING extends BaseSetup {

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
    public void deleteBookingTest() {
        // Specify the booking ID to delete

        ExtentReportManager.createTest("Delete booking test ");

        final int bookingId = intBookingId;

        given().baseUri(URL).header("Content-Type", "application/json").header("Cookie", "token=" + strToken).when().delete("/booking/" + bookingId).then()
                .statusCode(201); // Expecting HTTP 201 as per documentation

        ExtentReportManager.pass("Booking Deleted successfully");

    }
}