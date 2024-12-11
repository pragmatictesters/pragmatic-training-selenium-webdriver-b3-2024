package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownListSelectTest {



    @Test
    public void testDropdownSelect(){


        WebDriver webDriver = new ChromeDriver();


        webDriver.get("https://www.selenium.dev/selenium/web/web-form.html");
        Select selNumber = new Select(webDriver.findElement(By.name("my-select")));

        selNumber.selectByIndex(2);
        Assert.assertEquals(selNumber.getFirstSelectedOption().getText(), "Two");
        selNumber.selectByVisibleText("One");
        selNumber.selectByValue("3");
        Assert.assertEquals(selNumber.getFirstSelectedOption().getText(), "Three");

        webDriver.quit();
    }
}
