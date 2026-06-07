package base;

import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utils.DriverFactory;
import utils.PropertyReader;

// hey bro
// how are you
public class BaseTest {

    protected WebDriver driver;
    protected Properties config;
    
    private static final Logger log =
            LogManager.getLogger(BaseTest.class);
    
    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser)
    {

        // Load config
        config = PropertyReader.getProperties(
                "config.properties");

        log.info("Starting Test Execution");

        // Initialize browser
        driver = DriverFactory.initDriver(
                browser,
                config.getProperty("execution"),
                config.getProperty("grid.url"));

        log.info("Launching URL: {}",
                config.getProperty("url"));
        // Open application
        driver.get(config.getProperty("url"));

        // Wait
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Browser size
        driver.manage()
                .window()
                .setSize(new Dimension(1920, 1080));
    }

    @AfterMethod
    public void tearDown() {
    	//log.info("Closing Browser");
        DriverFactory.quitDriver();

    }
}