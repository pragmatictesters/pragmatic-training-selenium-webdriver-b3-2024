package com.pragmatic.selenium.examples.testng;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertTest {



    @Test
    public void testJavaScriptAlert(){

        SoftAssert softAssert = new SoftAssert();

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://the-internet.herokuapp.com/javascript_alerts");

        webDriver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = webDriver.switchTo().alert();

        softAssert.assertEquals(alert.getText(), "I am a JS Prompt"); //This will fail

        alert.sendKeys("Selenium");
        alert.accept(); //Clicking OK button in the alert

        softAssert.assertEquals(webDriver.findElement(By.id("result")).getText(), "You entered: Selenium"); //This will be passed
        webDriver.close();

        softAssert.assertAll();

    }
}
