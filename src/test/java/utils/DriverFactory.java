package utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                // Disable notifications
                chromeOptions.addArguments("--disable-notifications");

                // Disable popup blocking
                chromeOptions.addArguments("--disable-popup-blocking");

                // Disable password save popup
                chromeOptions.addArguments("--disable-save-password-bubble");

                // Open fresh guest profile
                chromeOptions.addArguments("--guest");

                // Disable password manager
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                chromeOptions.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(chromeOptions);

                break;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();

                break;

            case "edge":

                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();

                break;

            default:

                throw new IllegalArgumentException(
                        "Invalid browser: " + browser);
        }

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}