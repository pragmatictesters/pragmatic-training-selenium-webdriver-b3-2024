package com.pragmatic.selenium.examples.locators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @Test
    public void testLocateElementByAttributeAndValue() {
        // Locate the first checkbox
        webDriver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement eleTextBox = webDriver.findElement(new ByAttributeValue("myprop", "myvalue"));
        Assert.assertEquals(eleTextBox.getDomProperty("id"), "my-text-id");

    }


    @Test
    public void testLocateElementsByText(){
        webDriver.get("https://the-internet.herokuapp.com/javascript_alerts");
        webDriver.findElement(new ByText("Click for JS Alert")).click();
        Alert alert = webDriver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept(); //Clicking OK button in the alert

        Assert.assertEquals(webDriver.findElement(By.id("result")).getText(), "You successfully clicked an alert");
    }


    @Test
    public void testLocateElementsByPartialText(){
        webDriver.get("https://the-internet.herokuapp.com/javascript_alerts");
        webDriver.findElement(new ByPartialText("JS Alert")).click();
        Alert alert = webDriver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept(); //Clicking OK button in the alert

        Assert.assertEquals(webDriver.findElement(By.id("result")).getText(), "You successfully clicked an alert");
        webDriver.findElement(new ByPartialText("for JS Alert")).click();
        webDriver.switchTo().alert().accept();
    }






}
