package nagp.com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            // Load a properties file from class path, inside static method
            properties.load(input);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getUser() {
        return properties.getProperty("user");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public static void main(final String[] args) {
        final ConfigReader configReader = new ConfigReader();
        System.out.println("User: " + configReader.getUser());
        System.out.println("Password: " + configReader.getPassword());
    }
}
