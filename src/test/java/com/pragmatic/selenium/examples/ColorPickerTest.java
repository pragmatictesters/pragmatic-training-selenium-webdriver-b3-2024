package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ColorPickerTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver (Replace with your path to chromedriver)
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testColorPicker() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        // Locate the color picker input
        WebElement colorPicker = driver.findElement(By.name("my-colors"));

        // Option 1: Send new color value via sendKeys
        String orangeColor = "#ff5733"; // Orange color
        colorPicker.sendKeys(orangeColor);

        // Validate that the color value was updated
        String updatedValueSendKeys = colorPicker.getDomProperty("value");
        Assert.assertEquals(updatedValueSendKeys, orangeColor, "The color value should be updated to Orange.");

        // Option 2: Update color using JavaScript
        String greenColor = "#33ff57"; // Green color
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", colorPicker, greenColor);

        // Validate that the color value was updated using JavaScript
        String updatedValueJavaScript = colorPicker.getDomProperty("value");
        Assert.assertEquals(updatedValueJavaScript, greenColor, "The color value should be updated to Green.");
    }
}
