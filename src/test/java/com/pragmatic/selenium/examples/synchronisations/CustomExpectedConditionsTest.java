package com.pragmatic.selenium.examples.synchronisations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        webDriver.get("https://eviltester.github.io/synchole/collapseable.html");
    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }

    @Test
    public void testCustomExpectedConditions() {

        By collapsibleElement=  By.id("collapsable");
        webDriver.findElement(By.id("collapsable")).click();


        new WebDriverWait(webDriver,Duration.ofSeconds(10), Duration.ofMillis(20)).until(
                new ElementHasExpandedFully(collapsibleElement)
        );

        webDriver.findElement(By.id("aboutlink")).click();

    }

//    private static class ElementHasExpandedFully implements ExpectedCondition<Boolean> {
//        private final By collapsibleElement;
//        private int lastHeight;
//
//
//        public ElementHasExpandedFully(By collapsibleElement) {
//            this.collapsibleElement = collapsibleElement;
//        }
//
//
//        @Override
//        public Boolean apply(WebDriver webDriver) {
//            int newHeight =webDriver.findElement(collapsibleElement).getSize().height;
//            System.out.printf("new height %d > %d%n", newHeight, lastHeight);
//            if (newHeight > lastHeight) {
//                lastHeight = newHeight;
//                return false;
//            } else {
//                return true;
//            }
//
//        }
//    }
}
