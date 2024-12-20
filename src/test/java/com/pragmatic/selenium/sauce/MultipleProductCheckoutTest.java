package com.pragmatic.selenium.sauce;


import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class MultipleProductCheckoutTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
        login();
    }

    @AfterMethod
    public void afterMethod() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testMultipleProductCheckout() {

        // Create fake user information
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        // Capture the prices of the products before adding them to the cart
        double backpackPrice = getPriceOfProduct("Sauce Labs Backpack");
        double onesiePrice = getPriceOfProduct("Sauce Labs Onesie");
        double tshirtPrice = getPriceOfProduct("Test.allTheThings() T-Shirt (Red)");

        //TODO: We will improve following to locate the buttons with respect to the product name
        //Example //*[text()='Sauce Labs Backpack']/following::div[@class='inventory_item_price'][1]

        // Add multiple products to the cart
        webDriver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        webDriver.findElement(By.cssSelector("#add-to-cart-sauce-labs-onesie")).click();
        webDriver.findElement(By.cssSelector("button[data-test='add-to-cart-test.allthethings()-t-shirt-(red)']")).click();

        // Go to the shopping cart and proceed to checkout
        webDriver.findElement(By.cssSelector(".shopping_cart_link")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Your Cart");
        webDriver.findElement(By.cssSelector("#checkout")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Checkout: Your Information");

        // Fill in the checkout details
        webDriver.findElement(By.id("first-name")).sendKeys(firstName);
        webDriver.findElement(By.id("last-name")).sendKeys(lastName);
        webDriver.findElement(By.id("postal-code")).sendKeys("1000");
        webDriver.findElement(By.id("continue")).click();

        // Assert that we are on the Overview page
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Checkout: Overview");

        // Get the prices from the webpage
        String itemTotalText = webDriver.findElement(By.cssSelector(".summary_subtotal_label")).getText();
        String taxText = webDriver.findElement(By.cssSelector(".summary_tax_label")).getText();
        String totalText = webDriver.findElement(By.cssSelector(".summary_total_label")).getText();

        // Extract the numerical values from the text
        double itemTotal = extractAmount(itemTotalText);
        double tax = extractAmount(taxText);
        double total = extractAmount(totalText);

        // Calculate expected totals
        double expectedItemTotal = backpackPrice + onesiePrice + tshirtPrice; // Item total
        double expectedTotal = expectedItemTotal + tax; // Total including tax

        // Assert the item total and total match the expected values
        Assert.assertEquals(itemTotal, expectedItemTotal, "Item total does not match expected value.");
        Assert.assertEquals(total, expectedTotal, "Total does not match expected value.");

        // Finish the purchase
        webDriver.findElement(By.id("finish")).click();

        // Assert the confirmation message
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Checkout: Complete!");
        Assert.assertEquals(webDriver.findElement(By.cssSelector(".complete-header")).getText(), "Thank you for your order!");
    }


    private void login() {
        // Log in with valid credentials
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }

    // Helper method to extract the price of a product from the product list
    private double getPriceOfProduct(String productName) {
        List<WebElement> productList = webDriver.findElements(By.cssSelector(".inventory_item"));
        for (WebElement product : productList) {
            String productText = product.findElement(By.cssSelector(".inventory_item_name")).getText();
            if (productText.equals(productName)) {
                String priceText = product.findElement(By.cssSelector(".inventory_item_price")).getText();
                return extractAmount(priceText);
            }
        }
        return 0.0;
    }

    // Helper method to extract numeric value from the text (removes non-numeric characters)
    private double extractAmount(String text) {
        return Double.parseDouble(text.replaceAll("[^\\d.]", ""));
    }
}
