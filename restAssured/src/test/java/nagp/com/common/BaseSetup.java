package nagp.com.common;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import nagp.com.reports.ExtentReportManager;
import nagp.com.utils.ConfigReader;

public class BaseSetup {

    protected static final String URL = "https://restful-booker.herokuapp.com";

    protected final ConfigReader configReader = new ConfigReader();

    @BeforeClass
    public void setUp() {
        ExtentReportManager.initReports();
    }

    @AfterClass
    public void tearDown() {
        ExtentReportManager.flushReports();
    }
}