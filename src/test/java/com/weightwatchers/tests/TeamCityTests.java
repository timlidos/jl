package com.weightwatchers.tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TeamCityTests {

    @BeforeClass
    public void specificSetup() {
        System.out.println("BEFORE CLASS SOUT");
    }

    @Description("First test description")
    @Test
    public void firstTest() {
        System.out.println("FIRST TEST SOUT");
        Assert.assertTrue(true);
    }
}