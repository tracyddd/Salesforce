package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static utils.TestContextSetup.driver;

public class TestNGListener implements ITestListener {

    // when the test suite was started, it will be triggered
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }


    // When the test case is started, it will be trigger
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    // When the test case failed , it will be trigger
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        System.out.println("Error: " + result.getThrowable());
    }

    // When the test case was skipped, it will be trigger
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }


    // When the test suite is finished, it will trigger
    @Override
    public void onFinish(ITestContext context) {
        //在 onFinish tu use quit() again to ensure exceptional condition, but this is not must condition.
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies(); // 可选
            } catch (Exception ignored) {}
        }
        driver.quit();
        System.out.println("Test Suite Finished: " + context.getName());
    }
}
