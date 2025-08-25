package com.orangehrm.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.orangehrm.utils.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest extentTest;
    
    @BeforeClass
    public void launchBrowser() {
        System.out.println("Launching browser...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
    }
    
    @BeforeMethod
    public void setup() {
    	System.out.println("Navigating to login page...");
        driver.get("https://opensource-demo.orangehrmlive.com/");
        
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
    	System.out.println("Resetting state...");
    	
    }
    
    @AfterClass
    public void closeBrowser() {
        System.out.println("Closing browser...");
        driver.quit();
    }


}
