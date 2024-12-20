package com.pragmatic.selenium.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailsTest {


        WebDriver webDriver;

        @BeforeMethod
        public void beforeMethod() {
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.get("https://www.saucedemo.com/");
        }

        @AfterMethod
        public void afterMethod() {
            if (webDriver != null) {
                webDriver.quit();
            }
        }

        @Test
        public void testPriceDescriptionAndImage() {
            // Log in to the application
            webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
            webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
            webDriver.findElement(By.id("login-button")).click();
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                    "Products");

            // Find the Sauce Labs Fleece Jacket and click on its name
            webDriver.findElement(By.xpath("//*[text()='Sauce Labs Fleece Jacket']")).click();

            // Verify the product description
            String expectedDescription = "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.";
            Assert.assertEquals(webDriver.findElement(By.cssSelector(".inventory_details_desc")).getText(),
                    expectedDescription, "Product description does not match!");


            // Verify the product price
            String expectedPrice = "$49.99";
            Assert.assertEquals(webDriver.findElement(By.cssSelector(".inventory_details_price")).getText(),
                    expectedPrice, "Product price does not match!");

            // Verify the product image
            WebElement productImage = webDriver.findElement(By.cssSelector(".inventory_details_img"));
            String actualImageSrc = productImage.getDomProperty("src");
            String expectedImageSrc = "https://www.saucedemo.com/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg";
            Assert.assertEquals(actualImageSrc, expectedImageSrc, "Product image URL does not match!");
        }
    }
