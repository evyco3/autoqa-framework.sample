package org.example.framework.pages;

import org.example.framework.drivers.Driver;
import org.example.framework.utils.ActionExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    protected void click(Class<?> Tclass, WebElement element, String elementName) {
        ActionExecutor.execAction(Tclass,
                () -> waitForElementToBeVisible(element).click(),
                "Clicked on " + elementName,
                "Failed to click on " + elementName
        );
    }
    protected void sendKeys(Class<?> Tclass, WebElement element, String value, String elementName) {
        ActionExecutor.execAction(Tclass, () -> {
            WebElement visibleElement = waitForElementToBeVisible(element);
            // Clear existing text in the input field
            visibleElement.clear();
            // Send new keys to the input field
            visibleElement.sendKeys(value);
        }, "Sent keys to " + elementName + ":" + value, "Failed to send keys to " + elementName);
    }


    protected String getText(Class<?> Tclass, WebElement element, String elementName) {
        return ActionExecutor.execFunction(
                Tclass,
                () -> waitForElementToBeVisible(element).getText().toLowerCase().trim(),
                "Got Text from " + elementName + ": " + element.getText(),
                "Failed to get text from " + elementName
        );
    }

    protected void clickWithJSExecutor(Class<?> Tclass, WebElement element, String elementName) {
        WebElement clickableElement = waitForElementToBeVisible(element);
        ActionExecutor.execAction(Tclass, () -> {
            JavascriptExecutor executor = (JavascriptExecutor) Driver.get();
            executor.executeScript("arguments[0].click();", clickableElement);
        }, "Clicked on " + elementName + " using JavaScript executor", "Failed to click on " + elementName + " using JavaScript executor");
    }

    protected String getPageTitle(Class<?> Tclass) {
        return ActionExecutor.execFunction(Tclass, () ->
                        Driver.get().getTitle(),
                "Page Title: " + Driver.get().getTitle(),
                "Failed to return page title"
        );
    }

    protected void moveToElement(Class<?> Tclass, WebElement element, String elementName) {
        ActionExecutor.execAction(Tclass, () ->
                        new Actions(Driver.get()).moveToElement(waitForElementToBeVisible(element)).perform(),
                "Moved to " + elementName,
                "Failed to move to " + elementName
        );
    }
    protected String getTextUsingJavaScript(Class<?> Tclass, WebElement element, String elementName) {
        WebElement visibleElement = waitForElementToBeVisible(element);
        return ActionExecutor.execFunction(
                Tclass,
                () -> {
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.get();
                    String text = (String) jsExecutor.executeScript("return arguments[0].textContent", visibleElement);
                    return text.trim().toLowerCase();
                },
                "Got Text from " + elementName + " using JavaScriptExecutor: " + element.getText(),
                "Failed to get text from " + elementName + " using JavaScriptExecutor"
        );
    }
    public String getTitleUsingJavaScript(Class<?> Tclass) {
        return ActionExecutor.execFunction(
                Tclass,
                () -> {
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.get();
                    return (String) jsExecutor.executeScript("return document.title");
                },
                "Page Title: " + Driver.get().getTitle(),
                "Failed to get page title using JavaScriptExecutor"
        );
    }
    protected String getCurrentUrlUsingActionExecutor(Class<?> Tclass) {
        return ActionExecutor.execFunction(
                Tclass,
                () -> {
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.get();
                    return (String) jsExecutor.executeScript("return window.location.href");
                },
                "Current URL: " + Driver.get().getCurrentUrl(),
                "Failed to get current URL"
        );
    }
    protected void selectByVisibleText(Class<?> Tclass, WebElement element, String visibleText, String elementName) {
        ActionExecutor.execAction(Tclass, () -> {
                    Select dropdown = new Select(waitForElementToBeVisible(element));
                    dropdown.selectByVisibleText(visibleText);
                }, "Selected '" + visibleText + "' from " + elementName,
                "Failed to select '" + visibleText + "' from " + elementName);
    }

    protected void waitForPageToLoad(Class<?> Tclass,String title) {
        ActionExecutor.execAction(getClass(), () -> {
            new WebDriverWait(Driver.get(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.titleIs(title));
        }, Tclass+"Page loaded successfully", "Failed to load page");
    }


    private WebElement waitForElementToBeVisible(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(Driver.get())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }



}
