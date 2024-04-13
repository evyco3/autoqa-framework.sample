package org.example.framework.pages;

import org.example.framework.drivers.Driver;
import org.example.framework.factories.PageObjectFactory;
import org.openqa.selenium.By;

public class ProductGate extends BasePage{

    public <T> T set(String value1,String value2,Class<T>nextPageClass){
         var val1=String.format("//ol[@class='nav-primary']/li/*[text()='%s']",value1);
         var val2 = String.format("//ol[@class='nav-primary']/li/a[text()='%s']/parent::li//ul//a[text()='%s']", value1, value2);
         moveToElement(getClass(), Driver.get().findElement(By.xpath(val1)), value1);
         clickWithJSExecutor(getClass(),Driver.get().findElement(By.xpath(val2)),value2 );
         return PageObjectFactory.createPage(nextPageClass);
    }

}
