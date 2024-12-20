package com.pragmatic.selenium.examples;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Optional;

public class SimulateSlowConnectionTest {

    private WebDriver driver;
    private final String baseUrl = "https://opensource-demo.orangehrmlive.com";

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        // Access DevTools
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();

        // Enable network throttling
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false, // offline
                100,   // latency in ms
                50 * 1024, // download speed (50 KBps)
                20 * 1024, // upload speed (20 KBps)
                Optional.of(ConnectionType.CELLULAR3G)
        ));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
    }

    @Test
    public void testLogin() {
        // Open the OrangeHRM login page
        driver.get(baseUrl);

        // Locate and input username
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("Admin");

        // Locate and input password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("admin123");

        // Click the Login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Verify successful login by checking the presence of the dashboard element
        WebElement dashboardHeader = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Login failed: Dashboard not displayed.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
