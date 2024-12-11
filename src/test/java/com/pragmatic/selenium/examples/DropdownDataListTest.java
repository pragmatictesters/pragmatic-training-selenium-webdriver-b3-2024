package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownDataListTest {



    @Test
    public void testDropdownDataList(){

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.selenium.dev/selenium/web/web-form.html");

        // Locate the input field associated with the datalist
        WebElement datalistInput = webDriver.findElement(By.name("my-datalist"));

        // Send the desired value to the input field
        datalistInput.sendKeys("San Francisco");

        // Get the value entered in the input field
        String enteredValue = datalistInput.getDomProperty("value");

        // Validate that the value was correctly entered
        Assert.assertEquals(enteredValue, "San Francisco", "The entered value is incorrect!");

        webDriver.close();


    }
}
