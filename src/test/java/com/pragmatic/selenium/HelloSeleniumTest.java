package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloSeleniumTest {



    @Test
    public void  testSauceLogin(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
        webDriver.close();


    }

}
