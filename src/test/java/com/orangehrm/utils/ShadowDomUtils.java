package com.orangehrm.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShadowDomUtils {
    
    /**
     * Finds element within Shadow DOM using JavaScript
     * @param driver WebDriver instance
     * @param hostElement Shadow host element
     * @param shadowSelector CSS selector for element within shadow DOM
     * @return WebElement found within shadow DOM
     */
    public static WebElement findElementInShadowDOM(WebDriver driver, WebElement hostElement, String shadowSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript(
            "return arguments[0].shadowRoot.querySelector(arguments[1])",
            hostElement,
            shadowSelector
        );
    }

    /**
     * Finds multiple elements within Shadow DOM using JavaScript
     * @param driver WebDriver instance
     * @param hostElement Shadow host element
     * @param shadowSelector CSS selector for elements within shadow DOM
     * @return List of WebElements found within shadow DOM
     */
    public static List<WebElement> findElementsInShadowDOM(WebDriver driver, WebElement hostElement, String shadowSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (List<WebElement>) js.executeScript(
            "return arguments[0].shadowRoot.querySelectorAll(arguments[1])",
            hostElement,
            shadowSelector
        );
    }

    /**
     * Finds element within nested Shadow DOM using JavaScript
     * @param driver WebDriver instance
     * @param selectors Array of selectors to traverse through shadow DOMs
     * @return WebElement found within nested shadow DOM
     */
    public static WebElement findElementInNestedShadowDOM(WebDriver driver, String... selectors) {
        if (selectors.length == 0) {
            throw new IllegalArgumentException("At least one selector must be provided");
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        StringBuilder script = new StringBuilder("document");
        
        for (int i = 0; i < selectors.length - 1; i++) {
            script.append(".querySelector('")
                  .append(selectors[i])
                  .append("').shadowRoot");
        }
        
        script.append(".querySelector('")
              .append(selectors[selectors.length - 1])
              .append("')");

        return (WebElement) js.executeScript("return " + script.toString());
    }

    /**
     * Waits for element within Shadow DOM to be visible
     * @param driver WebDriver instance
     * @param hostElement Shadow host element
     * @param shadowSelector CSS selector for element within shadow DOM
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return WebElement found within shadow DOM
     */
    public static WebElement waitForShadowElement(WebDriver driver, WebElement hostElement, 
            String shadowSelector, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        
        return wait.until(driver1 -> {
            WebElement element = findElementInShadowDOM(driver1, hostElement, shadowSelector);
            if (element != null && element.isDisplayed()) {
                return element;
            }
            return null;
        });
    }

    /**
     * Clicks element within Shadow DOM using JavaScript
     * @param driver WebDriver instance
     * @param hostElement Shadow host element
     * @param shadowSelector CSS selector for element within shadow DOM
     */
    public static void clickShadowElement(WebDriver driver, WebElement hostElement, String shadowSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].shadowRoot.querySelector(arguments[1]).click()",
            hostElement,
            shadowSelector
        );
    }

    /**
     * Sends keys to element within Shadow DOM using JavaScript
     * @param driver WebDriver instance
     * @param hostElement Shadow host element
     * @param shadowSelector CSS selector for element within shadow DOM
     * @param keys Text to send to element
     */
    public static void sendKeysToShadowElement(WebDriver driver, WebElement hostElement, 
            String shadowSelector, String keys) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].shadowRoot.querySelector(arguments[1]).value = arguments[2]",
            hostElement,
            shadowSelector,
            keys
        );
    }

    /**
     * Gets text from element within Shadow DOM using JavaScript
     * @param driver WebDriver instance
     * @param hostElement Shadow host element
     * @param shadowSelector CSS selector for element within shadow DOM
     * @return Text content of the shadow DOM element
     */
    public static String getShadowElementText(WebDriver driver, WebElement hostElement, String shadowSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(
            "return arguments[0].shadowRoot.querySelector(arguments[1]).textContent",
            hostElement,
            shadowSelector
        );
    }

    /**
     * Checks if element within Shadow DOM exists
     * @param driver WebDriver instance
     * @param hostElement Shadow host element
     * @param shadowSelector CSS selector for element within shadow DOM
     * @return true if element exists, false otherwise
     */
    public static boolean shadowElementExists(WebDriver driver, WebElement hostElement, String shadowSelector) {
        try {
            WebElement element = findElementInShadowDOM(driver, hostElement, shadowSelector);
            return element != null;
        } catch (Exception e) {
            return false;
        }
    }
}
