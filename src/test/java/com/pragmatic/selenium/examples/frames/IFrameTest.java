package com.pragmatic.selenium.examples.frames;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

public class IFrameTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.selenium.dev/selenium/web/iframes.html");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSwitchToIFrameUsingElement() {
        WebElement iframe = driver.findElement(By.id("iframe1"));
        driver.switchTo().frame(iframe);
        //assertTrue(driver.getPageSource().contains("We Leave From Here"));
        assertTrue(driver.getPageSource().contains("We Leave From Here"));
        assertFalse(driver.getPageSource().contains("This page has iframes"));
        WebElement emailE = driver.findElement(By.id("email"));
        emailE.sendKeys("admin@selenium.dev");
        emailE.clear();
        driver.switchTo().defaultContent();
        assertTrue(driver.getPageSource().contains("This page has iframes"));
        assertFalse(driver.getPageSource().contains("We Leave From Here"));
    }

    @Test
    public void testSwitchToIFrameUsingNameOrId() {
        driver.switchTo().frame("iframe1-name");
        assertTrue(driver.getPageSource().contains("We Leave From Here"));
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("admin@selenium.dev");
        email.clear();
        driver.switchTo().defaultContent();
        assertTrue(driver.getPageSource().contains("This page has iframes"));
    }

    @Test
    public void testSwitchToIFrameUsingIndex() {
        driver.switchTo().frame(0);
        assertTrue(driver.getPageSource().contains("We Leave From Here"));
        driver.switchTo().defaultContent();
        assertTrue(driver.getPageSource().contains("This page has iframes"));
    }


}
