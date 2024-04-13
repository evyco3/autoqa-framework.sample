package org.example.framework.utils;


import io.qameta.allure.Allure;
import org.example.framework.drivers.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;

public class CustomTestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        Object testInstance = tr.getInstance();
        WebDriver driver = Driver.get();
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
        }
    }
}