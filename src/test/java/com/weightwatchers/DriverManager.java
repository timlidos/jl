package com.weightwatchers;

import com.weightwatchers.drivers.DriverFactory;
import com.weightwatchers.drivers.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import java.util.Objects;

public class DriverManager {
    private static WebDriver driver;
    private static EventFiringWebDriver eventFiringWebDriver;

    public static EventFiringWebDriver driver() {
        return eventFiringWebDriver;
    }

    public static void setUpBrowser() {
        driver = DriverFactory.getDriver(DriverType.CHROME);
        Assert.assertNotNull(driver, "Webdriver is not set up!");
        eventFiringWebDriver = new EventFiringWebDriver(driver);
    }

    public static void stopDriver() {
        SessionId sessionId = ((ChromeDriver) driver).getSessionId();
        if (Objects.nonNull(sessionId)) {
            driver.quit();
        }
        driver = null;
    }
}