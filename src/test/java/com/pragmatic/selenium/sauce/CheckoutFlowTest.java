package com.pragmatic.selenium.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutFlowTest {

    private final String BASE_URL;
    private WebDriver webDriver;

    public CheckoutFlowTest() {
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
        webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        webDriver.findElement(By.cssSelector(".shopping_cart_link")).click();
        webDriver.findElement(By.id("checkout")).click();
        webDriver.findElement(By.id("first-name")).sendKeys("Janesh");
        webDriver.findElement(By.id("last-name")).sendKeys("Kodikara");
        webDriver.findElement(By.id("postal-code")).sendKeys("11850");
        webDriver.findElement(By.id("continue")).click();
        webDriver.findElement(By.id("finish")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h2.complete-header")).getText(), "Thank you for your order!");
    }


}
