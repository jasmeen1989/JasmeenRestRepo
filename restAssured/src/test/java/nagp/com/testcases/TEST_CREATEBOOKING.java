package nagp.com.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import nagp.com.common.BaseSetup;
import nagp.com.data.restfulbooker.BookingData;
import nagp.com.data.restfulbooker.BookingDates;
import nagp.com.reports.ExtentReportManager;

public class TEST_CREATEBOOKING extends BaseSetup {

    // private static final String URL = "https://restful-booker.herokuapp.com";
    Logger log = LogManager.getLogger(TEST_CREATEBOOKING.class);

    @Test
    public void createBookingTest() {

        ExtentReportManager.createTest("test create booking ");
        final BookingDates bookingDates = new BookingDates("2018-02-01", "2019-01-01");
        final BookingData newBooking = new BookingData("Jim", "Brown", 111, true, bookingDates, "Breakfast");

        final int bookingId = given().contentType(ContentType.JSON).body(newBooking).when().post(URL + "/booking").then().statusCode(200).and().assertThat()
                .body("bookingid", notNullValue()).body("booking.firstname", equalTo(newBooking.getFirstname()))
                .body("booking.lastname", equalTo(newBooking.getLastname())).body("booking.totalprice", equalTo(newBooking.getTotalprice()))
                .body("booking.depositpaid", equalTo(newBooking.isDepositpaid()))
                .body("booking.bookingdates.checkin", equalTo(newBooking.getBookingdates().getCheckin()))
                .body("booking.bookingdates.checkout", equalTo(newBooking.getBookingdates().getCheckout()))
                .body("booking.additionalneeds", equalTo(newBooking.getAdditionalneeds())).extract().path("bookingid");

        log.info("Booking ID: " + bookingId);

        ExtentReportManager.pass("Booking ID created successfully with ID: " + bookingId);

    }

    public void getRequestBooking() {
        final int statusCode = given().when().get(URL + "/booking").statusCode();
        log.info("Status Code: " + statusCode);

        final String responseBody = given().when().get(URL + "/booking").getBody().asString();
        log.info("Response Body: " + responseBody);
    }

}