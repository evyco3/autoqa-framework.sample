package org.example.framework.pages.footer;

import org.example.framework.drivers.Driver;
import org.example.framework.factories.PageObjectFactory;
import org.example.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class FooterGate extends BasePage {

    public <T> T navigateToFooterSection(String sectionName,Class<T> nextPageClass){
        var linkLocator=String.format("//*[@class='footer']//a[text()='%s']",sectionName);
        clickFooterNavLink(linkLocator,sectionName);
        return PageObjectFactory.createPage(nextPageClass);
    }

    private void clickFooterNavLink(String locator,String linkName){
        WebElement  footerLink= Driver.get().findElement(By.xpath(locator));
        clickWithJSExecutor(getClass(),footerLink,linkName+" Navigation Link");

    }

}
