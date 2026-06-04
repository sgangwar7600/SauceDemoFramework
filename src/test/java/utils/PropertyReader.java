package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    public static Properties getProperties(String fileName) {

        Properties prop = new Properties();

        try {

            FileInputStream fis =
                    new FileInputStream(
                            System.getProperty("user.dir")
                            + "/src/test/resources/"
                            + fileName);

            prop.load(fis);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return prop;
    }
}