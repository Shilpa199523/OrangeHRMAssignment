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
public class LoginTestWithDataProvider extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelUtils.getLoginData();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        
        loginPage.login(username, password);
        
        Thread.sleep(5000);
        
        //extentTest.info("Testing login with username: " + username);
        
        if (username.equals("Admin") && password.equals("admin123")) {
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), 
                "Dashboard URL not found after login");
            //extentTest.pass("Login successful with valid credentials");
            dashboardPage.clickOnLogout();
        } else {
            Assert.assertTrue(driver.getCurrentUrl().contains("login"), 
                "Still on login page after invalid credentials");
           // extentTest.pass("Login failed as expected with invalid credentials");
        }
    }

}
