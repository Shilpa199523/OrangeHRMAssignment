package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;
    
    @FindBy (xpath = "//div[@role='alert']/descendant::p")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        Thread.sleep(5000);
    }
    
    public void validateErrorMessage(String expectedErrorMessage) {
        System.out.println("Validating Error Message...");
    	String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Pass - Expected Error message displayed: "+actualErrorMessage+"");
    }
}
