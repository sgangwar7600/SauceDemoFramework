package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();
    
    private static final Logger log =
            LogManager.getLogger(DriverFactory.class);
    
    public static WebDriver initDriver(
            String browser,
            String execution,
            String gridUrl) {

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions chromeOptions = new ChromeOptions();

                // Docker / Jenkins compatible
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");

                // Disable notifications
                chromeOptions.addArguments("--disable-notifications");

                // Disable popup blocking
                chromeOptions.addArguments("--disable-popup-blocking");

                // Guest mode
                chromeOptions.addArguments("--guest");

                // Disable password manager
                Map<String, Object> prefs = new HashMap<>();

                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                chromeOptions.setExperimentalOption("prefs", prefs);

                try {

                    if (execution.equalsIgnoreCase("grid")) {

                    	log.info("Running on Selenium Grid");
                    	log.info("Initializing browser: {}", browser);

                    	log.info("Execution Type: {}", execution);

                    	log.info("Connecting to Grid URL: {}", gridUrl);
                    	
                        driver.set(
                                new RemoteWebDriver(
                                        new URL(gridUrl),
                                        chromeOptions));

                    } else {

                        WebDriverManager.chromedriver().setup();

                        driver.set(
                                new ChromeDriver(chromeOptions));
                    }

                } catch (Exception e) {

                    throw new RuntimeException(
                            "Failed to initialize Chrome Driver",
                            e);
                }

                break;

            case "firefox":

                if (execution.equalsIgnoreCase("grid")) {

                    log.info("Running Firefox on Selenium Grid");

                    try {
						driver.set(
						        new RemoteWebDriver(
						                new URL(gridUrl),
						                new FirefoxOptions()));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

                } else {

                    WebDriverManager.firefoxdriver().setup();

                    driver.set(new FirefoxDriver());
                }

                break;

            case "edge":

                WebDriverManager.edgedriver().setup();

                driver.set(new EdgeDriver());

                break;

            default:

                throw new IllegalArgumentException(
                        "Invalid browser: " + browser);
        }

        driver.get()
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        return driver.get();
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();
        }
    }
}