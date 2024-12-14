package com.pragmatic.selenium.examples.testng.listeners;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Test
public class TestAtClassLevel {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("TestAtClassLevel.beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("TestAtClassLevel.afterMethod");
    }

    public void testMethod5() {
        System.out.println("TestPriority.testMethod5");
    }

    public void testMethod1() {
        System.out.println("TestPriority.testMethod1");
    }

    public void testMethod2() {
        System.out.println("TestPriority.testMethod2");
    }

    public void testMethod3() {
        System.out.println("TestPriority.testMethod3");
    }

    @Test(enabled = false)
    public void testMethod4() {
        System.out.println("TestPriority.testMethod4");
    }


}
