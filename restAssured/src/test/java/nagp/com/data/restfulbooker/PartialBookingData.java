package nagp.com.data.restfulbooker;

public class PartialBookingData {

    private final String  firstname;
    private final String  lastname;
    private final Integer totalprice;
    private final Boolean depositpaid;
    private final String  additionalneeds;

    // Private constructor to enforce usage of the builder
    private PartialBookingData(final Builder builder) {
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.totalprice = builder.totalprice;
        this.depositpaid = builder.depositpaid;
        this.additionalneeds = builder.additionalneeds;
    }

    // Getter methods
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    // Builder pattern implementation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String  firstname;
        private String  lastname;
        private Integer totalprice;
        private Boolean depositpaid;
        private String  additionalneeds;

        public Builder firstname(final String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(final String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder totalprice(final Integer totalprice) {
            this.totalprice = totalprice;
            return this;
        }

        public Builder depositpaid(final Boolean depositpaid) {
            this.depositpaid = depositpaid;
            return this;
        }

        public Builder additionalneeds(final String additionalneeds) {
            this.additionalneeds = additionalneeds;
            return this;
        }

        public PartialBookingData build() {
            return new PartialBookingData(this);
        }
    }
}