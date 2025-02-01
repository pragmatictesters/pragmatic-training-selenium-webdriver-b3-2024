package com.pragmatic.selenium.sauce;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

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


    @Test
    public void testCheckProductName() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        List<WebElement> eleProductNames = webDriver.findElements(By.cssSelector("div.inventory_item_name"));
        // Create an ArrayList to store product names
        ArrayList<String> productNamesList = new ArrayList<>();

        // Iterate over elements and add text to ArrayList
        for (WebElement product : eleProductNames) {
            productNamesList.add(product.getText());
        }

        Assert.assertFalse(productNamesList.isEmpty(), "Product list is empty!");
        Assert.assertTrue(productNamesList.contains("Sauce Labs Backpack"), "Product not found in the list!");
        Assert.assertFalse(productNamesList.contains("Pragmatic Labs Backpack"), "Product found in the list!");

        // Print to verify
        System.out.println(productNamesList);


        List<String> expectedProducts = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        );

        Assert.assertEquals(productNamesList, expectedProducts);

        List<String> expectedProductsNoOrder = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Bolt T-Shirt",
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Onesie"

                );

        Assert.assertEqualsNoOrder(productNamesList, expectedProductsNoOrder);

        // Convert productNamesList to a set for comparison (ignoring order)
        Assert.assertEqualsNoOrder(productNamesList.toArray(), expectedProducts.toArray(), "Product lists do not match!");
        Assert.assertEquals(productNamesList.size(), expectedProducts.size(), "Product count do not match!");
        Assert.assertEquals(productNamesList.get(0), "Sauce Labs Backpack", "First product name is incorrect!");
        Assert.assertEquals(productNamesList.get(productNamesList.size() - 1), "Test.allTheThings() T-Shirt (Red)", "Last product name is incorrect!");

        //Verify if there are any duplicate products
        Set<String> uniqueProducts = new HashSet<>(productNamesList);
        Assert.assertEquals(uniqueProducts.size(), productNamesList.size(), "Duplicate products found in the list!");


        List<String> unexpectedProducts = Arrays.asList("Fake Product 1", "Fake Product 2");
        for (String product : unexpectedProducts) {
            Assert.assertFalse(productNamesList.contains(product), "Unexpected product found: " + product);
        }

    }

    @Test
    public void testCheckProductNameWithAssertJ() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        List<WebElement> eleProductNames = webDriver.findElements(By.cssSelector("div.inventory_item_name"));

        // Extract product names into a List
        List<String> productNamesList = new ArrayList<>();
        for (WebElement product : eleProductNames) {
            productNamesList.add(product.getText());
        }

        // Expected Product List
        List<String> expectedProducts = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        );

        // Unexpected Products
        List<String> unexpectedProducts = Arrays.asList("Fake Product 1", "Fake Product 2");

        // **AssertJ Assertions**
        Assertions.assertThat(productNamesList)
                .isNotEmpty()  // Ensure the list is not empty
                .contains("Sauce Labs Backpack") // Ensure a specific product exists
                .doesNotContain("Pragmatic Labs Backpack") // Ensure an unexpected product is NOT present
                .containsExactlyInAnyOrderElementsOf(expectedProducts) // Ensure the list has all expected items in any order
                .hasSize(expectedProducts.size()); // Ensure product count matches

        // Verify first and last product names
        Assertions.assertThat(productNamesList.get(0))
                .as("First product name is incorrect!")
                .isEqualTo("Sauce Labs Backpack");

        Assertions.assertThat(productNamesList.get(productNamesList.size() - 1))
                .as("Last product name is incorrect!")
                .isEqualTo("Test.allTheThings() T-Shirt (Red)");

        // Ensure no duplicate product names exist
        Assertions.assertThat(new HashSet<>(productNamesList))
                .as("Duplicate products found in the list!")
                .hasSameSizeAs(productNamesList);

        // Ensure unexpected products are NOT present
        Assertions.assertThat(productNamesList)
                .doesNotContainAnyElementsOf(unexpectedProducts);

        // Print for verification
        System.out.println(productNamesList);
    }
}
