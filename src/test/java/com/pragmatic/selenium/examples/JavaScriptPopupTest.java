package com.pragmatic.selenium.examples;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaScriptPopupTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
        webDriver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.close();
    }

    @Test
    public void testJavaScriptAlert(){
        webDriver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = webDriver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept(); //Clicking OK button in the alert

        Assert.assertEquals(webDriver.findElement(By.id("result")).getText(), "You successfully clicked an alert");
    }

    @Test
    public void testJavaScriptAlertException(){
        webDriver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // Try to click the next button while the alert is still open
        try {
            webDriver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
            Assert.fail("Expected an UnhandledAlertException but no exception was thrown.");
        } catch (UnhandledAlertException e) {
            // Assert the exception message to check it's the expected alert message
            Assert.assertTrue(e.getMessage().contains("unexpected alert open"), "Unexpected alert message: " + e.getMessage());
        }
    }

    @Test
    public void testJavaScriptConfirmationOK(){
        webDriver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert = webDriver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.accept(); //Clicking OK button in the alert

        Assert.assertEquals(webDriver.findElement(By.id("result")).getText(), "You clicked: Ok");
    }

    @Test
    public void testJavaScriptConfirmationCancel(){
        webDriver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert = webDriver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss(); //Clicking Cancel button in the alert

        Assert.assertEquals(webDriver.findElement(By.id("result")).getText(), "You clicked: Cancel");
    }


    @Test
    public void testJavaScriptPrompt(){
        webDriver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = webDriver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        alert.sendKeys("Selenium");
        alert.accept(); //Clicking OK button in the alert

        Assert.assertEquals(webDriver.findElement(By.id("result")).getText(), "You entered: Selenium");
    }

}
