package com.pragmatic.selenium.examples.frames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FrameTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();

        // Navigate to the page with frames
        driver.get("https://the-internet.herokuapp.com/nested_frames"); // Replace with the actual URL of your page
    }

    @Test
    public void testFrames() {
        // Switch to the top frame
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        // Perform an action in the top frame (e.g., validate some element or text)
        WebElement topFrameElement = driver.findElement(By.tagName("body")); // Adjust the locator based on your needs
        Assert.assertNotNull(topFrameElement, "Top frame body should be present");
        Assert.assertEquals(topFrameElement.getText().trim(), "LEFT");

        // Switch back to the main page
        driver.switchTo().defaultContent();

        // Switch to the bottom frame
        driver.switchTo().frame("frame-bottom");
        // Perform an action in the bottom frame (e.g., validate some element or text)
        WebElement bottomFrameElement = driver.findElement(By.tagName("body")); // Adjust the locator based on your needs
        Assert.assertNotNull(bottomFrameElement, "Bottom frame body should be present");
        Assert.assertEquals(bottomFrameElement.getText().trim(), "BOTTOM");

        // Switch back to the main page
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
