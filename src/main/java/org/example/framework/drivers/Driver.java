package org.example.framework.drivers;

import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Objects;

public final class Driver {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    private Driver() {
    }

    public static void initDriver() {
        if (Objects.isNull(get())) {
            WebDriver driver = DriverFactory.getDriver(DriverFactory.BrowserType.CHROME);
            set(driver);
           get().get("https://ecommerce.tealiumdemo.com/");
        }
    }

    public static void quit() {
        if (Objects.nonNull(get())) {
            get().quit();
            remove();
        }
    }

    public static WebDriver get() {
        return DRIVER_THREAD_LOCAL.get();
    }

    private static void set(WebDriver driver) {
        DRIVER_THREAD_LOCAL.set(driver);
    }

    private static void remove() {
        DRIVER_THREAD_LOCAL.remove();
    }
}
