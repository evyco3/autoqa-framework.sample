package org.example.framework.pages.account;

import org.apache.commons.lang3.StringUtils;
import org.example.framework.drivers.Driver;
import org.example.framework.factories.PageObjectFactory;
import org.example.framework.pages.BasePage;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage {

    public DashboardPage(){
        waitForPageToLoad(getClass(),"My Account");
    }

    public <T>T setDashboard(String value,Class<T>nextPageClass){
        String val=String.format("//*[@class='block-content']//ul//*[text()='%s']", StringUtils.capitalize(value));
        clickWithJSExecutor(getClass(), Driver.get().findElement(By.xpath(val)),value );
        return PageObjectFactory.createPage(nextPageClass);
    }
}
