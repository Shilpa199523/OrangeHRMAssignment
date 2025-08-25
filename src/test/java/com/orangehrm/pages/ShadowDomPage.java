package com.orangehrm.pages;

import com.orangehrm.utils.ShadowDomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShadowDomPage {
    private WebDriver driver;

    @FindBy(css = "#shadow-host")
    private WebElement shadowHost;

    public ShadowDomPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Example method to interact with a button in shadow DOM
     */
    public void clickShadowButton() {
        ShadowDomUtils.clickShadowElement(driver, shadowHost, "#shadow-button");
    }

    /**
     * Example method to input text in shadow DOM
     */
    public void enterTextInShadowInput(String text) {
        ShadowDomUtils.sendKeysToShadowElement(driver, shadowHost, "#shadow-input", text);
    }

    /**
     * Example method to get text from shadow DOM
     */
    public String getShadowText() {
        return ShadowDomUtils.getShadowElementText(driver, shadowHost, "#shadow-text");
    }

    /**
     * Example method to interact with nested shadow DOM
     */
    public void clickNestedShadowElement() {
        WebElement nestedElement = ShadowDomUtils.findElementInNestedShadowDOM(
            driver,
            "#outer-shadow-host",
            "#inner-shadow-host",
            "#nested-button"
        );
        nestedElement.click();
    }

    /**
     * Example method to wait for shadow DOM element
     */
    public WebElement waitForShadowElement() {
        return ShadowDomUtils.waitForShadowElement(
            driver,
            shadowHost,
            "#loading-element",
            10
        );
    }

    /**
     * Example method to check if shadow DOM element exists
     */
    public boolean isShadowElementPresent() {
        return ShadowDomUtils.shadowElementExists(driver, shadowHost, "#target-element");
    }
}
