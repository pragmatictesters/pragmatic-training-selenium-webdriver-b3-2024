package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class MobileWebTest {

    @Test
    public void testOnPixel2() {
        // Set up mobile emulation for Pixel 2
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 2");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver webDriver = new ChromeDriver(options);
        try {
            webDriver.get("https://www.saucedemo.com");
            webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
            webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
            webDriver.findElement(By.id("login-button")).click();

            // Validate the page title
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
        } finally {
            webDriver.close();
        }
    }



    @Test
    public void testOnIPhoneX() {
        // Set up mobile emulation for iPhone X
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver webDriver = new ChromeDriver(options);
        try {
            webDriver.get("https://www.saucedemo.com");
            webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
            webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
            webDriver.findElement(By.id("login-button")).click();

            // Validate the page title
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
        } finally {
            webDriver.close();
        }
    }


}
