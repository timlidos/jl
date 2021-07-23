package com.weightwatchers.pages;

import com.weightwatchers.base.BaseWorkspacePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public String getPageTitle() {
        return driver().getTitle().replaceAll(" ", " ");
    }

    public FindWorkshopPage clickStudioLabel() {
        waitForVisibility(studioLabel);
        studioLabel.click();
        return this;
    }

    public void typeIntoSearchTextBox(String value) {
        waitForVisibility(searchTextBox);
        searchTextBox.sendKeys(value);
    }

    public FindWorkshopPage searchFor(String value) {
        typeIntoSearchTextBox(value);
        searchTextBox.sendKeys(Keys.ENTER);
        return this;
    }

    public String getFirstResultTitle() {
        waitForVisibility(resultsBlock);
        return firstResultTitle.getText();
    }

    public String getFirstResultDistance() {
        waitForVisibility(resultsBlock);
        return firstResultDistance.getText();
    }

    public com.weightwatchers.pages.FindWorkshopResultPage clickFirstResultTitle() {
        waitForVisibility(firstResultTitle);
        firstResultTitle.click();
        return new com.weightwatchers.pages.FindWorkshopResultPage();
    }

    public FindWorkshopPage openFindWorkshopPage(String url) {
        driver().navigate().to(url);
        return this;
    }
}