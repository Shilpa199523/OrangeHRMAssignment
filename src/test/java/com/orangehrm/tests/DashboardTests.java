package com.orangehrm.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.CommonMethods;

@Listeners(com.orangehrm.utils.TestListener.class)
public class DashboardTests extends BaseTest{
    @Test(dataProvider = "loginData")
    public void TC03_LoginValidCredentials(String username, String password) throws InterruptedException {
    	LoginPage loginPage = new LoginPage(driver);
    	CommonMethods commonMethods = new CommonMethods(driver);
    	loginPage.login(username, password);
    	commonMethods.validateCurrentUrl("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    	//System.out.println("After Assertion of home page current url");
    }
    
	@DataProvider(name = "loginData")
	public Object[][] getData1() {
		return new Object[][] {
			{"Admin", "admin123"}
		};
	}
    
	@Test(dependsOnMethods = "TC03_LoginValidCredentials")
	public void TC04_verifyProfilePicOnDashboard() throws InterruptedException {
		DashboardPage dashboardPage = new DashboardPage(driver);
		System.out.println("Profile Picture Verification...");
		Assert.assertTrue(dashboardPage.getProfilePic().isDisplayed());
	}

	@Test(dependsOnMethods = "TC03_LoginValidCredentials")
	public void TC05_verifyContentsOfSidePanel() throws InterruptedException {
		System.out.println("Side Panel menu items Verification...");
		
		DashboardPage dashboardPage = new DashboardPage(driver);
		Assert.assertTrue(dashboardPage.getSearchTextBox().isDisplayed(), "Search Text box is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("Admin").isDisplayed(), "Admin menu is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("PIM").isDisplayed(), "PIM menu is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("Leave").isDisplayed(), "Leave menu is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("Time").isDisplayed(), "Time menu is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("Recruitment").isDisplayed(), "Recruitment menu is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("My Info").isDisplayed(), "My Info menu is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("Dashboard").isDisplayed(), "Dashboard menu is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("Directory").isDisplayed(), "Directory menu is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("Maintenance").isDisplayed(), "Maintenance menu is displayed");
		Assert.assertTrue(dashboardPage.getSidePanelItem("Claim").isDisplayed(), "Claim menu is displayed");
	}
	
	@Test(dependsOnMethods = "TC03_LoginValidCredentials")
	public void TC06_LogOut() throws InterruptedException {
		System.out.println("Logging out...");
		DashboardPage dashboardPage = new DashboardPage(driver);
		CommonMethods commonMethods = new CommonMethods(driver);
		
		dashboardPage.clickOnLogout();
		commonMethods.validateCurrentUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
}

