package com.pragmatic.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerTest {


    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
        webDriver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.close();
    }

    @Test
    public void testSelectSpecificDateInCurrentDate()  {
        LocalDate currentDate = LocalDate.now();
        int currentDay = 14; // Fixed day of the current month
        String expectedDate = currentDate.withDayOfMonth(currentDay).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        WebElement dateInput = webDriver.findElement(By.name("my-date"));
        dateInput.click();
        webDriver.findElement(By.xpath("//td[@class='day' and text()='14']")).click();
        Assert.assertEquals(dateInput.getDomProperty("value"), expectedDate);
    }


    @Test
    public void testTypeSpecificDateInCurrentMonth()  {
        LocalDate currentDate = LocalDate.now();
        int currentDay = 14; // Fixed day of the current month
        String expectedDate = currentDate.withDayOfMonth(currentDay).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        WebElement dateInput = webDriver.findElement(By.name("my-date"));
        dateInput.sendKeys(expectedDate);
        Assert.assertEquals(dateInput.getDomProperty("value"), expectedDate);
    }






}
