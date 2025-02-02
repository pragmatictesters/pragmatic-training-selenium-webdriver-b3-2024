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

public class CustomExpectedConditionsTest {


    WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        webDriver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }

    @Test
    public void testCustomExpectedConditionAllElementsHaveLoaded() {
        webDriver.get("https://eviltester.github.io/synchole/messages.html");
        By byMessageList=  By.cssSelector("#messageslist>li");

        new WebDriverWait(webDriver,Duration.ofSeconds(10), Duration.ofMillis(500)).until(
                ExpectedConditions.visibilityOfElementLocated(byMessageList)
        );

        new WebDriverWait(webDriver,Duration.ofSeconds(10), Duration.ofMillis(2000)).until(
                new AllMessagesLoaded(byMessageList)
        );
        System.out.println("Total message count is " + webDriver.findElements(byMessageList).size());
    }

    @Test
    public void testCustomExpectedConditionElementHasExpandedFully() {
        webDriver.get("https://eviltester.github.io/synchole/collapseable.html");
        By collapsibleElement=  By.id("collapsable");
        webDriver.findElement(By.id("collapsable")).click();

        new WebDriverWait(webDriver,Duration.ofSeconds(10), Duration.ofMillis(20)).until(
                new ElementHasExpandedFully(collapsibleElement)
        );
        webDriver.findElement(By.id("aboutlink")).click();
    }




}
