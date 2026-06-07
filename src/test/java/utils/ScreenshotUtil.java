package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(
            WebDriver driver,
            String testName) {

        String destination =
                System.getProperty("user.dir")
                + "/test-output/screenshots/"
                + testName + ".png";

        File src =
                ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File dest = new File(destination);

        try {

            dest.getParentFile().mkdirs();

            Files.copy(
                    src.toPath(),
                    dest.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return destination;
    }
}