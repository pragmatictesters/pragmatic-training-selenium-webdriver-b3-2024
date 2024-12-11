package com.pragmatic.selenium.examples;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CopyAndPasteTest {


    @Test
    public void copyAndPaste() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        WebElement textField = driver.findElement(By.id("my-text-id"));
        new Actions(driver)
                .sendKeys(textField, "Selenium!")
                .sendKeys(Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(cmdCtrl)
                .sendKeys("xvv")
                .keyUp(cmdCtrl)
                .perform();



        Assert.assertEquals( textField.getDomProperty("value"), "SeleniumSelenium!");
    }
}
