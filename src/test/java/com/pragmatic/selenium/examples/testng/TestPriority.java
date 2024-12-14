package com.pragmatic.selenium.examples.testng;

import org.testng.annotations.Test;

public class TestPriority {

    @Test (priority = 3)
    public void testMethod1() {
        System.out.println("TestPriority.testMethod1");
    }

    @Test(priority = 2)
    public void testMethod2() {
        System.out.println("TestPriority.testMethod2");
    }

    @Test (priority = 1)
    public void testMethod3() {
        System.out.println("TestPriority.testMethod3");
    }

    @Test
    public void testMethod4() {
        System.out.println("TestPriority.testMethod4");
    }

    @Test (priority = 0)
    public void testMethod5() {
        System.out.println("TestPriority.testMethod5");
    }
}
