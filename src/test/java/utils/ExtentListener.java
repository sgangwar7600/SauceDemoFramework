package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentListener
        implements ITestListener {

    private static ExtentReports extent =
            ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(
            ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getMethod()
                              .getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        test.get().fail(result.getThrowable());

        String screenshotPath =
                ScreenshotUtil.captureScreenshot(
                        DriverFactory.getDriver(),
                        result.getMethod()
                              .getMethodName());

        try {

            test.get()
                .addScreenCaptureFromPath(
                        screenshotPath);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(
            ITestContext context) {

        extent.flush();
    }
}