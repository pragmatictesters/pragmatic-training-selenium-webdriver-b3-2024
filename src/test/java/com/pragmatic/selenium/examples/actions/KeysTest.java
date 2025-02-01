package com.pragmatic.selenium.examples.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KeysTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
         webDriver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }

    @Test
    public void keyUp() {

        webDriver.get("https://www.selenium.dev/selenium/web/single_text_input.html");
        WebElement textField = webDriver.findElement(By.id("textInput"));
//        textField.sendKeys("Ab");

        new Actions(webDriver)
                .keyDown(Keys.SHIFT)
                .sendKeys("P")
                .keyUp(Keys.SHIFT)
                .sendKeys("ragmatic")
                .perform();

        Assert.assertEquals(textField.getDomProperty("value"),"Pragmatic");
    }
}
