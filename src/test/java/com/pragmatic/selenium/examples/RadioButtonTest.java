package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RadioButtonTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initialize the WebDriver (assuming ChromeDriver is properly configured and path is not needed)
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        // Clean up and close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testRadioButtonSelection() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html"); // Replace with your URL

        // Locate the radio buttons
        WebElement radioButton1 = driver.findElement(By.id("my-radio-1"));
        WebElement radioButton2 = driver.findElement(By.id("my-radio-2"));

        // Check if the first radio button (which should be checked by default) is selected
        Assert.assertTrue(radioButton1.isSelected(), "The first radio button should be selected by default.");

        // Check if the second radio button is NOT selected initially
        Assert.assertFalse(radioButton2.isSelected(), "The second radio button should not be selected initially.");

        // Select the second radio button
        radioButton2.click();

        // Check that the second radio button is now selected
        Assert.assertTrue(radioButton2.isSelected(), "The second radio button should be selected after clicking.");

        // Check that the first radio button is NOT selected after the second is clicked
        Assert.assertFalse(radioButton1.isSelected(), "The first radio button should not be selected after the second is clicked.");
    }


}
