package com.pragmatic.selenium.sauce.tests;

import com.pragmatic.selenium.sauce.pages.LandingPage;
import com.pragmatic.selenium.sauce.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class SauceLoginTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.close();
    }



    @Test
    public void testLoginWithValidCredentials(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.typeUsername("standard_user")
                .typePassword("secret_sauce")
                .clickLogin();
        LandingPage landingPage = new LandingPage(webDriver);
        Assert.assertEquals(landingPage.getTitle(), "Products");
    }


}
