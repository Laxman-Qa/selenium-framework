package utils;

import com.aventstack.extentreports.*;
import org.testng.*;

import test.BaseTest;

public class TestListener extends BaseTest implements ITestListener {

    private static ExtentReports extent = ExtentManager.getExtent();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /* ================= TEST START ================= */

    @Override
    public void onTestStart(ITestResult result) {

    	extentTest.set(extent.createTest(result.getMethod().getMethodName()));


        log.info("Test Started: {}", result.getMethod().getMethodName());
        extentTest.get().log(Status.INFO, "Test Started");
    }

    /* ================= TEST SUCCESS ================= */

    @Override
    public void onTestSuccess(ITestResult result) {

        log.info("Test Passed: {}", result.getMethod().getMethodName());
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    /* ================= TEST FAILURE ================= */

    @Override
    public void onTestFailure(ITestResult result) {

        log.error("Test Failed: {}", result.getMethod().getMethodName());
        log.error(result.getThrowable());

        extentTest.get().log(Status.FAIL, result.getThrowable());
        	
        String screenshotPath =ScreenshotUtil.captureScreenshot( driver,result.getMethod().getMethodName());
                extentTest.get().addScreenCaptureFromPath(screenshotPath);
    }

    /* ================= TEST SKIPPED ================= */

    @Override
    public void onTestSkipped(ITestResult result) {

        log.warn("Test Skipped: {}", result.getMethod().getMethodName());
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    /* ================= FINISH ================= */

    @Override
    public void onFinish(ITestContext context) {

        log.info("Flushing Extent Report");
        extent.flush();
        extentTest.remove();
    }
}
