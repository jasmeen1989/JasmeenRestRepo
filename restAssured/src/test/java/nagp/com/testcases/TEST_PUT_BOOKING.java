package nagp.com.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

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

public class TEST_PUT_BOOKING extends BaseSetup {

    // private static final String URL = "https://restful-booker.herokuapp.com";
    Logger log = LogManager.getLogger(TEST_PUT_BOOKING.class);

    String strToken = null;

    int intBookingId = 0;

    final Map<String, Object> responseMap = new HashMap<>();

    @BeforeClass
    public void getToken() {
        final AuthenticationPojo requestBody = new AuthenticationPojo(configReader.getUser(), configReader.getPassword());
        final String response = given().contentType(ContentType.JSON).body(requestBody).when().log().all().post(URL + "/auth").then().assertThat()
                .statusCode(200).log().all().body("token", notNullValue()).and().extract().response().asString();

        final JSONObject responseObject = new JSONObject(response);

        // responseMap.put("id", responseObject.getInt("id"));
        responseMap.put("token", responseObject.getString("token"));
        strToken = (String) responseMap.get("token");

        // extract token

        final BookingDates bookingDates = new BookingDates("2018-02-01", "2019-01-01");
        final BookingData newBooking = new BookingData("Jimo", "Brown", 111, true, bookingDates, "Breakfast");

        final int bookingId = given().contentType(ContentType.JSON).body(newBooking).when().post(URL + "/booking").then().statusCode(200).and().assertThat()
                .body("bookingid", notNullValue()).body("booking.firstname", equalTo(newBooking.getFirstname()))
                .body("booking.lastname", equalTo(newBooking.getLastname())).body("booking.totalprice", equalTo(newBooking.getTotalprice()))
                .body("booking.depositpaid", equalTo(newBooking.isDepositpaid()))
                .body("booking.bookingdates.checkin", equalTo(newBooking.getBookingdates().getCheckin()))
                .body("booking.bookingdates.checkout", equalTo(newBooking.getBookingdates().getCheckout()))
                .body("booking.additionalneeds", equalTo(newBooking.getAdditionalneeds())).extract().path("bookingid");

        log.info("Booking ID:" + bookingId);

        intBookingId = bookingId;

        // extract bookingId
    }

    @Test
    public void updateBookingTest() {

        final BookingDates bookingDates = new BookingDates("2018-01-01", "2019-02-01");
        final BookingData updatedBooking = new BookingData("Jimo", "Brown", 111, true, bookingDates, "Breakfast");

        given().header("Content-Type", "application/json").header("Accept", "application/json").header("Cookie", "token=" + strToken).body(updatedBooking)
                .when().log().all().put(URL + "/booking/" + intBookingId).then().statusCode(200).and().assertThat()
                .body("firstname", equalTo(updatedBooking.getFirstname())).body("lastname", equalTo(updatedBooking.getLastname()))
                .body("totalprice", equalTo(updatedBooking.getTotalprice())).body("depositpaid", equalTo(updatedBooking.isDepositpaid()))
                .body("bookingdates.checkin", equalTo(updatedBooking.getBookingdates().getCheckin()))
                .body("bookingdates.checkout", equalTo(updatedBooking.getBookingdates().getCheckout()))
                .body("additionalneeds", equalTo(updatedBooking.getAdditionalneeds()));
    }
}
