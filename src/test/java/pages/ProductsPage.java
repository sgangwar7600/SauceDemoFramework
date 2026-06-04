package pages;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LocatorUtil;
import utils.PropertyReader;

public class ProductsPage {

    private WebDriver driver;

    private Properties prop;

    private WebDriverWait wait;

    public ProductsPage(WebDriver driver) {

        this.driver = driver;

        prop = PropertyReader
                .getProperties("locators.properties");

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10));
    }

    public void addProductToCart() {

        WebElement addToCartBtn =
                wait.until(
                    ExpectedConditions
                        .elementToBeClickable(
                            LocatorUtil.getLocator(
                                prop.getProperty("addToCart"))));

        addToCartBtn.click();

        WebElement cartBtn =
                wait.until(
                    ExpectedConditions
                        .elementToBeClickable(
                            LocatorUtil.getLocator(
                                prop.getProperty("cart"))));

        cartBtn.click();
    }
}