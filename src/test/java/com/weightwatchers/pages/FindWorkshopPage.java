package com.weightwatchers.pages;

import com.weightwatchers.base.BaseWorkspacePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindWorkshopPage extends BaseWorkspacePage {

    @FindBy(className = "studioIcon-2TdMR")
    protected WebElement studioLabel;

    @FindBy(id = "location-search")
    protected WebElement searchTextBox;

    @FindBy(className = "resultContainer-m4u0m")
    protected WebElement resultsBlock;

    @FindBy(className = "linkUnderline-1_h4g")
    protected WebElement firstResultTitle;

    @FindBy(className = "distance-OhP63")
    protected WebElement firstResultDistance;

    public FindWorkshopPage() {
        super(driver());
    }

    @Step("Get page title")
    public String getPageTitle() {
        return driver().getTitle().replaceAll(" ", " ");
    }

    @Step("Click studio label")
    public FindWorkshopPage clickStudioLabel() {
        waitForVisibility(studioLabel);
        studioLabel.click();
        return this;
    }

    @Step("Type into search text box {value}")
    public void typeIntoSearchTextBox(String value) {
        waitForVisibility(searchTextBox);
        searchTextBox.sendKeys(value);
    }

    @Step("Search for {value}")
    public FindWorkshopPage searchFor(String value) {
        typeIntoSearchTextBox(value);
        searchTextBox.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Get closest distance")
    public String getClosestDistance() {
        waitForVisibility(resultsBlock);
        List<WebElement> results = driver().findElements(By.className("distance-OhP63"));
        List<String> distances = new ArrayList<>();
        for (WebElement element : results) {
            distances.add(element.getText());
        }
        List<Double> distancesInMiles = new ArrayList<>();
        for (String s : distances) {
            distancesInMiles.add(Double.parseDouble(s.replaceAll(" mi", "")));
        }
        Collections.sort(distancesInMiles);
        return String.valueOf(distancesInMiles.get(0));
    }

    @Step("Get closest result title")
    public String getClosestDistanceTitle(String distance) {
        String xPath = "//span[contains(., '" + distance + "')]/..//a[@class='linkUnderline-1_h4g']";
        WebElement closestDistanceTitle = driver().findElement(By.xpath(xPath));
        return closestDistanceTitle.getText();
    }

    @Step("Click closest result title")
    public FindWorkshopResultPage clickClosestResultTitle() {
        String closestDistance = getClosestDistance();
        String xPath = "//span[contains(., '" + closestDistance + "')]/..//a[@class='linkUnderline-1_h4g']";
        WebElement closestDistanceTitle = driver().findElement(By.xpath(xPath));
        waitForVisibility(closestDistanceTitle);
        closestDistanceTitle.click();
        return new FindWorkshopResultPage();
    }

    @Step("Open Find workshop page")
    public FindWorkshopPage openFindWorkshopPage(String url) {
        driver().navigate().to(url);
        return this;
    }
}