package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScreenResolutionTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        // Initialize the ChromeDriver with ChromeOptions
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Run in headless mode for testing
        options.addArguments("--disable-gpu");
        webDriver = new ChromeDriver(options);
        System.out.println("ChromeDriver initialized.");
    }

    @AfterMethod
    public void tearDown() {
        // Quit the driver after each test
        if (webDriver != null) {
            webDriver.quit();
            System.out.println("ChromeDriver quit.");
        }
    }

    private void login(){
        webDriver.get("https://www.saucedemo.com");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }


    @Test
    public void testSetResolution1280x720() {
        // Set browser window size to 1280x720
        Dimension resolution = new Dimension(1280, 720);
        webDriver.manage().window().setSize(resolution);

        login();

        // Validate the window size
        Dimension currentSize = webDriver.manage().window().getSize();
        System.out.println("Current window size: " + currentSize.getWidth() + "x" + currentSize.getHeight());
        Assert.assertEquals(currentSize.getWidth(), 1280, "Width mismatch for resolution 1280x720");
        Assert.assertEquals(currentSize.getHeight(), 720, "Height mismatch for resolution 1280x720");
    }

    @Test
    public void testSetResolution1024x768() {
        // Set browser window size to 1024x768
        Dimension resolution = new Dimension(1024, 768);
        webDriver.manage().window().setSize(resolution);

        login();

        // Validate the window size
        Dimension currentSize = webDriver.manage().window().getSize();
        System.out.println("Current window size: " + currentSize.getWidth() + "x" + currentSize.getHeight());
        Assert.assertEquals(currentSize.getWidth(), 1024, "Width mismatch for resolution 1024x768");
        Assert.assertEquals(currentSize.getHeight(), 768, "Height mismatch for resolution 1024x768");
    }

    @Test
    public void testSetResolution800x600() {
        // Set browser window size to 800x600
        Dimension resolution = new Dimension(800, 600);
        webDriver.manage().window().setSize(resolution);

        login();

        // Validate the window size
        Dimension currentSize = webDriver.manage().window().getSize();
        System.out.println("Current window size: " + currentSize.getWidth() + "x" + currentSize.getHeight());
        Assert.assertEquals(currentSize.getWidth(), 800, "Width mismatch for resolution 800x600");
        Assert.assertEquals(currentSize.getHeight(), 600, "Height mismatch for resolution 800x600");
    }
}
