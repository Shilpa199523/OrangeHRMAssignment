package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;


@Listeners(com.orangehrm.utils.TestListener.class)

public class LoginTestWithExcelData extends BaseTest {

     @Test
    public void testValidLoginWithExcel() throws InterruptedException {
        Map<String, String> credentials = ExcelUtils.getLoginCredentials("validLogin");
        LoginPage loginPage = new LoginPage(driver); 
        DashboardPage dashboardPage = new DashboardPage(driver);
        
        //extentTest.info("Testing valid login with credentials from Excel");
        loginPage.login(credentials.get("username"), credentials.get("password"));
        
        Thread.sleep(5000);
        
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), 
            "Dashboard URL not found after login");
        dashboardPage.clickOnLogout();
    }

    @Test
    public void testInvalidLoginWithExcel() throws InterruptedException {
        Map<String, String> credentials = ExcelUtils.getLoginCredentials("invalidLogin");
        LoginPage loginPage = new LoginPage(driver);
        
        //extentTest.info("Testing invalid login with credentials from Excel");
        loginPage.login(credentials.get("username"), credentials.get("password"));
        
        Thread.sleep(5000);
        
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), 
            "Still on login page after invalid credentials");
    }
}
