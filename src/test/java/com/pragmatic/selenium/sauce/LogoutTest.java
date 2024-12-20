package com.pragmatic.selenium.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogoutTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void afterMethod() {
        if (webDriver != null){
            webDriver.quit();
        }
    }

    @Test
    public void testLogout() {
        // Log in with valid credentials
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        // Assert login success
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Products");

        // Open the burger menu and log out
        webDriver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#logout_sidebar_link")));
        webDriver.findElement(By.cssSelector("#logout_sidebar_link")).click();

        // Assert user is redirected to login page after logout
        String currentUrl = webDriver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.equals("https://www.saucedemo.com") || currentUrl.equals("https://www.saucedemo.com/"),
//                "Logout unsuccessful - Not on the expected login page. Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.matches("https://www\\.saucedemo\\.com/?"),
                "Logout unsuccessful - Not on the expected login page. Current URL: " + currentUrl);


        // Test browser back functionality after logout
        webDriver.navigate().back();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.error > h3")).getText(), "Epic sadface: You can only access '/inventory.html' when you are logged in.");
    }

    // Additional useful logout tests:
    @Test
    public void testLogoutButtonVisibility() {
        // Log in with valid credentials
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        // Assert login success
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Products");

        // Open the burger menu and log out
        webDriver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#logout_sidebar_link")));

        // Assert that the logout button is visible and enabled
        WebElement logoutButton = webDriver.findElement(By.cssSelector("#logout_sidebar_link"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible.");
        Assert.assertTrue(logoutButton.isEnabled(), "Logout button is not enabled.");
    }

    @Test
    public void testLogoutAndLoginAgain() {
        // Log in with valid credentials
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        // Assert login success
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Products");

        // Log out and assert redirection to login page
        webDriver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#logout_sidebar_link")));
        webDriver.findElement(By.cssSelector("#logout_sidebar_link")).click();

        // Log in again
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        // Assert login success after re-login
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(), "Products");
    }
}

