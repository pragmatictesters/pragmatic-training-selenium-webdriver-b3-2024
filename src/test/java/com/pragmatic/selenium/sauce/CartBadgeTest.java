package com.pragmatic.selenium.sauce;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartBadgeTest {

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
    public void testCartBadgeUpdates() {

        // Log in to the application
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Products");

        // Initial check: the cart badge should not exist before adding products
        WebElement cartBadge = getCartBadge();
        Assert.assertNull(cartBadge, "Cart badge should not exist before adding any product.");

        // Add the first product to the cart
        webDriver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();

        // After adding one product, the badge should appear with count 1
        cartBadge = getCartBadge();
        Assert.assertNotNull(cartBadge, "Cart badge should appear after adding a product.");
        Assert.assertEquals(cartBadge.getText(), "1", "Cart badge should show 1 after adding one product.");

        // Add another product to the cart
        webDriver.findElement(By.cssSelector("#add-to-cart-sauce-labs-onesie")).click();

        // After adding the second product, the badge should show 2
        cartBadge = getCartBadge();
        Assert.assertNotNull(cartBadge, "Cart badge should appear after adding a second product.");
        Assert.assertEquals(cartBadge.getText(), "2", "Cart badge should show 2 after adding another product.");

        // Remove the first product from the cart
        webDriver.findElement(By.cssSelector("#remove-sauce-labs-backpack")).click();

        // After removing the first product, the badge should show 1
        cartBadge = getCartBadge();
        Assert.assertNotNull(cartBadge, "Cart badge should appear after removing a product.");
        Assert.assertEquals(cartBadge.getText(), "1", "Cart badge should show 1 after removing a product.");

        // Remove the second product from the cart
        webDriver.findElement(By.cssSelector("#remove-sauce-labs-onesie")).click();

        // After removing all products, the cart badge should no longer exist
        cartBadge = getCartBadge();
        Assert.assertNull(cartBadge, "Cart badge should not exist after removing all products.");
    }

    // Helper method to get the cart badge element
    private WebElement getCartBadge() {
        try {
            return webDriver.findElement(By.cssSelector(".shopping_cart_badge"));
        } catch (Exception e) {
            return null; // Return null if the badge does not exist
        }
    }
}
