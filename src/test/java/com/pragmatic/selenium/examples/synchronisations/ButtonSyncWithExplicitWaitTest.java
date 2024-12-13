package com.pragmatic.selenium.examples.synchronisations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ButtonSyncWithExplicitWaitTest {


    WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/buttons.html");
    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }

    @Test
    public void testBasicSynWithImplicitWait() {

        waitAndClick(By.id("button00"));
        waitAndClick(By.id("button01"));
        waitAndClick(By.id("button02"));
        waitAndClick(By.id("button03"));
        Assert.assertEquals(webDriver.findElement(By.id("buttonmessage")).getText(), "All Buttons Clicked");

    }

    private void waitAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        webDriver.findElement(by).click();

    }
}
