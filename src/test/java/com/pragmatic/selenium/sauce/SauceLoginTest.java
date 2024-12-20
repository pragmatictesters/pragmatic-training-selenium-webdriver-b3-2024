package com.pragmatic.selenium.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLoginTest {

    private final String BASE_URL;
    private WebDriver webDriver;

    public SauceLoginTest() {
        BASE_URL = "http://saucedemo.com";
    }


    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(BASE_URL);
    }


    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @Test
    public void testLoginWithValidCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
    }

     @Test
    public void testLoginWithValidCredentialsName() {
        webDriver.findElement(By.name("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.name("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.name("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
    }


    @Test
    public void testLoginWithValidCredentialsClassName() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.className("submit-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
    }


    @Test
    public void testLoginWithPerformanceGlitchedCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
    }


    @Test
    public void testLoginWithBlankCredentials() {
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), "Epic sadface: Username is required");
    }

    @Test
    public void testLoginWithBlankUsername() {
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), "Epic sadface: Username is required");
    }


    @Test
    public void testLoginWithBlankPassword() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), "Epic sadface: Password is required");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("Secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

}
