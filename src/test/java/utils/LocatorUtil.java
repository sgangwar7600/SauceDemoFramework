package utils;

import org.openqa.selenium.By;

public class LocatorUtil {

    public static By getLocator(String locator) {

        String locatorType =
                locator.split(":")[0];

        String locatorValue =
                locator.split(":")[1];

        switch(locatorType.toLowerCase()) {

            case "id":
                return By.id(locatorValue);

            case "name":
                return By.name(locatorValue);

            case "xpath":
                return By.xpath(locatorValue);

            case "css":
                return By.cssSelector(locatorValue);

            case "classname":
                return By.className(locatorValue);

            case "tagname":
                return By.tagName(locatorValue);

            case "linktext":
                return By.linkText(locatorValue);

            case "partiallinktext":
                return By.partialLinkText(locatorValue);

            default:

                throw new IllegalArgumentException(
                        "Invalid locator type: "
                                + locatorType);
        }
    }
}