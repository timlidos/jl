package com.weightwatchers.tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TeamCityTestsPartTwo {

    @BeforeClass
    public void specificSetup() {
        System.out.println("BEFORE CLASS SOUT SECOND ONE");
    }

    @Description("Second test description")
    @Test
    public void secondTest() {
        System.out.println("SECOND TEST SOUT");
        Assert.assertTrue(true);
    }
}