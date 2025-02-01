package com.pragmatic.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class HelloSeleniumTest {

    private static final Logger logger = LogManager.getLogger(HelloSeleniumTest.class);

    @Test
    public void testSauceLogin() {

        try {
            logger.info("testSauceLogin is started ");

            WebDriver webDriver = new ChromeDriver();
            webDriver.get("https://www.saucedemo.com");
            logger.debug("Start typing the username standard_user");
            webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
            logger.debug("Start typing the password secret_sauce");
            webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
            webDriver.findElement(By.id("login-button")).click();
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
            webDriver.close();
            logger.info("testSauceLogin is completed ");

        } catch (ElementNotInteractableException e) {
            logger.error("ElementNotInteractableException is found" + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
