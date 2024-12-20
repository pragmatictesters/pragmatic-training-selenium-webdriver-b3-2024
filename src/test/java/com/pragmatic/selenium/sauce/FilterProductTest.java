package com.pragmatic.selenium.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FilterProductTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
        // Log in with valid credentials
        login();
    }

    @AfterMethod
    public void afterMethod() {
        if (webDriver != null){
            webDriver.quit();
        }
    }

    @Test
    public void testFilterProductsA2Z() {

        // Select "Name (A to Z)" from the sorting dropdown
        selectSortOption("az");

        // Verify products are sorted A to Z
        List<WebElement> productElements = webDriver.findElements(By.cssSelector(".inventory_item_name"));
        String previousProductName = "";
        for (WebElement productElement : productElements) {
            String currentProductName = productElement.getText();
            Assert.assertTrue(currentProductName.compareTo(previousProductName) >= 0,
                    "Products are not sorted in ascending order (A to Z). Current: " + currentProductName + " Previous: " + previousProductName);
            previousProductName = currentProductName;
        }
    }


    @Test
    public void testFilterProductsZ2A() {


        // Select "Name (Z to A)" from the sorting dropdown
        selectSortOption("za");

        // Verify products are sorted Z to A
        List<WebElement>  productElements = webDriver.findElements(By.cssSelector(".inventory_item_name"));
        String previousProductName = productElements.get(0).getText();
        for (WebElement productElement : productElements) {
            String currentProductName = productElement.getText();
            Assert.assertTrue(currentProductName.compareTo(previousProductName) <= 0,
                    "Products are not sorted in descending order (Z to A). Current: " + currentProductName + " Previous: " + previousProductName);
            previousProductName = currentProductName;
        }
    }


    @Test
    public void testSortPriceLowToHigh() {
        // Select "Price (low to high)" from the sorting dropdown
        selectSortOption("lohi");

        // Get all product prices
        List<WebElement> priceElements = webDriver.findElements(By.cssSelector(".inventory_item_price"));
        double previousPrice = 0.0;

        // Compare the prices to ensure they are in low to high order
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "").trim();
            double currentPrice = Double.parseDouble(priceText);
            Assert.assertTrue(currentPrice >= previousPrice,
                    "Products are not sorted in ascending order (low to high). Current Price: " + currentPrice + " Previous Price: " + previousPrice);
            previousPrice = currentPrice;
        }
    }

    @Test
    public void testSortPriceHighToLow() {

        // Select "Price (high to low)" from the sorting dropdown
        selectSortOption("hilo");

        // Get all product prices
        List<WebElement> priceElements = webDriver.findElements(By.cssSelector(".inventory_item_price"));
        double previousPrice = Double.MAX_VALUE;

        // Compare the prices to ensure they are in high to low order
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "").trim();
            double currentPrice = Double.parseDouble(priceText);
            Assert.assertTrue(currentPrice <= previousPrice,
                    "Products are not sorted in descending order (high to low). Current Price: " + currentPrice + " Previous Price: " + previousPrice);
            previousPrice = currentPrice;
        }
    }

    private void login() {
        // Log in with valid credentials
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }


    // Helper method to select a sort option from the dropdown
    private void selectSortOption(String value) {
        WebElement sortDropdown = webDriver.findElement(By.cssSelector(".product_sort_container"));
        Select select = new Select(sortDropdown);
        select.selectByValue(value);
    }
}
