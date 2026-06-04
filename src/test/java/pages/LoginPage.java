package pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import utils.LocatorUtil;
import utils.PropertyReader;

public class LoginPage {

    private WebDriver driver;

    private Properties prop;

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        prop = PropertyReader
                .getProperties("locators.properties");
    }

    public void login(String username, String password) {

        driver.findElement(
                LocatorUtil.getLocator(
                        prop.getProperty("username")))
                .sendKeys(username);

        driver.findElement(
                LocatorUtil.getLocator(
                        prop.getProperty("password")))
                .sendKeys(password);

        driver.findElement(
                LocatorUtil.getLocator(
                        prop.getProperty("loginBtn")))
                .click();
    }
}