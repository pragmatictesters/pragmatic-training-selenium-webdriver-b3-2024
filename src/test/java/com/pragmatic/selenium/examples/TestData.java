package com.pragmatic.selenium.examples;

import org.testng.annotations.DataProvider;

public class TestData {


    @DataProvider
    public Object[][] userCredentials() {
        return new Object[][]{
                {"","", "Epic sadface: Username is required"},
                {"","secret_sauce", "Epic sadface: Username is required"},
                {"standard_user","", "Epic sadface: Password is required"},
                {"standard_user","invalidPWD", "Epic sadface: Username and password do not match any user in this service"},
        };
    }
}
