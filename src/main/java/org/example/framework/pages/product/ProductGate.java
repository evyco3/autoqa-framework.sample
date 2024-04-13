package org.example.framework.pages.product;

import org.apache.commons.lang3.StringUtils;
import org.example.framework.drivers.Driver;
import org.example.framework.factories.PageObjectFactory;
import org.example.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductGate extends BasePage {

    public <T> T navigateToProductSection(String mainCategory, String subCategory, Class<T>nextPageClass){
         var mainLocator=String.format("//ol[@class='nav-primary']/li/*[text()='%s']", StringUtils.capitalize(mainCategory));
         var subLocator = String.format("//ol[@class='nav-primary']/li/a[text()='%s']/parent::li//ul//a[text()='%s']", StringUtils.capitalize(mainCategory), StringUtils.capitalize(subCategory));
         moveAndClickProductNavLink(mainLocator,subLocator,mainCategory,subCategory);
         return PageObjectFactory.createPage(nextPageClass);
    }
    private void moveAndClickProductNavLink(String mainLocator,String subLocator,String mainLink,String subLink){
        WebElement mainLoc=Driver.get().findElement(By.xpath(mainLocator));
        WebElement subLoc=Driver.get().findElement(By.xpath(subLocator));
        moveToElement(getClass(),mainLoc,mainLink);
        clickWithJSExecutor(getClass(),subLoc,subLink);
    }

}
