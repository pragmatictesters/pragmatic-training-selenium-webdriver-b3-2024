package com.pragmatic.selenium.examples.javascript;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JavaScriptExecutorTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testJavaScriptExecution() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Logging into Sauce Demo
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        // Using JavaScript to click login button
        js.executeScript("arguments[0].click();", loginButton);

        // Finding another button on the inventory page (e.g., Add to Cart)
        WebElement addToCartButton = driver.findElement(By.xpath("//*[@data-test='inventory-item'][1]/descendant::button"));

        // Clicking button using JavaScript
        js.executeScript("arguments[0].click();", addToCartButton);

         //addToCartButton = driver.findElement(By.xpath("//*[@data-test='inventory-item'][1]/descendant::button"));


        // Getting button text using JavaScript
        String buttonText = (String) js.executeScript("return arguments[0].innerText", addToCartButton);
        System.out.println("Button text: " + buttonText);

        // Executing JavaScript directly
        js.executeScript("console.log('Hello world from Selenium!')");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

