package com.pragmatic.selenium.examples.testng.ignoring;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class SampleIgnoreSecondTest {

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
    }

    @Test
    public void testMethod3() {
        System.out.println("TestPriority.testMethod3");
    }

    @Test
    public void testMethod4() {
        System.out.println("TestPriority.testMethod4");
    }


}
