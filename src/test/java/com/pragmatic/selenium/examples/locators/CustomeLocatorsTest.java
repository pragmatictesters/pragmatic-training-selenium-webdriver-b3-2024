package com.pragmatic.selenium.examples.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomeLocatorsTest {


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
        WebElement eleTextBox = webDriver.findElement(new ByAttributeValue("myprop", "myvalue"));
        Assert.assertEquals(eleTextBox.getDomProperty("id"), "my-text-id");


    }
}
