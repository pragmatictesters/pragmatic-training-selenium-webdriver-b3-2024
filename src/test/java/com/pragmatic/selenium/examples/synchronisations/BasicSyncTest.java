package com.pragmatic.selenium.examples.synchronisations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BasicSyncTest {

    WebDriver webDriver;

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
    public void testBasicSynWithImplicitWait() {

        webDriver.findElement(By.id("collapsable")).click();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        webDriver.findElement(By.id("aboutlink")).click();

    }

    @Test
    public void testBasicSynWithSleep() throws InterruptedException {
        webDriver.findElement(By.id("collapsable")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.id("aboutlink")).click();
    }


    @Test
    public void testBasicSynWithExplicitWaitVisibilityOfElement() {
        webDriver.findElement(By.id("collapsable")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("aboutlink"))));
        webDriver.findElement(By.id("aboutlink")).click();
    }

    @Test
    public void testBasicSynWithExplicitWaitElementToBeClickable() {
        webDriver.findElement(By.id("collapsable")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id("aboutlink"))));
        webDriver.findElement(By.id("aboutlink")).click();
    }


    @Test
    public void testBasicSynWithFluentWait() {
        webDriver.findElement(By.id("collapsable")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10),Duration.ofMillis(100));
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id("aboutlink"))));
        webDriver.findElement(By.id("aboutlink")).click();
    }










}
