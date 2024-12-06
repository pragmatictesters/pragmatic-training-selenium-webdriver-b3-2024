package com.pragmatic.selenium.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLoginTest {

    private final String  BASE_URL;
    private  WebDriver webDriver;

    public SauceLoginTest() {
        BASE_URL = "http://saucedemo.com";
    }


    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
        webDriver.get(BASE_URL);
    }


    @AfterMethod
    public void afterMethod(){
        webDriver.close();
    }

    @Test
    public void testLoginWithValidCredentials(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
    }


}
