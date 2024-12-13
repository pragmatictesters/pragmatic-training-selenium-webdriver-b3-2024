package com.pragmatic.selenium.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver webDriver;

    @FindBy(id = "user-name")
    WebElement txtUsername;

    @FindBy(id= "password")
    WebElement txtPassword;

    @FindBy(id="login-button")
    WebElement btnLogin;

    @FindBy(css = "h3[data-test='error']")
    WebElement errMessage;


    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }


    public LoginPage typeUsername(String username) {
        txtUsername.clear();
        txtUsername.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        btnLogin.click();
    }


    public String getError(){
        return errMessage.getText().trim();
    }
}
