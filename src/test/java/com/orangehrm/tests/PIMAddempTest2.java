package com.orangehrm.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utils.CommonMethods;
import com.orangehrm.utils.ExcelUtils;
@Listeners(com.orangehrm.utils.TestListener.class)

public class PIMwithExcel extends BaseTest{

   
	  @Test(dataProvider = "loginData")
	
	    public void TC05_LoginValidCredentials(String username, String password) throws InterruptedException {
	    	LoginPage loginPage = new LoginPage(driver);
	    	CommonMethods commonMethods = new CommonMethods(driver);
	    	loginPage.login(username, password);
	    	commonMethods.validateCurrentUrl("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
	    	
	    }
	    
		@DataProvider(name = "loginData")
		public Object[][] getData1() {
			return new Object[][] {
				{"Admin", "admin123"}
			};
		}
		
		@DataProvider(name ="empData")
		public Object[][] getEmpData(){
			return ExcelUtils.getEmpData();
		}
		
		
  
		@Test(dataProvider="empData" ,dependsOnMethods = "TC05_LoginValidCredentials")
		public void addEmployeeTest(String Firstname,String MiddleName,String Lastname) throws InterruptedException  {
			
			
			PIMPage PIMpagei = new PIMPage(driver);
			
			PIMpagei.NavPIM();//navigate to PIM
			PIMpagei.PIMclick(); // CLicking on Add
			Thread.sleep(6000);
	
			PIMpagei.EMpdetails(Firstname, MiddleName, Lastname);
			String empid = PIMpagei.Getgenerated();
			PIMpagei.savedetails();
			Thread.sleep(3000);
			PIMpagei.emplist();
			PIMpagei.searchempidinemplist(empid);
			PIMpagei.submitsearch();
			//Thread.sleep(4000);
			
			//Assert.assertTrue(PIMpagei.isemppresent(empid),"notfound");
			JavascriptExecutor jascr = (JavascriptExecutor) driver;
			
			//jascr.executeScript("arguments[0].scrollIntoView(true);", PIMpagei.scroll());
			jascr.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(3000);
		}
		
}	
		
		
		

	  
 
	
 


