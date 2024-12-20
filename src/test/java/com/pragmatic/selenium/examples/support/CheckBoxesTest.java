package com.pragmatic.selenium.examples.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBoxesTest {


    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @Test
    public void testCheck() {

        // Locate the first checkbox
        WebElement firstCheckbox = webDriver.findElement(By.id("my-check-1"));

        Check checkBox = new Check(firstCheckbox);

        // Assert that the first checkbox is selected
        Assert.assertTrue(checkBox.isChecked(), "The first checkbox should be selected.");
        Assert.assertEquals(checkBox.getLabel(), "Checked checkbox");

        // Locate the second checkbox
        WebElement eleSecondCheck = webDriver.findElement(By.id("my-check-2"));

        // Assert that the second checkbox is not selected
        Assert.assertFalse(eleSecondCheck.isSelected(), "The second checkbox should not be selected by default.");
        Check secondCheck = new Check(eleSecondCheck);

        // Click the second checkbox to select it
        secondCheck.check();

        // Assert that the second checkbox is now selected
        Assert.assertTrue(secondCheck.isChecked(), "The second checkbox should be selected after clicking.");

        secondCheck.uncheck();
        Assert.assertFalse(secondCheck.isChecked(), "The second checkbox should be unchecked after clicking again");


    }
}
