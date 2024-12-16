package nagp.com.data.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthenticationPojo {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    // Default constructor
    public AuthenticationPojo() {
    }

    // Parameterized constructor
    public AuthenticationPojo(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}