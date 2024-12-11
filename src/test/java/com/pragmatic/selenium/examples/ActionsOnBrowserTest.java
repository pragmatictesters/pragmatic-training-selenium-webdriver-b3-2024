package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class ActionsOnBrowserTest {


    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @Test
    public void testNavigationOptionOne() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com/");
        Assert.assertEquals(webDriver.getTitle(), "Swag Labs");
    }

    @Test
    public void testNavigationOptionTwo() {
        webDriver = new ChromeDriver();
        webDriver.navigate().to("https://www.saucedemo.com/");
        Assert.assertEquals(webDriver.getTitle(), "Swag Labs");
    }



    @Test(expectedExceptions = NoSuchSessionException.class)
    public void testGetTitleAfterBrowserClose() {
        webDriver = new ChromeDriver();
        webDriver.navigate().to("https://www.saucedemo.com/");
        Assert.assertEquals(webDriver.getTitle(), "Swag Labs");
        webDriver.close();
        webDriver.getTitle();
    }





    @Test
    public void testBrowserBackForwardAndRefresh() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com/");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Products");
        webDriver.navigate().back();
        webDriver.navigate().forward();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Products");
        webDriver.navigate().refresh();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Products");
    }





}
