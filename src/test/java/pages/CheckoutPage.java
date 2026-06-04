package pages;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LocatorUtil;
import utils.PropertyReader;

public class CheckoutPage {

    private WebDriver driver;
    private Properties prop;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {

        this.driver = driver;
        this.prop = PropertyReader.getProperties("locators.properties");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void completeOrder(String firstName,
                              String lastName,
                              String zipCode) {

        // First Name
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LocatorUtil.getLocator(
                                prop.getProperty("firstname"))));

        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        // Last Name
        WebElement lastNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LocatorUtil.getLocator(
                                prop.getProperty("lastname"))));

        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        // Zip Code
        WebElement zipCodeField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LocatorUtil.getLocator(
                                prop.getProperty("zipcode"))));

        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);

        System.out.println("Before Continue URL: "
                + driver.getCurrentUrl());

        // Continue
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        LocatorUtil.getLocator(
                                prop.getProperty("continueBtn"))));

        continueBtn.click();

        // Wait for Checkout Step Two page
        wait.until(ExpectedConditions.urlContains(
                "checkout-step-two"));

        System.out.println("After Continue URL: "
                + driver.getCurrentUrl());

        // Finish
        WebElement finishBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        LocatorUtil.getLocator(
                                prop.getProperty("finish"))));

        finishBtn.click();
    }

    public boolean isOrderSuccessful() {

        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LocatorUtil.getLocator(
                                prop.getProperty("success"))));

        return successMessage.isDisplayed();
    }
}