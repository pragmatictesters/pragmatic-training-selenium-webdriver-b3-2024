package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RangeInputTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initialize the WebDriver (with ChromeDriver, ensure path is correct)
        driver = new ChromeDriver();
    }

    @Test
    public void testRangeInput() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html"); // URL for the page with the range input

        // Locate the range input
        WebElement rangeInput = driver.findElement(By.name("my-range"));

        // Option 1: Set the value by using sendKeys
        rangeInput.sendKeys("8"); // Set the value to 8
        String value = rangeInput.getDomProperty("value");
        Assert.assertEquals(value, "8", "The value of the range input should be 8.");

        // Option 2: Use Actions to slide the range input
        Actions actions = new Actions(driver);
        actions.clickAndHold(rangeInput).moveByOffset(50, 0).release().perform(); // Adjust the slider (move by offset, adjust as needed)

        // Get the new value of the range input after slider movement
        value = rangeInput.getDomProperty("value");
        Assert.assertNotEquals(value, "5", "The value of the range input should have changed.");
    }

    @AfterMethod
    public void tearDown() {
        // Clean up and close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
