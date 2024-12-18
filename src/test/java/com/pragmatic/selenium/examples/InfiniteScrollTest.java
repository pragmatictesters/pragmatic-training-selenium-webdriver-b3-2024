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

import java.util.List;

public class InfiniteScrollTest {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        // Initialize WebDriver (driver setup is assumed to be handled externally)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void testInfiniteScrollLoadsMoreContent() throws InterruptedException {
        // Locate the parent element containing the content
        By contentLocator = By.cssSelector("div.scroll.columns");

        // Get initial count of content elements
        WebElement eleContent = driver.findElement(contentLocator);
        int initialContentHeight = eleContent.getSize().height;
        System.out.println("initialContentHeight = " + initialContentHeight);

        // Scroll down multiple times
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 3; i++) {  // Adjust the loop as needed to verify more loading
            js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
            Thread.sleep(2000); // Wait for new content to load
        }

        // Get the count of content elements after scrolling
        //List<WebElement> newContent = driver.findElements(contentLocator);
        int newContentHeight = eleContent.getSize().height;
        System.out.println("newContentHeight = " + newContentHeight);

        // Assert that new content has been loaded
        Assert.assertTrue(newContentHeight > initialContentHeight, "New content was not loaded after scrolling.");
    }

}
