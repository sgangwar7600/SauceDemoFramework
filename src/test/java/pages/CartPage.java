package pages;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LocatorUtil;
import utils.PropertyReader;

public class CartPage {

    private WebDriver driver;

    private Properties prop;

    private WebDriverWait wait;

    public CartPage(WebDriver driver) {

        this.driver = driver;

        prop = PropertyReader
                .getProperties("locators.properties");

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20));
    }

    public void clickCheckout() {

        WebElement checkoutBtn =
                wait.until(
                    ExpectedConditions
                        .elementToBeClickable(
                            LocatorUtil.getLocator(
                                prop.getProperty("checkout"))));

        checkoutBtn.click();
    }
}