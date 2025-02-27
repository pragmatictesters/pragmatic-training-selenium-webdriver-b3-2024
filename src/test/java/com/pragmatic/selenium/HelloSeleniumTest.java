package com.pragmatic.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

public class HelloSeleniumTest {

    private static final Logger logger = LogManager.getLogger(HelloSeleniumTest.class);

    @Test
    public void testSauceLogin() {

        try {
            logger.info("testSauceLogin is started ");

            WebDriver webDriver = new ChromeDriver();
            webDriver.get("https://www.saucedemo.com");
            logger.debug("Start typing the username standard_user");
//            webDriver.findElement(By.id("user-name")).sendKeys("standard_user");

            webDriver.findElement(By.xpath("//*[@id='user-name1'] | //*[@name='user-name']")).sendKeys("standard_user");


            logger.debug("Start typing the password secret_sauce");
            webDriver.findElement(By.id("password")).sendKeys("secret_sauce");

            Button button = new Button(webDriver.findElement(By.id("login-button")));
            button.click();

            //webDriver.findElement(By.id("login-button")).clear();
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");

            //Get all the product names
//            webDriver.findElements(By.cssSelector("[data-test='inventory-item-name']"))
//                    .stream()
//                    .map(element -> element.getText())
//                    .forEach(productName -> System.out.println(productName));

            System.out.println("------------------------------");
            webDriver.findElements(By.cssSelector("[data-test='inventory-item-name']"))
                    .stream()
                    .map(WebElement::getText)
                    .filter(productName -> productName.startsWith("Sauce"))
                    .forEach(System.out::println);


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
