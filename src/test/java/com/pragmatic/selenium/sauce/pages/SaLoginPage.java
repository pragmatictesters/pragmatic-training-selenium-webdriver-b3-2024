package com.pragmatic.selenium.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaLoginPage {
    //Define elements with @FindBy , @FindBys etc
    @FindBy(id= "user-name")
    WebElement txtUsername;
    @FindBy(id= "password")
    WebElement txtPassword;
    @FindBy(id= "login-button")
    WebElement btnLogin;
    @FindBy(xpath= "//h3[@data-test='error']")
    WebElement errMessage;

    private final WebDriver webDriver;

    public SaLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //Initializing the element
        PageFactory.initElements(webDriver, this);
    }

    public SaLoginPage typeUsername(String username) {
        txtUsername.sendKeys(username);
        return this;
    }

    public SaLoginPage typePassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public String getError() {
        return errMessage.getText();
    }
}
