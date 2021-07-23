package com.weightwatchers.pages;

import com.weightwatchers.base.BaseWorkspacePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class FindWorkshopResultPage extends BaseWorkspacePage {

    @FindBy(className = "locationName-1jro_")
    protected WebElement locationTitle;

    @FindBy(className = "title-3o8Pv")
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
        String className = "day-CZkCD";
        List<WebElement> days = driver().findElements(By.className(className));
        for (WebElement element : days) {
            day = driver().findElement(By.className("dayName-CTNC6")).getAttribute("innerText");
            time = driver().findElement(By.className("times-fms3v")).getAttribute("innerText");
            result.add(day + "\n" + time);
        }
        return result;
    }
}