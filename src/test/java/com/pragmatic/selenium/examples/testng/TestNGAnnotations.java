package com.pragmatic.selenium.examples.testng;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.internal.thread.ThreadTimeoutException;

import java.util.Random;


public class TestNGAnnotations extends TestNGTestBase {


    @BeforeClass
    public void beforeClass() {
        System.out.println("TestNGAnnotations.beforeClass");
    }


    @BeforeMethod
    public void beforeMethod() {
        System.out.println("TestNGAnnotations.beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("TestNGAnnotations.afterMethod");
    }

    @BeforeMethod(onlyForGroups = {"smoke"})
    public void beforeMethodOnlyForSmokeGroups() {
        System.out.println("TestNGAnnotations.beforeMethodOnlyForSmokeGroups");

    }

    @AfterMethod(onlyForGroups = {"smoke"})
    public void afterMethodOnlyForSmokeGroups() {
        System.out.println("TestNGAnnotations.afterMethodOnlyForSmokeGroups");
    }


    @AfterClass
    public void afterClass() {
        System.out.println("TestNGAnnotations.afterClass");
    }


    @Test(groups = {"smoke"})
    public void testMethod1() {
        System.out.println("TestNGAnnotations.testMethod1");
    }

    @Test(groups = {"smoke", "regression"})
    public void testMethod2() {
        System.out.println("TestNGAnnotations.testMethod2");
    }


    @Test(groups = {"regression"})
    public void testMethod3() {
        System.out.println("TestNGAnnotations.testMethod3");
    }

    @Test(description = "Verify login with valid credentials")
    public void testMethod4() {
        System.out.println("TestNGAnnotations.testMethod4");
    }

    @Test(enabled = false)
    public void testMethod5() {
        System.out.println("TestNGAnnotations.testMethod5");
    }

    @Test(dependsOnMethods = { "testMethod4"})
    public void testMethod6() {
        System.out.println("TestNGAnnotations.testMethod6");
    }


    @Test(dependsOnMethods = {"testMethod3", "testMethod4"})
    public void testMethod7() {
        System.out.println("TestNGAnnotations.testMethod7");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testMethod8() {
        System.out.println("TestNGAnnotations.testMethod8");
        WebDriver webDriver = null;
        webDriver.close();
    }


    @Test(invocationCount = 5)
    public void testMethod9() {
        System.out.println("TestNGAnnotations.testMethod9");
    }


    @Test(invocationCount = 5, invocationTimeOut = 5000, expectedExceptions = ThreadTimeoutException.class)
    public void testMethod10() throws InterruptedException {
        System.out.println("TestNGAnnotations.testMethod10");
        Thread.sleep(2000);
    }


    @Test(timeOut = 1200)
    public void testMethod11() throws InterruptedException {
        System.out.println("TestNGAnnotations.testMethod11");
        Thread.sleep(2000);
    }

    @Test(invocationCount = 20, threadPoolSize = 5)
    public void testMethod12() throws InterruptedException {
        System.out.println("TestNGAnnotations.testMethod12");
        Thread.sleep(2000);
    }


    @Test(invocationCount = 5, successPercentage = 10)
    public void flakyTest() {

        Random random = new Random();
        int randomNumber = random.nextInt(5) + 1;
        // Simulating a flaky test: Passes every 3rd attempt.
        if (randomNumber % 3 == 0) {
            System.out.println("Attempt Test passed!");
        } else {
            throw new RuntimeException("Simulating failure.");
        }

    }

}
