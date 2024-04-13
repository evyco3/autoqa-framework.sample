package org.example.framework.pages.account;

import org.apache.commons.lang3.StringUtils;
import org.example.framework.drivers.Driver;
import org.example.framework.factories.PageObjectFactory;
import org.example.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * A single method that will handle the entire account dropdown links
 */


public class AccountGate extends BasePage {
    @FindBy(css = "a[data-target-element*='account']")
    private WebElement accountDropdownBtn;

    public <T> T navigateToAccountDropdown(String accountSection, Class<T>nextPageClass){
        clickWithJSExecutor(getClass(),this.accountDropdownBtn,"Account dropdown button");
        var linkLocator=String.format("//*[@id='header-account']//a[text()='%s']",StringUtils.capitalize(accountSection));
        clickAccountNavLink(linkLocator,accountSection);
        return PageObjectFactory.createPage(nextPageClass);
    }
    public void clickAccountNavLink(String locator,String linkName){
        WebElement element=Driver.get().findElement(By.xpath(locator));
        clickWithJSExecutor(getClass(),element,linkName+"Navigation Link");
    }



}

