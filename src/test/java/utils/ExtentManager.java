package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportPath =
                    System.getProperty("user.dir")
                    + "/Output/ExtentReport.html";

            
            //C:\Users\Shashikant\eclipse-workspace-clean\SauceDemoFramework\Output
            
            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config()
                 .setDocumentTitle("Automation Report");

            spark.config()
                 .setReportName("SauceDemo Execution Report");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo(
                    "Framework",
                    "Selenium TestNG");

            extent.setSystemInfo(
                    "Execution",
                    "Grid");

            extent.setSystemInfo(
                    "Author",
                    "Shashikant");
        }

        return extent;
    }
}