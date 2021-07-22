package com.weightwatchers.pages;

import com.weightwatchers.base.BaseWorkspacePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class FindWorkshopResultPage extends BaseWorkspacePage {

    @FindBy(xpath = "//div[contains(@class, 'addressContainer')]//h1")
    protected WebElement locationTitle;

    @FindBy(xpath = "//div[contains(@class, 'hours')]//div[contains(@class, 'title')]")
    protected WebElement businessHoursLabel;

    public FindWorkshopResultPage() {
        super(driver());
    }

    public String getLocationTitle() {
        return locationTitle.getText();
    }

    public FindWorkshopResultPage clickBusinessHoursLabel() {
        waitForVisibility(businessHoursLabel);
        businessHoursLabel.click();
        return this;
    }

    public List<String> getBusinessHours() {
        String day;
        String time;
        List<String> result = new ArrayList<>();
        String xPath = "//div[contains(@class, 'hoursWrapper')]//div[contains(@class, 'day-')]";
        List<WebElement> days = driver().findElements(By.xpath(xPath));
        for (WebElement element : days) {
            day = element.findElement(By.xpath("./div[contains(@class, 'dayName')]")).getAttribute("innerText");
            time = element.findElement(By.xpath("./div[contains(@class, 'times')]")).getAttribute("innerText");
            result.add(day + "\n" + time);
        }
        return result;
    }
}