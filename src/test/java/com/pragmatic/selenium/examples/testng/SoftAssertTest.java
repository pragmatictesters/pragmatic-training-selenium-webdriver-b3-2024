package com.pragmatic.selenium.examples.testng;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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


    @Test
    public void testHardAssert(){
        Assert.assertEquals(2,2);
        Assert.assertEquals(6,5);
        Assert.assertEquals(11,10);
        System.out.println("SoftAssertTest.testHardAssert");
    }

    @Test
    public void testSoftAssert(){

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(2,2);
        softAssert.assertEquals(6,5);
        softAssert.assertEquals(11,10);
        softAssert.assertAll();
        System.out.println("SoftAssertTest.testHardAssert");

    }
}
