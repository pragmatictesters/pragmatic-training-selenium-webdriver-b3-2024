package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SauceLabsIntegrationTest {

    private final String BASE_URL;
    private RemoteWebDriver webDriver;

    public SauceLabsIntegrationTest() {
        BASE_URL = "http://saucedemo.com";
    }


    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-janesh-79803");
        sauceOptions.put("accessKey", "e757804f-297b-4fed-9fc6-5952c7ab0120");
        sauceOptions.put("build", "selenium-build-8GO4S");
        sauceOptions.put("name", "Pragmatic First Test");
        browserOptions.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        webDriver = new RemoteWebDriver(url, browserOptions);
    }


    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        webDriver.manage().window().maximize();
        webDriver.get(BASE_URL);
    }


    @AfterClass
    public void afterClass() {
        if (webDriver != null) {
            webDriver.close();
        }
    }

    private void logout(){
        // Log out and assert redirection to login page
        webDriver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#logout_sidebar_link")));
        webDriver.findElement(By.cssSelector("#logout_sidebar_link")).click();

    }


    @Test
    public void testLoginWithValidCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
        logout();
    }

    @Test
    public void testLoginWithValidCredentialsName() {
        webDriver.findElement(By.name("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.name("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.name("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
        logout();
    }


    @Test
    public void testLoginWithValidCredentialsClassName() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.className("submit-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
        logout();
    }


    @Test
    public void testLoginWithPerformanceGlitchedCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.title")).getText(), "Products");
        logout();
    }


    @Test
    public void testLoginWithBlankCredentials() {
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), "Epic sadface: Username is required");
    }

    @Test
    public void testLoginWithBlankUsername() {
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), "Epic sadface: Username is required");
    }


    @Test
    public void testLoginWithBlankPassword() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), "Epic sadface: Password is required");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("Secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

}
