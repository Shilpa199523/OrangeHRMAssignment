package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class DashboardPage extends com.orangehrm.base.BaseTest{
	
	private By searchTextBox = By.xpath("//input[@placeholder='Search']");
	private By headerText = By.xpath("//div[@class='oxd-topbar-header']/descendant::h6");
	private By brandLogo = By.xpath("//img[@alt='client brand banner']");
	private By getProfilePic = By.xpath("//div[@class='oxd-topbar-header']/descendant::img[@alt='profile picture']");
	private By sidePanelCollapseButton = By.xpath("//nav[@aria-label='Sidepanel']/descendant::button");
	private By userDropdownIcon = By.xpath("//i[contains(@class,'userdropdown-icon')]");

	private By logOutButton = By.xpath("//a[.='Logout']");
	
    public DashboardPage(WebDriver driver) {
		this.driver = driver;
    }
	
	public WebElement getHeaderText() {
		return driver.findElement(headerText);
	}

	public WebElement getBrandLogo() {

		return driver.findElement(brandLogo);
	}

	public WebElement getProfilePic() {
		return driver.findElement(getProfilePic);
	}

	public WebElement getSidePanelCollapseButton() {
		return driver.findElement(sidePanelCollapseButton);
	}

    public WebElement getSearchTextBox() {
		return driver.findElement(searchTextBox);
	}

    public WebElement getSidePanelItem(String MenuItemText) {
    	return driver.findElement(By.xpath("//nav[@aria-label='Sidepanel']/descendant::span[.='"+MenuItemText+"']"));
    }

	public WebElement getUserDropDownIcon(){
		return driver.findElement(userDropdownIcon);
	}

	public WebElement getLogOutButton() {
		return driver.findElement(logOutButton);
	}

	public void clickOnLogout() throws InterruptedException {
		getUserDropDownIcon().click();
		Thread.sleep(3000);
		getLogOutButton().click();
	}
}

