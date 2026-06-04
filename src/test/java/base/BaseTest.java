package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.DriverFactory;
import utils.PropertyReader;

public class BaseTest {

    protected WebDriver driver;

    protected Properties config;

    @BeforeMethod
    public void setup() {

        // Load config file
        config =
                PropertyReader
                .getProperties("config.properties");

        // Initialize browser
        driver =
                DriverFactory.initDriver(
                        config.getProperty("browser"));

        // Open application
        driver.get(config.getProperty("url"));

        // Wait
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Maximize window
        driver.manage().window().maximize();
    }
// SauceDemo Automation Framework
    
    @AfterMethod
    public void tearDown() {

        if(driver != null) {

            driver.quit();
        }
    }
}