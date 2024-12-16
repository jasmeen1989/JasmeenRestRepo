package nagp.com.data.restfulbooker;

public class Tokencreds {

    private final String username;
    private final String password;

    // Private constructor to enforce usage of the builder
    private Tokencreds(final Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Builder pattern implementation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String username;
        private String password;

        public Builder username(final String username) {
            this.username = username;
            return this;
        }

        public Builder password(final String password) {
            this.password = password;
            return this;
        }

        public Tokencreds build() {
            return new Tokencreds(this);
        }
    }
}
