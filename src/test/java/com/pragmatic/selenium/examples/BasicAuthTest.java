package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BasicAuthTest {

    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        // Initialize the WebDriver (Google Chrome assumed to be set up under the hood)
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBasicAuth() {
        // URL with embedded credentials
        String username = "admin";
        String password = "admin";
        String baseUrl = "https://the-internet.herokuapp.com/basic_auth";
        String authenticatedUrl = "https://" + username + ":" + password + "@"
                + baseUrl.replace("https://", "");

        System.out.println("authenticatedUrl = " + authenticatedUrl);
        // Navigate to the URL
        driver.get(authenticatedUrl);

        // Validate successful login by checking page text
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Congratulations! You must have the proper credentials."),
                "Login failed: Expected success message not found!");
        Assert.assertEquals(driver.findElement(By.tagName("h3")).getText(),
                "Basic Auth");
        Assert.assertEquals(driver.findElement(By.tagName("p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }

    
}

