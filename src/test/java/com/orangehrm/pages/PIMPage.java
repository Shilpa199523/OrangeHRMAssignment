package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PIMPage {
	WebDriver driver;

	
     By PIMMenu= By.xpath("//span[text() = 'PIM']");
	
	By PIMADD= By.xpath("//button[contains(., 'Add')]");
	
	
	By PIMFN= By.name("firstName");
	By PIMMN= By.name("middleName");
	By PIMLN= By.name("lastName");
	By attvalue= By.xpath("//*[text()='Employee Id']/parent::div/following-sibling::div/input");
	By empserachvalue = By.xpath("//*[text()='Employee Id']/parent::div/following-sibling::div/input");
	By PIMsave=By.xpath("//*[@type='submit']");
	 By EMplist = By.xpath("//*[text()='Employee List']");
	
	// By searcgempid = By.xpath("//*[text()='Employee Id']/parent::div/following-sibling::div/input");
	
		By searchsubmit=By.xpath("//*[@type='submit']");
		
		
	public PIMPage(WebDriver driver)
	{
		this.driver = driver;
}
	
	
	
	//Actions
	public void NavPIM() {
		driver.findElement(PIMMenu).click();
		
	}
	
	public void PIMclick() {
		driver.findElement(PIMADD).click();
	}
		
		public void EMpdetails(String fname, String mname,String lname) {
			driver.findElement(PIMFN).sendKeys(fname);
			driver.findElement(PIMMN).sendKeys(mname);
			driver.findElement(PIMLN).sendKeys(lname);
		}
		
		public void savedetails() {
			driver.findElement(PIMsave).click();;
		}
		public void emplist() {
			driver.findElement(EMplist).click();
		}
		public String attval() {
			return driver.findElement(attvalue).getAttribute("value");
		}
		
		public void submitsearch() {
			driver.findElement(searchsubmit).click();
		}
		
		public String Getgenerated () {
			return driver.findElement(attvalue).getAttribute("value");
		}
		public void searchempidinemplist(String empid) {
			driver.findElement(empserachvalue).sendKeys(empid);
		}
		
		
		public void searchByname(String employeeid) {
			driver.findElement(EMplist);
		}
		
		 
		
	
public boolean isemppresent(String empid) {
	WebElement emprow = driver.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']"));
	return emprow.isDisplayed();
	
			
}
}
	
	


