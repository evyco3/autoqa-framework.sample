package org.example.framework.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.EnumMap;
import java.util.function.Supplier;

public final class DriverFactory {

    private static final EnumMap<BrowserType, Supplier<WebDriver>> DRIVER_MAP = new EnumMap<>(BrowserType.class);

    private DriverFactory() {
    }

    static {
        DRIVER_MAP.put(BrowserType.CHROME, () -> {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        });
        DRIVER_MAP.put(BrowserType.FIREFOX, () -> {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(options);
        });
        DRIVER_MAP.put(BrowserType.EDGE, () -> {
           EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(options);
        });

        DRIVER_MAP.put(BrowserType.IE, () -> {
            WebDriverManager.iedriver().setup();
            InternetExplorerOptions options = new InternetExplorerOptions();
            WebDriverManager.iedriver().setup();;
            return new InternetExplorerDriver(options);
        });

    }

    public static WebDriver getDriver(BrowserType browserType){
        return DRIVER_MAP.get(browserType).get();
    }


    public enum BrowserType{
        CHROME,FIREFOX,EDGE,IE,SAFARI
    }
}
