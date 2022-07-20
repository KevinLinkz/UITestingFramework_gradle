package com.UIautomation.listener;

import com.UIautomation.factory.ExtendReportSetup;
import com.UIautomation.factory.ExtendTestFactory;
import com.UIautomation.factory.WebDriverFactory;
import com.UIautomation.service.IOService;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestNGListener implements ITestListener {
    static ExtentReports extentReports;
    ExtentTest extentTest;
    WebDriver webDriver;

    @Override
    public synchronized void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        webDriver = WebDriverFactory.getInit().initWebDriver("chrome");

        ExtendTestFactory.getInit().setExtentTest(extentTest);
        WebDriverFactory.getInit().setWebDriver(webDriver);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtendTestFactory.getInit().getExtentTest().log(Status.PASS, result.getName() + " Test Passed");
        WebDriverFactory.getInit().teardown();

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL, result.getName() + " Test Failed<br/>" + result.getThrowable());
        WebDriverFactory.getInit().teardown();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }



    @Override
    public void onStart(ITestContext context) {
        cleanChromeDriver();
        IOService.getInit().initFolder();
        extentReports = ExtendReportSetup.getInit().setUp();
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        ExtendTestFactory.getInit().removeExtentTest();

    }

    private void cleanChromeDriver(){
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
            Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
