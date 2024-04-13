package org.example.framework.pages;

import org.example.framework.drivers.Driver;
import org.example.framework.factories.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * A single method that will handle the entire account dropdown links
 */


public class AccountGate extends BasePage{
    @FindBy(css = "a[data-target-element*='account']")
    private WebElement accountDropdownBtn;

    public <T> T set(String accountValue, Class<T>nextPageClass){
        clickWithJSExecutor(getClass(),this.accountDropdownBtn,"Account dropdown button");
        var value=String.format("//*[@id='header-account']//a[text()='%s']",accountValue);
        clickWithJSExecutor(getClass(), Driver.get().findElement(By.xpath(value)),accountValue );
        return PageObjectFactory.createPage(nextPageClass);
    }

}

