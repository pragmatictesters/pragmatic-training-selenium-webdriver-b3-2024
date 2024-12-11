package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest {


    @Test
    public void testDragAndDrop(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions actions = new Actions(webDriver);


        WebElement sourceElement = webDriver.findElement(By.id("column-a"));
        WebElement targetElement = webDriver.findElement(By.id("column-b"));

        Assert.assertEquals(sourceElement.getText(), "A");
        Assert.assertEquals(targetElement.getText(), "B");

        actions.dragAndDrop(sourceElement, targetElement);
        actions.build().perform();

        Assert.assertEquals(sourceElement.getText(), "B");
        Assert.assertEquals(targetElement.getText(), "A");

        webDriver.quit();

    }
}