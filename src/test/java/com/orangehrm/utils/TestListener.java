package com.orangehrm.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.orangehrm.base.BaseTest;


public class TestListener extends BaseTest implements ITestListener {
	
	  private static ExtentReports extent = ExtentManager.getInstance(); private
	  static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	public void onTestSuccess(ITestResult result) {
		WebDriver driver = ((BaseTest) result.getInstance()).driver;
		CommonMethods cm = new CommonMethods(driver);
		String screenshotPath = cm.captureScreenshot(result.getMethod().getMethodName());
		String relativePath = "./screenshots/" + new File(screenshotPath).getName();
		try {
			test.get().pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
		} catch (Exception e) {
			test.get().pass("Test Passed - Screenshot not available");
		}
	}

	public void onTestFailure(ITestResult result) {
		WebDriver driver = ((BaseTest) result.getInstance()).driver;
		CommonMethods cm = new CommonMethods(driver);
		String screenshotPath = cm.captureScreenshot(result.getMethod().getMethodName());
		String relativePath = "./screenshots/" + new File(screenshotPath).getName();
		try {
			test.get().fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
		} catch (Exception e) {
			test.get().fail(result.getThrowable());
		}
	}

	public void onTestSkipped(ITestResult result) {
		test.get().skip(result.getThrowable());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
