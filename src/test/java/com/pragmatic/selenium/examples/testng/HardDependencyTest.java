package com.pragmatic.selenium.examples.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HardDependencyTest {

    @Test
    public void testMethod5() {
        System.out.println("TestPriority.testMethod5");
    }

    @Test
    public void testMethod1() {
        System.out.println("TestPriority.testMethod1");
    }

    @Test
    public void testMethod2() {
        System.out.println("TestPriority.testMethod2");
        Assert.fail("This test is forcefully failed to demo soft dependency");
    }

    @Test(dependsOnMethods = {"testMethod1", "testMethod2"})
    public void testMethod3() {
        System.out.println("TestPriority.testMethod3");
    }

    @Test(dependsOnMethods = {"testMethod2"}, alwaysRun = false)
    public void testMethod4() {
        System.out.println("TestPriority.testMethod4");
    }


}
