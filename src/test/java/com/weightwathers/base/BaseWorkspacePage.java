package com.weightwathers.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseWorkspacePage extends BrowseBase {

    private final WebDriver driver;

    public BaseWorkspacePage(WebDriver driver) {
        PageFactory.initElements(driver(), this);
        this.driver = driver;
    }
}