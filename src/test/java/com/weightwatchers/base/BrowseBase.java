package com.weightwatchers.base;

import com.weightwatchers.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public abstract class BrowseBase {

    /**
     * Default timeout for waiting element changing property.
     */
    private static final int ELEMENT_TIMEOUT_SECONDS = 10;

    protected String baseUrl;

    /**
     * Selenium web driver, which implements EventFiringWebDriver interface.
     *
     * @return web driver.
     */
    protected static EventFiringWebDriver driver() {
        return DriverManager.driver();
    }

    /**
     * Wait for element visibility.
     *
     * @param element is web element to wait for visibility.
     */
    public static void waitForVisibility(WebElement element) {
        waitForVisibility(element, ELEMENT_TIMEOUT_SECONDS);
    }

    /**
     * Wait for element visibility.
     *
     * @param element is web element to wait for visibility.
     * @param timeout is timeout in seconds to wait for element.
     */
    protected static void waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @BeforeMethod
    public void setUp() {
        DriverManager.setUpBrowser();
        baseUrl = "https://www.weightwatchers.com/us/find-a-workshop";
    }

    @AfterMethod
    public void createScreenshot() {
        File directory = new File("screenshots/");
        if (!directory.exists())
            directory.mkdir();
        Date date = new Date();
        String uniqueString = String.valueOf(date.getTime());
        String filepath = "screenshots/" + uniqueString + ".png";
        Screenshot screen1 = new AShot().takeScreenshot(driver());
        try {
            ImageIO.write(screen1.getImage(), "PNG", new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try (InputStream is = Files.newInputStream(Paths.get(filepath))) {
                Allure.addAttachment("Screenshot", is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (File file : directory.listFiles()) {
            file.delete();
        }
        close();
    }

    public void close() {
        DriverManager.stopDriver();
    }
}