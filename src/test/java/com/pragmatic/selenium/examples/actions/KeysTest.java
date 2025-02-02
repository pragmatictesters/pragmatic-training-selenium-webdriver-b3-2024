package com.pragmatic.selenium.examples.actions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KeysTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @Test
    public void keyUp() {

        webDriver.get("https://www.saucedemo.com/");
        WebElement textField = webDriver.findElement(By.id("user-name"));


        Actions actions = new Actions(webDriver);
        actions.keyDown(Keys.SHIFT)
                .sendKeys(textField, "p")
                .keyUp(Keys.SHIFT)
                .perform();

        actions.sendKeys(textField, "ragmatic")
                .perform();

        Assert.assertEquals(textField.getDomProperty("value"), "Pragmatic");
    }

    @Test
    public void testCopyAndPaste() {
        webDriver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement textField = webDriver.findElement(By.id("my-text-id"));
        Keys controlKey = getControlKey();

        new Actions(webDriver)
                .sendKeys(textField, "Selenium!")
                .sendKeys(textField, Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(controlKey)
                .sendKeys("xvvv")
                .keyUp(controlKey)
                .perform();
        Assert.assertEquals(textField.getDomProperty("value"), "SeleniumSeleniumSelenium!");
    }

    private static Keys getControlKey() {
        return Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
    }


}
