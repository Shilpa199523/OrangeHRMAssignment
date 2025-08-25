package com.orangehrm.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.io.*;

import com.orangehrm.base.BaseTest;

public class CommonMethods extends BaseTest{
    WebDriver driver;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }
		
	public void validateCurrentUrl(String expectedUrl) {
			System.out.println("Validating current URL...");
	        String actualUrl = driver.getCurrentUrl();
	        Assert.assertEquals(actualUrl, expectedUrl, "Pass - Expected URL: "+actualUrl+"");
	}
	

	public String captureScreenshot(String testName) {
	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = "test-output/screenshots/";
        new File(screenshotDir).mkdirs(); // Ensure directory exists
        String screenshotPath = screenshotDir + testName + "_" + timestamp + ".png";
	    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    try {
	    	FileHandler.copy(src, new File(screenshotPath));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return screenshotPath;
    }

}
