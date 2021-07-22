package com.weightwathers.pages;

import com.weightwathers.base.BaseWorkspacePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindWorkshopPage extends BaseWorkspacePage {

    @FindBy(xpath = "//span[normalize-space()='Studio']")
    protected WebElement studioLabel;

    @FindBy(id = "location-search")
    protected WebElement searchTextBox;

    @FindBy(xpath = "//div[contains(@class, 'resultContainer')]")
    protected WebElement resultsBlock;

    @FindBy(xpath = "//div[contains(@class, 'linkContainer')]/a")
    protected WebElement firstResultTitle;

    @FindBy(xpath = "//span[contains(@class, 'distance')]")
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

    public FindWorkshopResultPage clickFirstResultTitle() {
        waitForVisibility(firstResultTitle);
        firstResultTitle.click();
        return new FindWorkshopResultPage();
    }

    public FindWorkshopPage openFindWorkshopPage(String url) {
        driver().navigate().to(url);
        return this;
    }
}