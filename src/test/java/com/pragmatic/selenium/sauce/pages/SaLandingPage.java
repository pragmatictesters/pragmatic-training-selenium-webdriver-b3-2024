package com.pragmatic.selenium.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaLandingPage {

    @FindBy(css = "span.title")
    WebElement eleTitle;

    public SaLandingPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }


    public String getTitle() {
        return eleTitle.getText();
    }
}
