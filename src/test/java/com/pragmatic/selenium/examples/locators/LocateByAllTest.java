package com.pragmatic.selenium.examples.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LocateByAllTest {


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
        By inputs = new ByAll(By.id("user-name"), By.id("password"));
        List<WebElement> loginElements = webDriver.findElements(inputs);

        loginElements.get(0).sendKeys("standard_user");
        loginElements.get(1).sendKeys("secret_sauce");
        Assert.assertEquals(loginElements.get(0).getDomProperty("value"), "standard_user");

    }








}
