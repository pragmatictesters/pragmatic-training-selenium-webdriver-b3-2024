package com.pragmatic.selenium.examples.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocateByChainedTest {


    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @Test
    public void testLocateByAll() {
        // Locate the first checkbox
        By byUsername = new ByChained(By.tagName("form"), By.id("user-name"));
        WebElement eleUsername = webDriver.findElement(byUsername);

        eleUsername.sendKeys("standard_user");
        Assert.assertEquals(eleUsername.getDomProperty("value"), "standard_user");

    }








}
