package com.weightwatchers.tests;

import com.weightwatchers.pages.FindWorkshopPage;
import com.weightwatchers.base.BrowseBase;
import com.weightwatchers.pages.FindWorkshopResultPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FindWorkspaceTests extends BrowseBase {

    private String expectedTitle;
    private String zipCode;

    @BeforeMethod
    protected void specificSetup() {
        expectedTitle = "Find WW Studios & Meetings Near You | WW USA";
        zipCode = "10011";
    }

    @Description("Get business hours of workshop")
    @Test
    public void getBusinessHoursOfWorkshop() {
        FindWorkshopPage findWorkshopPage = new FindWorkshopPage();
        FindWorkshopResultPage findWorkshopResultPage = new FindWorkshopResultPage();

        findWorkshopPage
                .openFindWorkshopPage(baseUrl);
        Assert.assertEquals(findWorkshopPage.getPageTitle(), expectedTitle);

        findWorkshopPage
                .clickStudioLabel()
                .searchFor(zipCode);

        String closestDistance = findWorkshopPage.getClosestDistance();
        String closestDistanceTitle = findWorkshopPage.getClosestDistanceTitle(closestDistance);

        System.out.println("-------------------");
        System.out.println("Title: " + closestDistanceTitle);
        System.out.println("Distance: " + closestDistance);
        System.out.println("-------------------");

        findWorkshopPage
                .clickClosestResultTitle();

        Assert.assertEquals(findWorkshopResultPage.getLocationTitle(), closestDistanceTitle);

        findWorkshopResultPage
                .clickBusinessHoursLabel();
        List<String> businessHours = findWorkshopResultPage.getBusinessHours();
        for (String s : businessHours) {
            System.out.println(s);
            System.out.println("-------------------");
        }
    }
}