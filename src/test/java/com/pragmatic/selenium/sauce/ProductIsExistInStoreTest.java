package com.pragmatic.selenium.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductIsExistInStoreTest {

    private WebDriver webDriver;

    // Constants for test data
    private final String BASE_URL = "https://www.saucedemo.com/";
    private final String USERNAME = "standard_user";
    private final String PASSWORD = "secret_sauce";
    private final String SEARCH_PRODUCT = "Sauce Labs Fleece Jacket";

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testProductIsExistInStore() {
        // Step 1: Login
        login(USERNAME, PASSWORD);

        // Step 2: Check product availability
        boolean productAvailable = isProductAvailable(SEARCH_PRODUCT);

        // Step 3: Assert the product existence
        Assert.assertTrue(productAvailable, SEARCH_PRODUCT + " is not available in the store.");
        System.out.println(SEARCH_PRODUCT + " is available in the store.");
    }

    // Helper method to perform login
    private void login(String username, String password) {
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();
    }


    // Helper method to check if a product exists
    private boolean isProductAvailable(String productName) {
        // Get all product elements
        List<WebElement> productElements = webDriver.findElements(By.cssSelector(".inventory_item_name"));

        // Check if the product is in the list
        for (WebElement productElement : productElements) {
            if (productElement.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }
}
