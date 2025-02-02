package com.pragmatic.selenium.sauce.tests;

import com.pragmatic.selenium.examples.testng.dataprovider.TestData;
import com.pragmatic.selenium.sauce.pages.SaLandingPage;
import com.pragmatic.selenium.sauce.pages.SaLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLoginTestWithPageFactoryTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @Test
    public void testLoginWithInvalidPassword() {
        //Create a new login page
        SaLoginPage loginPage = new SaLoginPage(webDriver);
        //Interact with the login page
        loginPage.typeUsername("standard_user")
                .typePassword("secret_sauce234")
                .clickLogin();
        Assert.assertEquals(loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test (dataProvider = "user-credentials", dataProviderClass = TestData.class)
    public void testLoginWithInvalidCredentials(String username, String password, String expectedError) {
        //Create a new login page
        SaLoginPage loginPage = new SaLoginPage(webDriver);
        //Interact with the login page
        loginPage.typeUsername(username)
                .typePassword(password)
                .clickLogin();
        Assert.assertEquals(loginPage.getError(), expectedError);
    }

    @Test
    public void testLoginWithValidCredentials() {
        //Create a new login page
        SaLoginPage loginPage = new SaLoginPage(webDriver);
        //Interact with the login page
        loginPage.typeUsername("standard_user")
                .typePassword("secret_sauce")
                .clickLogin();
        SaLandingPage landingPage = new SaLandingPage(webDriver);

        Assert.assertEquals(landingPage.getTitle(),
                "Products", "Title does not match with the expected");

    }
}
