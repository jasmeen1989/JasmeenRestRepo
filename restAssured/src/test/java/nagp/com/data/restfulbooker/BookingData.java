package nagp.com.data.restfulbooker;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingData {

    private String       firstname;
    private String       lastname;
    private int          totalprice;
    private boolean      depositpaid;
    private BookingDates bookingdates;
    private String       additionalneeds;

    public BookingData() {
    }

    public BookingData(final String firstname, final String lastname, final int totalprice, final boolean depositpaid, final BookingDates bookingdates,
            final String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(final int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(final boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(final BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(final String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
}