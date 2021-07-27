package com.weightwatchers.tests;

import com.weightwatchers.base.BrowseBase;
import com.weightwatchers.pages.FindWorkshopPage;
import com.weightwatchers.pages.FindWorkshopResultPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FindWorkspaceTests extends BrowseBase {

    FindWorkshopPage findWorkshopPage;
    FindWorkshopResultPage findWorkshopResultPage;
    private String expectedTitle = "Find WW Studios & Meetings Near You | WW USA";
    private String zipCode = "10011";
    private String closestDistanceTitle;

    @BeforeClass
    public void specificSetup() {
        findWorkshopPage = new FindWorkshopPage();
        findWorkshopResultPage = new FindWorkshopResultPage();
    }

    @Description("navigate to find work space page")
    @Test
    public void navigateToWwStudioFinderPage() {
        findWorkshopPage.openFindWorkshopPage(baseUrl);
    }

    @Description("assert loaded page title contains Find WW Studios & Meetings Near You | WW USA")
    @Test(dependsOnMethods = {"navigateToWwStudioFinderPage"})
    public void assertLoadedPageTitleContainsExpectedTitle() {
        Assert.assertEquals(findWorkshopPage.getPageTitle(), expectedTitle);
    }

    @Description("under Find your Workshop, click on Studios")
    @Test(dependsOnMethods = {"assertLoadedPageTitleContainsExpectedTitle"})
    public void clickOnStudiosUnderFindYourWorkshop() {
        findWorkshopPage.clickStudioLabel();
    }

    @Description("in the search field search for meetings for zipcode 10011")
    @Test(dependsOnMethods = {"clickOnStudiosUnderFindYourWorkshop"})
    public void searchForMeetingsForZipCode() {
        findWorkshopPage.searchFor(zipCode);
    }

    @Description("print the title of the first result and the distance")
    @Test(dependsOnMethods = {"searchForMeetingsForZipCode"})
    public void printTheTitleOfTheFirstResultAndDistance() {
        String closestDistance = findWorkshopPage.getClosestDistance();
        closestDistanceTitle = findWorkshopPage.getClosestDistanceTitle(closestDistance);
        System.out.println("-------------------");
        System.out.println("Title: " + closestDistanceTitle);
        System.out.println("Distance: " + closestDistance);
        System.out.println("-------------------");
    }

    @Description("click on the first search result and then verify displayed location name/title")
    @Test(dependsOnMethods = {"printTheTitleOfTheFirstResultAndDistance"})
    public void clickOnTheFirstSearchResult() {
        findWorkshopPage.clickClosestResultTitle();
    }

    @Description("verify displayed location name/title matches with the name of the first searched result that was clicked")
    @Test(dependsOnMethods = {"clickOnTheFirstSearchResult"})
    public void verifyDisplayedLocationNameAndTitleMatchesWithTheNameOfTheFirstSearchedResultThatWasClicked() {
        Assert.assertEquals(findWorkshopResultPage.getLocationTitle(), closestDistanceTitle);
    }

    @Description("click on Business hours")
    @Test(dependsOnMethods = {"verifyDisplayedLocationNameAndTitleMatchesWithTheNameOfTheFirstSearchedResultThatWasClicked"})
    public void clickOnBusinessHours() {
        findWorkshopResultPage.clickBusinessHoursLabel();
    }

    @Description("print all the business hours for that studio")
    @Test(dependsOnMethods = {"clickOnBusinessHours"})
    public void printAllTheBusinessHoursThatStudio() {
        List<String> businessHours = findWorkshopResultPage.getBusinessHours();
        for (String s : businessHours) {
            System.out.println(s);
            System.out.println("-------------------");
        }
    }
}