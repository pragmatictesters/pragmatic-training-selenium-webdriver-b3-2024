package com.pragmatic.selenium.examples.synchronisations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CustomWebDriverWaitTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/collapseable.html");

    }


    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }

    @Test
    public void testCustomWebDriverWait() {
        webDriver.findElement(By.id("collapsable")).click();
        WebElement link = webDriver.findElement(By.id("aboutlink"));

        WaitFor.clickableLink(webDriver).until(ExpectedConditions.elementToBeClickable(link));

        link.click();

        Assert.assertTrue(webDriver.getCurrentUrl().contains("about.html"));
    }

    private static class WaitFor {

        public static WebDriverWait clickableLink(WebDriver webDriver) {

            return (WebDriverWait) new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NullPointerException.class)
                    .withMessage("Cannot find a clickable link");
        }
    }
}
